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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import com.rafaelsonego.brewer.validations.TextFieldValidation;

@Entity
@Table(name = "beer")
public class Beer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Pattern(regexp = "([a-zA-Z]{2}\\d{4})?", message = "SKU: Correct standard XX9999")
	@NotBlank(message = "SKU: Field is required")
	private String sku;

	@TextFieldValidation
	@NotBlank(message = "Name: Field is required")
	private String name;

	@Size(min = 10, max = 500, message = "Description: Size between 10 and 500 characters")
	@NotBlank(message = "Description: Field is required")
	private String description;

	@NotNull(message = "Value: Field is required")
	@DecimalMin(value = "2.00", message = "Minimum value of a beer is 2.00")
	@DecimalMax(value = "20.00", message = "Maximum value of a beer is 20.00")
	private BigDecimal value;

	/***
	 * @Column(name = "alcohol_percent"): alcohol_percent is a name of the column on the database
	 */
	@Column(name = "alcohol_percent")
	@NotNull(message="Alcohol Percent: Field is required")
	@DecimalMax(value = "100.00", message = "Maximum alcohol percent is 100.00")
	private BigDecimal alcoholPercent;

	@NotNull(message="Comission: Field is required")
	@DecimalMax(value = "100.00", message = "Maximum value of the comission is 100.00")
	private BigDecimal commission;

	@NotNull(message="Inventory: Field is required")
	@Max(value = 10000, message = "Maximum value of the inventory is 1000.00")
	private Integer inventory;

	@NotNull(message="Origin: Field is required")
	@Enumerated(EnumType.STRING)
	private Origin origin;

	@NotNull(message="Taste: Field is required")
	@Enumerated(EnumType.STRING)
	private Taste taste;

	/***
	 * @ManyToOne: Many beers have one style (beer 1 - style 1 beer 2 - style 1 beer 3 - style 1)
	 * @JoinColumn(name = "style_id"): style_id is a name of the column on the database
	 */
	@ManyToOne
	@JoinColumn(name = "style_id")
	@NotNull(message="Style: Field is required")
	private BeerStyle beerStyle;

	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		sku = sku.toUpperCase();
	}

	@Column(name = "content_type")
	private String photoContentType;
	
	@Column(name = "photo")
	private String photoName;
	
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
	
	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoNameOrMock() {
		return StringUtils.isEmpty(photoName)? "missing.png" : photoName;
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
