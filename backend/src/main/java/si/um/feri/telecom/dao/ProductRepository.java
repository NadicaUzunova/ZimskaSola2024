package si.um.feri.telecom.dao;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import si.um.feri.telecom.vao.Product;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {
}
