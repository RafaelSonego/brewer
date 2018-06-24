package com.rafaelsonego.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelsonego.brewer.model.Beer;
import com.rafaelsonego.brewer.repository.BeerRepository;
import com.rafaelsonego.brewer.service.exception.BeerExpetion;

@Service
public class BeerService {

	@Autowired
	private BeerRepository beerRepository;

	@Transactional
	public void save(Beer beer) {
		validateBeerAlreadyExists(beer);

		beerRepository.save(beer);
	}

	private void validateBeerAlreadyExists(Beer beer) {
		Optional<Beer> beerOptional = beerRepository.findByNameIgnoreCaseOrSkuIgnoreCase(beer.getName(), beer.getSku());
		if (beerOptional.isPresent()) {
			throw new BeerExpetion("Beer alredy exists with the name: " + beer.getName());
		}
	}

}
