package si.um.feri.telecom;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.vertx.VertxContextSupport;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.subscription.Cancellable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.hibernate.reactive.mutiny.Mutiny;
import si.um.feri.telecom.dao.MeasurementRepository;
import si.um.feri.telecom.dao.ProductRepository;
import si.um.feri.telecom.dto.post.PostMeasurement;
import si.um.feri.telecom.vao.Measurement;
import si.um.feri.telecom.vao.Product;

@ApplicationScoped
public class AddTestData {

    @Inject
    ProductRepository productRepository;
    @Inject
    MeasurementRepository measurementRepository;

    public void onStart(@Observes StartupEvent ev) throws Throwable {
        VertxContextSupport.subscribeAndAwait(() ->
            Panache.withTransaction(() -> {
                Product p1 = new Product();
                p1.setName("Milka Classic");
                p1.setMinMeasure(-5.0);
                p1.setMaxMeasure(18.0);

                Product p2 = new Product();
                p2.setName("Chicken Breasts");
                p2.setMinMeasure(-25.0);
                p2.setMaxMeasure(-8.0);

                PostMeasurement pm1 = new PostMeasurement(p1.getId(), 12);
                PostMeasurement pm2 = new PostMeasurement(p1.getId(), -10);
                return productRepository.persist(p1, p2).chain(() -> measurementRepository.persist(new Measurement(pm1, p1), new Measurement(pm2, p2)));
            }));
    }
}
