package sn.ism.service;

import java.util.List;
import java.util.Optional;

import sn.ism.entities.Client;
import sn.ism.entities.Dette;
import sn.ism.entities.Paiement;

public interface ClientService {
	void createClient(String surname, String telephone, String address, String email, String login, String password, String photoUrl);
    //Client createClient(Client client);
    List<Client> listClients(boolean avecCompte);
    Optional<Client> findClientByTelephone(String telephone);
    
    void listClientDebts(String phone);

    List<Paiement> listPayments(String phone);
}
