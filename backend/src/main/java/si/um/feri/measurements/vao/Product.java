package si.um.feri.measurements.vao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Product {

	public Product(si.um.feri.measurements.dto.Product dto) {
		setName(dto.name());
		setMaxMeasure(dto.maxMeasure());
		setMinMeasure(dto.minMeasure());
	}

	public Product() {

	}

	/**
	 * Updates the current product instance with the values from the provided Product DTO.
	 *
	 * This method sets the name, maximum measurement, and minimum measurement of the
	 * current product based on the data from the specified Product DTO.
	 *
	 * @param dto the Product DTO containing the new values for the product
	 * @throws NullPointerException if the provided dto is null
	 * @throws IllegalArgumentException if any of the values in dto are invalid (e.g.,
	 *         maxMeasure is less than minMeasure)
	 */
	public void updateFrom(si.um.feri.measurements.dto.Product dto) {
		setName(dto.name());
		setMaxMeasure(dto.maxMeasure());
		setMinMeasure(dto.minMeasure());
	}
	
	/**
	 * Converts the current object to a Data Transfer Object (DTO) of type {@link si.um.feri.measurements.dto.Product}.
	 *
	 * @return a {@link si.um.feri.measurements.dto.Product} instance containing the id, name,
	 *         maximum measurement, and minimum measurement of the current object.
	 *
	 * @throws IllegalStateException if the object is in an invalid state for conversion,
	 *         such as if the maximum measurement is less than the minimum measurement.
	 */
	public si.um.feri.measurements.dto.Product toDto() {
		return new si.um.feri.measurements.dto.Product(
			getId(),
			getName(),
			maxMeasure,
			minMeasure);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	protected String name;

	protected LocalDateTime created=LocalDateTime.now();

	protected double maxMeasure;

	protected double minMeasure;

	/**
	 * Retrieves the unique identifier associated with this object.
	 *
	 * @return the unique identifier (ID) as a {@code Long} object.
	 *         Returns {@code null} if the ID has not been set.
	 *
	 * @throws IllegalStateException if the ID is not initialized and cannot be retrieved.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the identifier for this object.
	 *
	 * @param id the identifier to set, which can be null.
	 * @throws IllegalArgumentException if the id is negative.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retrieves the name associated with this object.
	 *
	 * @return the name as a {@code String}.
	 * @throws NullPointerException if the name is null.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the object.
	 *
	 * @param name the name to be set; must not be null or empty
	 * @throws IllegalArgumentException if the provided name is null or empty
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the creation date and time of the object.
	 *
	 * @return a {@link LocalDateTime} representing the creation date and time.
	 * @throws IllegalStateException if the creation date and time is not set.
	 */
	public LocalDateTime getCreated() {
		return created;
	}

	/**
	 * Sets the creation date and time.
	 *
	 * @param created the LocalDateTime representing the creation date and time
	 * @throws IllegalArgumentException if the created parameter is null
	 */
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	/**
	 * Retrieves the maximum measurement value.
	 *
	 * @return the maximum measurement as a double.
	 * @throws IllegalStateException if the maximum measurement has not been initialized.
	 */
	public double getMaxMeasure() {
		return maxMeasure;
	}

	/**
	 * Sets the maximum measurement value.
	 *
	 * @param maxMeasure the maximum measurement value to set
	 * @throws IllegalArgumentException if maxMeasure is negative
	 */
	public void setMaxMeasure(double maxMeasure) {
		this.maxMeasure = maxMeasure;
	}

	/**
	 * Retrieves the minimum measurement value.
	 *
	 * @return the minimum measurement as a double.
	 * @throws IllegalStateException if the minimum measurement has not been initialized.
	 */
	public double getMinMeasure() {
		return minMeasure;
	}

	/**
	 * Sets the minimum measurement value.
	 *
	 * @param minMeasure the minimum measurement value to set
	 * @throws IllegalArgumentException if minMeasure is less than zero
	 */
	public void setMinMeasure(double minMeasure) {
		this.minMeasure = minMeasure;
	}
}
