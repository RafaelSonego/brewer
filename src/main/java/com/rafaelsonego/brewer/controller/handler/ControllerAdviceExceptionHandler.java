package com.rafaelsonego.brewer.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rafaelsonego.brewer.service.exception.BeerStyleException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

	@ExceptionHandler(BeerStyleException.class)
	public ResponseEntity<String> handleBeerStyleException(BeerStyleException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
}
