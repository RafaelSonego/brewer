package com.rafaelsonego.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoStorage {

	String saveTemporaryPhoto(MultipartFile[] files);

	byte[] getTemporaryPhoto(String name);
	
	byte[] getPhoto(String name);

	void moveToPermanentStorage(String photoName);
	
}
