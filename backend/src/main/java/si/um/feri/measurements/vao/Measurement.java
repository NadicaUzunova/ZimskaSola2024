package si.um.feri.measurements.vao;

import jakarta.persistence.*;
import si.um.feri.measurements.dto.post.PostMeasurement;

import java.time.LocalDateTime;

@Entity
public class Measurement {
	
	public Measurement(PostMeasurement m, Product p) {
		this.value=m.avgTemperature();
		this.product=p;
	}

	public Measurement() {

	}

	/**
	 * Converts the current object to a Data Transfer Object (DTO) representation.
	 *
	 * This method creates a new instance of {@link si.um.feri.measurements.dto.Measurement}
	 * using the properties of the current object. The created DTO includes the following fields:
	 * <ul>
	 *   <li>ID of the current object</li>
	 *   <li>Formatted creation date using JSON date format</li>
	 *   <li>ID of the associated product, or -1 if no product is associated</li>
	 *   <li>Value of the measurement</li>
	 *   <li>Status indicating if the measurement is valid</li>
	 * </ul>
	 *
	 * @return a {@link si.um.feri.measurements.dto.Measurement} object representing this measurement.
	 *
	 * @throws NullPointerException if the creation date is null or if the product is null and cannot be processed.
	 */
	public si.um.feri.measurements.dto.Measurement toDto() {
		return new si.um.feri.measurements.dto.Measurement(
				id,
				si.um.feri.measurements.dto.Measurement.JSON_DATE_FORMAT.format(created),
			(product!=null)?Long.valueOf(product.getId()):-1,
			value,
			isOk
		);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "measvalue")
	private double value;
	
	private LocalDateTime created=LocalDateTime.now();
	
	private boolean isOk=true;
	
	@ManyToOne
	private Product product;

	/**
	 * Retrieves the unique identifier associated with this instance.
	 *
	 * @return the unique identifier (ID) as a {@link Long} object.
	 *
	 * @throws NullPointerException if the ID has not been initialized and is null.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the identifier for this object.
	 *
	 * @param id the identifier to set, which can be null
	 * @throws IllegalArgumentException if the id is negative
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retrieves the current value.
	 *
	 * @return the current value as a double.
	 * @throws IllegalStateException if the value has not been initialized.
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Sets the value of this object to the specified double value.
	 *
	 * @param value the new value to be set
	 * @throws IllegalArgumentException if the value is not valid (e.g., if it is NaN or infinite)
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * Retrieves the creation date and time of the object.
	 *
	 * @return a {@link LocalDateTime} object representing the creation date and time.
	 *
	 * @throws NullPointerException if the creation date and time has not been initialized.
	 */
	public LocalDateTime getCreated() {
		return created;
	}

	/**
	 * Sets the creation timestamp for this object.
	 *
	 * @param created the LocalDateTime representing the creation time
	 * @throws IllegalArgumentException if the created parameter is null
	 */
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	/**
	 * Checks if the current state is acceptable.
	 *
	 * @return {@code true} if the state is acceptable; {@code false} otherwise.
	 *
	 * @throws IllegalStateException if the method is called when the object is in an invalid state.
	 */
	public boolean isOk() {
		return isOk;
	}

	/**
	 * Sets the value of the 'isOk' property.
	 *
	 * @param ok a boolean value indicating the new state of 'isOk'.
	 * @throws IllegalArgumentException if the provided value is not valid (e.g., if additional validation rules are applied).
	 */
	public void setOk(boolean ok) {
		isOk = ok;
	}

	/**
	 * Retrieves the product associated with this instance.
	 *
	 * @return the {@link Product} object representing the product.
	 * @throws IllegalStateException if the product has not been initialized or is null.
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Sets the product for this instance.
	 *
	 * @param product the Product object to be set
	 * @throws IllegalArgumentException if the provided product is null
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
}
