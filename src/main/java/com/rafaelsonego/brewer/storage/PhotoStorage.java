package com.rafaelsonego.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoStorage {

	String saveTemporaryPhoto(MultipartFile[] files);
	
}
