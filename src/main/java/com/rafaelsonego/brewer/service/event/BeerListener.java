package com.rafaelsonego.brewer.service.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rafaelsonego.brewer.storage.PhotoStorage;

@Component
public class BeerListener {
	
	@Autowired
	private PhotoStorage photoStorage;
	
	@EventListener(condition = "#event.hasPhoto()")
	public void saveBeer(BeerSaveEvent event) {
		photoStorage.moveToPermanentStorage(event.getBeer().getPhotoName());
	}
	
}
