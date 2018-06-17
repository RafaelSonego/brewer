package com.rafaelsonego.brewer.model;

public enum Taste {
	SWEET("Sweet"), SOUR("Sour"), BITTER("Bitter"), STRONG("Strong"), SOFTY("Softy");

	private String description;

	Taste(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
