package sn.ism.service;

import java.io.File;

import org.springframework.stereotype.Service;

import sn.ism.entities.Client;

@Service
public class PhotoServiceFireBase implements PhotoService {

	@Override
	public String storePhoto(Client client, File photo) {
		// Logique pour stocker la photo dans Cloudinary
        return "fireBase_url";
	}
}

