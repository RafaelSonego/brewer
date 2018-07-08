package com.rafaelsonego.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.rafaelsonego.brewer.dto.PhotoDTO;

public class PhotoStorageRunnable implements Runnable {

	private DeferredResult<PhotoDTO> result;
	private  MultipartFile[] files;
	private PhotoStorage photoStorage;
	
	public PhotoStorageRunnable( MultipartFile[] files, DeferredResult<PhotoDTO> result, PhotoStorage photoStorage) {
		this.result = result;
		this.files = files;
		this.photoStorage = photoStorage;
	}
	
	@Override
	public void run() {
		String photoName = this.photoStorage.saveTemporaryPhoto(files);
		result.setResult(new PhotoDTO(photoName, files[0].getContentType()));
	}

}
