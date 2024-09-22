package sn.ism.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.ism.entities.Client;
import sn.ism.entities.Dette;

@Repository
public interface DetteRepository extends JpaRepository<Dette, Long> {

	//List<Dette> findByClientIdAndEstSoldeeFalse(Long clientId);
    
    List<Dette> findByClientAndPaidIsFalse(Client client);

    Optional<Dette> findFirstByClientAndPaidIsFalse(Client client);
}