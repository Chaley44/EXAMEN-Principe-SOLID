package sn.ism.service;

import sn.ism.entities.Dette;
import sn.ism.entities.Paiement;


import java.util.List;

public interface DetteService {

	void createDebt(String telephone, double montant);

    void recordPayment(String telephone, double montant); 

    List<Dette> listUnpaidDebts(String phone);

    List<Paiement> listPayments(String phone);
}

