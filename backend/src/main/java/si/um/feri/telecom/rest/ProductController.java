package si.um.feri.telecom.rest;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;
import si.um.feri.telecom.dao.ProductRepository;
import si.um.feri.telecom.vao.Product;

import java.util.List;

@Path("/products")
public class ProductController {

    @Inject
    ProductRepository productRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Product>> getAllProducts() {
        return productRepository.listAll();
    }

    //stream() metoda je deprecated

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Product> addProduct(Product product){
        return productRepository.persistAndFlush(product);
    }


}
