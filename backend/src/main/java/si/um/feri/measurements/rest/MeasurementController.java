package si.um.feri.measurements.rest;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import si.um.feri.measurements.dao.MeasurementRepository;
import si.um.feri.measurements.dao.ProductRepository;
import si.um.feri.measurements.dto.post.PostMeasurement;
import si.um.feri.measurements.dto.post.PostMeasurementResponse;
import si.um.feri.measurements.vao.Measurement;

import java.util.logging.Logger;

@Path("/product_measurement")
public class MeasurementController {

    private static final Logger log = Logger.getLogger(MeasurementController.class.toString());

    @Inject
    MeasurementRepository measurementRepository;

    @Inject
    ProductRepository productRepository;

    boolean ok = true;
    @POST
    /**
     * Adds a measurement for a given product.
     *
     * This method retrieves a product by its ID from the repository, creates a new
     * Measurement object based on the provided PostMeasurement data, and checks
     * if the average temperature is within the acceptable range defined by the
     * product's minimum and maximum measurements. If the average temperature
     * is outside this range, it logs an appropriate message indicating that
     * action is needed.
     *
     * The method persists the Measurement object to the database and returns
     * a response indicating whether the operation was successful or not.
     *
     * @param m the PostMeasurement object containing measurement data
     * @return a Uni containing a RestResponse with the result of the operation
     *         and a message indicating success or failure
     *
     * @throws IllegalArgumentException if the provided PostMeasurement object
     *         contains invalid data (e.g., null values)
     * @throws NoSuchElementException if no product is found with the given ID
     * @throws PersistenceException if there is an error persisting the measurement
     */
    public Uni<RestResponse<PostMeasurementResponse>> addMeasurement(PostMeasurement m){
        return productRepository.findById(Long.valueOf(m.id())).onItem().transformToUni(item -> {
                    log.info("id: "+item.getId());
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
