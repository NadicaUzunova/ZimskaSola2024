package si.um.feri.telecom.dao;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import si.um.feri.telecom.vao.Measurement;

@ApplicationScoped
public class MeasurementRepository implements PanacheRepository<Measurement> {
}
