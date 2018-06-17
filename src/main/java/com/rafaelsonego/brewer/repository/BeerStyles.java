package com.rafaelsonego.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafaelsonego.brewer.model.BeerStyle;

@Repository
public interface BeerStyles extends JpaRepository<BeerStyle, Long> {

}
