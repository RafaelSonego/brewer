package com.rafaelsonego.brewer.storage.local;

import static java.nio.file.FileSystems.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.rafaelsonego.brewer.storage.PhotoStorage;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class PhotoStorageLocal implements PhotoStorage {

	private static final Logger logger = LoggerFactory.getLogger(PhotoStorageLocal.class);

	private Path localPath;
	private Path localPathTemp;

	public PhotoStorageLocal() {
		this(getDefault().getPath(System.getenv("USERPROFILE"), ".BrewerPhotos"));// System.getenv("HOME") MAC or LINUX
	}

	public PhotoStorageLocal(Path path) {
		this.localPath = path;
		this.localPathTemp = getDefault().getPath(this.localPath.toString(), ".brewerPhotosTemp");
		createFolder();
	}

	@Override
	public String saveTemporaryPhoto(MultipartFile[] files) {
		if (files != null && files.length > 0) {
			MultipartFile file = files[0];
			try {
				String photoName = renamePhotoName(file.getOriginalFilename());
				file.transferTo(new File(this.localPathTemp.toAbsolutePath().toString() + getDefault().getSeparator() + photoName));
				return photoName;
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException("Error save the image");
			}
		}
		return null;
	}

	@Override
	public byte[] getTemporaryPhoto(String name) {
		try {
			return Files.readAllBytes(this.localPathTemp.resolve(name));
		} catch (IOException e) {
			throw new RuntimeException("Error get temporary photo", e);
		}
	}
	
	@Override
	public byte[] getPhoto(String name) {
		try {
			return Files.readAllBytes(this.localPath.resolve(name));
		} catch (IOException e) {
			throw new RuntimeException("Error get photo", e);
		}
	}

	
	@Override
	public void moveToPermanentStorage(String photoName) {
		try {
			Files.move(this.localPathTemp.resolve(photoName), this.localPath.resolve(photoName));
			Thumbnails.of(this.localPath.resolve(photoName).toString()).size(40, 68).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("Error move photo", e);
		}
	}
	
	private void createFolder() {
		try {
			Files.createDirectories(this.localPath);
			Files.createDirectories(this.localPathTemp);
			if (logger.isDebugEnabled()) {
				logger.debug("Folder Created");
				logger.debug("localPath: " + this.localPath.toAbsolutePath());
				logger.debug("localPathTemp: " + this.localPathTemp.toAbsolutePath());
			}
		} catch (IOException e) {
			throw new RuntimeException("Error create local folder", e);
		}
	}

	private String renamePhotoName(String originPhotoName) {
		return UUID.randomUUID().toString() + originPhotoName;
	}

}
