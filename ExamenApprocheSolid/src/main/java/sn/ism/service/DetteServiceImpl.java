package sn.ism.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import sn.ism.entities.Client;
import sn.ism.entities.Dette;
import sn.ism.entities.Paiement;
import sn.ism.repository.ClientRepository;
import sn.ism.repository.DetteRepository;
import sn.ism.repository.PaiementRepository;
import sn.ism.service.DetteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DetteServiceImpl implements DetteService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DetteRepository debtRepository;

    @Autowired
    private PaiementRepository paymentRepository;

    @Override
    public void createDebt(String telephone, double montant) {
        Optional<Client> clientOpt = clientRepository.findByTelephone(telephone);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            
            Dette dette = new Dette();
            dette.setMontant(montant);
            dette.setClient(client);
            dette.setDate(LocalDate.now()); 
            dette.setEstSoldee(false);            

            debtRepository.save(dette);
            System.out.println("Dette créée avec succès pour le client: " + client.getSurname());
        } else {
            System.out.println("Client introuvable avec le téléphone : " + telephone);
        }
    }

    @Override
    public void recordPayment(String telephone, double montant) {

    	Optional<Client> clientOpt = clientRepository.findByTelephone(telephone);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();

            Optional<Dette> debtOpt = debtRepository.findFirstByClientAndPaidIsFalse(client);
            if (debtOpt.isPresent()) {
                Dette dette = debtOpt.get();

                Paiement payment = new Paiement();
                payment.setMontant(montant);
                payment.setDate(LocalDate.now());
                payment.setDette(dette);

                paymentRepository.save(payment);

                if (dette.getMontant() <= montant) {
                    dette.setEstSoldee(false); 
                }
                debtRepository.save(dette);

                System.out.println("Paiement enregistré pour le client : " + client.getSurname());
            } else {
                System.out.println("Aucune dette non soldée trouvée pour le client : " + client.getSurname());
            }
        } else {
            System.out.println("Client introuvable avec le téléphone : " + telephone);
        }
    }

    @Override
    public List<Dette> listUnpaidDebts(String telephone) {
        Optional<Client> clientOpt = clientRepository.findByTelephone(telephone);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();

            return debtRepository.findByClientAndPaidIsFalse(client);
        } else {
            System.out.println("Client introuvable avec le téléphone : " + telephone);
            return List.of();
        }
    }

    @Override
    public List<Paiement> listPayments(String telephone) {
        Optional<Client> clientOpt = clientRepository.findByTelephone(telephone);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();

            return paymentRepository.findByDebtClient(client);
        } else {
            System.out.println("Client introuvable avec le téléphone : " + telephone);
            return List.of();
        }
    }
}

