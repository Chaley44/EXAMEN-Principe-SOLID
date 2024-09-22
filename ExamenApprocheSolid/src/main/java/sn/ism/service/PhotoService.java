package sn.ism.service;

import java.io.File;

import sn.ism.entities.Client;

public interface PhotoService {
    String storePhoto(Client client, File photo);
}

