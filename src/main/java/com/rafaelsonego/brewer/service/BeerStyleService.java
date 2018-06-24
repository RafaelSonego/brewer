package com.rafaelsonego.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelsonego.brewer.model.BeerStyle;
import com.rafaelsonego.brewer.repository.BeerStyleRepository;
import com.rafaelsonego.brewer.service.exception.BeerStyleException;

@Service
public class BeerStyleService {

	@Autowired
	private BeerStyleRepository beerStyleRepository;
	
	@Transactional
	public BeerStyle save(BeerStyle beerStyle){
		ValidateBeerStyleAlreadyExists(beerStyle);
		return beerStyleRepository.saveAndFlush(beerStyle);
	}

	private void ValidateBeerStyleAlreadyExists(BeerStyle beerStyle){
		Optional<BeerStyle> beerSetypeOptional = beerStyleRepository.findByNameIgnoreCase(beerStyle.getName());
		if(beerSetypeOptional.isPresent()) {
			throw new BeerStyleException("Style of beer alredy exists");
		}
	}
	
}
