package com.rafaelsonego.brewer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "beer")
public class Beer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Error SKU!!")
	private String sku;

	@NotBlank(message = "Error Name!!")
	private String name;

	@Size(min = 1, max = 50, message = "Error Description!!")
	private String description;

	private BigDecimal value;

	/***
	 * @Column(name = "alcohol_percent"): alcohol_percent is a name of the column on the database
	 */
	@Column(name = "alcohol_percent")
	private BigDecimal alcoholPercent;

	private BigDecimal commission;

	private Integer inventory;

	@Enumerated(EnumType.STRING)
	private Origin origin;

	@Enumerated(EnumType.STRING)
	private Taste taste;

	/***
	 * @ManyToOne: Many beers have one style (beer 1 - style 1 beer 2 - style 1 beer 3 - style 1)
	 * @JoinColumn(name = "style_id"): style_id is a name of the column on the database
	 */
	@ManyToOne
	@JoinColumn(name = "style_id")
	private BeerStyle beerStyle;

	// *************** Getter and Setters ***************

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getAlcoholPercent() {
		return alcoholPercent;
	}

	public void setAlcoholPercent(BigDecimal alcoholPercent) {
		this.alcoholPercent = alcoholPercent;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer stock) {
		this.inventory = stock;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public Taste getTaste() {
		return taste;
	}

	public void setTaste(Taste taste) {
		this.taste = taste;
	}

	public BeerStyle getBeerStyle() {
		return beerStyle;
	}

	public void setBeerStyle(BeerStyle beerStyle) {
		this.beerStyle = beerStyle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Beer other = (Beer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
