package com.rafaelsonego.brewer.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.rafaelsonego.brewer.model.BeerStyle;

public class BeerStyleConverter implements Converter<String, BeerStyle>{

	@Override
	public BeerStyle convert(String id) {
		if(!StringUtils.isEmpty(id)) {
			BeerStyle beerStyle = new BeerStyle();
			beerStyle.setId(Long.valueOf(id));
			return beerStyle;
		}
		return null;
	}

}
