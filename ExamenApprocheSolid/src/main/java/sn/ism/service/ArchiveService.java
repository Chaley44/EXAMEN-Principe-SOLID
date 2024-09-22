package sn.ism.service;

import java.util.List;

import sn.ism.entities.Dette;

public interface ArchiveService {
    void archiveSettledDebts(List<Dette> dette);
}

