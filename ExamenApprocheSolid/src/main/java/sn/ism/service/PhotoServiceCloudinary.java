package sn.ism.service;

import java.io.File;

import org.springframework.stereotype.Service;

import sn.ism.entities.Client;

@Service
public class PhotoServiceCloudinary implements PhotoService {

	@Override
	public String storePhoto(Client client, File photo) {
		// Logique pour stocker la photo dans Cloudinary
        return "cloudinary_url";
	}
}

