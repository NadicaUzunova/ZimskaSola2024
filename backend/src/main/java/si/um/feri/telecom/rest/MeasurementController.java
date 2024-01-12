package si.um.feri.telecom.rest;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import si.um.feri.telecom.dao.MeasurementRepository;
import si.um.feri.telecom.dao.ProductRepository;
import si.um.feri.telecom.dto.post.PostMeasurement;
import si.um.feri.telecom.dto.post.PostMeasurementResponse;
import si.um.feri.telecom.vao.Measurement;
import si.um.feri.telecom.vao.Product;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Path("/measurements")
public class MeasurementController {

    private static final Logger log = Logger.getLogger(MeasurementController.class.toString());

    @Inject
    MeasurementRepository measurementRepository;

    @Inject
    ProductRepository productRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Measurement>> getAllProducts() {
        return measurementRepository.listAll();
    }
    boolean ok = true;
    @POST
    public Uni<RestResponse<PostMeasurementResponse>> addMeasurement(PostMeasurement m){
        return productRepository.findById(Long.valueOf(m.id())).onItem().transformToUni(item -> {
                    Measurement vao = new Measurement(m, item);
                    boolean ok = true;

                    if (m.avgTemperature() < item.getMinMeasure()) {
                        log.info(() -> "/product_measurement sent: " + m + "; product: " + item + "; ACTION NEEDED-lower");
                        ok = false;
                    }
                    if (m.avgTemperature() > item.getMaxMeasure()) {
                        log.info(() -> "/product_measurement sent: " + m + "; product: " + item + "; ACTION NEEDED-higher");
                        ok = false;
                    }

                    vao.setOk(ok);
                    boolean finalOk = ok;
                    return measurementRepository.persistAndFlush(vao)
                            .onItem().transform(ignored -> RestResponse.ok(new PostMeasurementResponse(finalOk ? "ok" : "not ok")));
                })
                .onFailure().recoverWithItem(failure -> RestResponse.ResponseBuilder.create(Response.Status.NOT_ACCEPTABLE, new PostMeasurementResponse("product-not-found")).build());
        }
}
