package si.um.feri.telecom.vao;

import jakarta.persistence.*;
import si.um.feri.telecom.dto.post.PostMeasurement;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;

@Entity
public class Measurement {
	
	public Measurement(PostMeasurement m, Product p) {
		this.value=m.avgTemperature();
		this.product=p;
	}

	public Measurement() {

	}

	public si.um.feri.telecom.dto.Measurement toDto() {
		return new si.um.feri.telecom.dto.Measurement(
			id,
				si.um.feri.telecom.dto.Measurement.JSON_DATE_FORMAT.format(created),
			(product!=null)?product.getId():-1,
			value,
			isOk
		);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "measvalue")
	private double value;
	
	private LocalDateTime created=LocalDateTime.now();
	
	private boolean isOk=true;
	
	@ManyToOne
	private Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean ok) {
		isOk = ok;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
