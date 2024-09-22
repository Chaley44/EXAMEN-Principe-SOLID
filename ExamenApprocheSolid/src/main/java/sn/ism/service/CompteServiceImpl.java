package sn.ism.service;

import org.springframework.stereotype.Service;
import sn.ism.entities.Compte;
import sn.ism.repository.CompteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;

    public CompteServiceImpl(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Override
    public Compte createCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    @Override
    public List<Compte> listAllComptes() {
        return compteRepository.findAll();
    }

    @Override
    public Optional<Compte> findCompteByEmail(String email) {
        return compteRepository.findByEmail(email);
    }

    @Override
    public void deleteCompte(Long id) {
        compteRepository.deleteById(id);
    }
}

