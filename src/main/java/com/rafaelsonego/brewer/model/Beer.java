package com.rafaelsonego.brewer.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Beer {

	//@NotEmpty Accept spaces
	@NotBlank(message = "Error SKU!!")
	private String sku;
	
	@NotBlank(message = "Error Name!!")
	private String name;
	
	@Size(min = 1, max = 50, message = "Error Description!!")
	private String description;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
