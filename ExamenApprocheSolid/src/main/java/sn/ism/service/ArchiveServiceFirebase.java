package sn.ism.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sn.ism.entities.Dette;

@Service
public class ArchiveServiceFirebase implements ArchiveService {
    @Override
    public void archiveSettledDebts(List<Dette> dette) {
        // Logique pour archiver les dettes dans Firebase
    }
}
