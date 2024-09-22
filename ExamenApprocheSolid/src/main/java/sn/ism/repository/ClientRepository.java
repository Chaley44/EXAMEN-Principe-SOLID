package sn.ism.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.ism.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
	List<Client> findByCompteIsNull();
    List<Client> findByCompteIsNotNull();
    Optional<Client> findByTelephone(String telephone);
}
