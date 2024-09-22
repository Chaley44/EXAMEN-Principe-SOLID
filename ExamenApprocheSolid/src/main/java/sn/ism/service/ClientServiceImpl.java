package sn.ism.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import sn.ism.entities.Client;
import sn.ism.entities.Compte;
import sn.ism.entities.Dette;
import sn.ism.entities.Paiement;
import sn.ism.repository.ClientRepository;
import sn.ism.repository.CompteRepository;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CompteRepository compteRepository;

    public ClientServiceImpl(ClientRepository clientRepository, CompteRepository compteRepository) {
        this.clientRepository = clientRepository;
        this.compteRepository = compteRepository;
    }

    @Override
    public void createClient(String surname, String telephone, String address, String email, String login, String password, String photoUrl) {

        Optional<Client> existingClient = clientRepository.findByTelephone(telephone);
        if (existingClient.isPresent()) {
            System.out.println("Le client avec le téléphone " + telephone + " existe déjà.");
        } else {
            Client client = new Client();
            client.setSurname(surname);
            client.setTelephone(telephone);
            client.setAdresse(address);

            if (email != null && login != null && password != null) {
                
                Optional<Compte> existingAccount = compteRepository.findByEmail(email);
                if (existingAccount.isPresent()) {
                    client.setCompte(existingAccount.get());
                    System.out.println("Compte existant associé au client.");
                } else {
                    Compte newAccount = new Compte();
                    newAccount.setEmail(email);
                    newAccount.setLogin(login);
                    newAccount.setPassword(password);
                    newAccount.setPhotoUrl(photoUrl);

                    compteRepository.save(newAccount);
                    client.setCompte(newAccount);
                    System.out.println("Nouveau compte créé et associé au client.");
                }
            }

            clientRepository.save(client);
            System.out.println("Client créé avec succès : " + surname);
        }
    }

    @Override
    public List<Client> listClients(boolean withAccount) {
       
        if (withAccount) {
            
            return clientRepository.findByCompteIsNotNull();
        } else {
            
            return clientRepository.findByCompteIsNull();
        }
    }

    @Override
    public Optional<Client> findClientByTelephone(String telephone) {
        
        Optional<Client> clientOpt = clientRepository.findByTelephone(telephone);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            System.out.println("Client trouvé : " + client.getSurname());
            return Optional.of(client);
        } else {
            System.out.println("Client introuvable avec le téléphone : " + telephone);
            return null;
        }
    }

    @Override
    public void listClientDebts(String telephone) {
        
        Optional<Client> clientOpt = clientRepository.findByTelephone(telephone);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            
            client.getDettes().stream()
                  .filter(debt -> !debt.isEstSoldee())
                  .forEach(debt -> System.out.println("Dette : " + debt.getMontant() + " Date : " + debt.getDate()));
        } else {
            System.out.println("Client introuvable avec le téléphone : " + telephone);
        }
    }

	

	@Override
	public List<Paiement> listPayments(String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

