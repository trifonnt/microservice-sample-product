package ch.frankel.microservice.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = AUTO)
	private long id;

	private String name;

	public Product() {
	}

	public Product(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}