package sn.ism.service;

import java.util.List;
import java.util.Optional;

import sn.ism.entities.Compte;

public interface CompteService {
    Compte createCompte(Compte compte);
    List<Compte> listAllComptes();
    Optional<Compte> findCompteByEmail(String email);
    void deleteCompte(Long id);
}
