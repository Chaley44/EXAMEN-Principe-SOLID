package sn.ism.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ism.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {
    Optional<Compte> findByEmail(String email);
}
