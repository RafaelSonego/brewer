package com.rafaelsonego.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.rafaelsonego.brewer.dto.PhotoDTO;

public class PhotoStorageRunnable implements Runnable {

	private DeferredResult<PhotoDTO> result;
	private  MultipartFile[] files;
	
	public PhotoStorageRunnable( MultipartFile[] files, DeferredResult<PhotoDTO> result) {
		this.result = result;
		this.files = files;
	}
	
	@Override
	public void run() {
		result.setResult(new PhotoDTO(files[0].getOriginalFilename(), files[0].getContentType()));
	}

}
