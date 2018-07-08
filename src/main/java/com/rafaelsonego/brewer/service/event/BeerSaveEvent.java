package com.rafaelsonego.brewer.service.event;

import org.springframework.util.StringUtils;

import com.rafaelsonego.brewer.model.Beer;

public class BeerSaveEvent {

	private Beer beer;

	public BeerSaveEvent(Beer beer) {
		this.beer = beer;
	}

	public Beer getBeer() {
		return beer;
	}
	
	public boolean hasPhoto() {
		return !StringUtils.isEmpty(this.beer.getPhotoName());
	}

}
