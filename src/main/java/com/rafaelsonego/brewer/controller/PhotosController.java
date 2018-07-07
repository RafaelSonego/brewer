package com.rafaelsonego.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.rafaelsonego.brewer.dto.PhotoDTO;
import com.rafaelsonego.brewer.storage.PhotoStorage;
import com.rafaelsonego.brewer.storage.PhotoStorageRunnable;

@RestController
@RequestMapping("/photos")
public class PhotosController {
	
	@Autowired
	private PhotoStorage photoStorage;
	
	@PostMapping
	public DeferredResult<PhotoDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<PhotoDTO> result = new DeferredResult<>();
		Thread thread = new Thread(new PhotoStorageRunnable(files, result, photoStorage));
		thread.start();
		return result;
	}

}
