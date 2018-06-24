package com.rafaelsonego.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelsonego.brewer.model.Beer;
import com.rafaelsonego.brewer.repository.BeerRepository;

@Service
public class BeerService {

	@Autowired
	private BeerRepository beerRepository;

	@Transactional
	public void save(Beer beer) {
		beerRepository.save(beer);
	}

}
