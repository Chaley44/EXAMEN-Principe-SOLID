package sn.ism.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.ism.entities.Client;
import sn.ism.entities.Paiement;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    // Trouver tous les paiements d'une dette spécifique
    //List<Paiement> findByDetteId(Long detteId);
	
	List<Paiement> findByDebtClient(Client client);
}
