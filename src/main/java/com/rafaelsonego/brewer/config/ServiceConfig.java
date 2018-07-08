package com.rafaelsonego.brewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.rafaelsonego.brewer.service.BeerService;
import com.rafaelsonego.brewer.storage.PhotoStorage;
import com.rafaelsonego.brewer.storage.local.PhotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = BeerService.class)
public class ServiceConfig {
	
	@Bean
	public PhotoStorage photoStorage() {
		return new PhotoStorageLocal();
	}
}
