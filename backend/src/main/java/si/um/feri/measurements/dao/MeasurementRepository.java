package si.um.feri.measurements.dao;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import si.um.feri.measurements.vao.Measurement;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class MeasurementRepository implements PanacheRepository<Measurement> {
    /**
     * Retrieves a list of {@link Measurement} objects that were created after the specified date and time.
     *
     * @param created the date and time to compare against; only measurements created after this time will be included
     * @return a {@link Uni} containing a list of {@link Measurement} objects that match the criteria
     * @throws IllegalArgumentException if the provided {@code created} parameter is null
     * @throws SomeDatabaseException if there is an error while querying the database
     */
    public Uni<List<Measurement>> findByCreatedGreaterThan(LocalDateTime created){
        return find("created >= ?1", created).list();
    }
}
