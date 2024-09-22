package sn.ism;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sn.ism.service.ClientService;
import sn.ism.service.CompteService;
import sn.ism.service.DetteService;
import sn.ism.service.PhotoService;

@Component
public class MenuConsole {

    @Autowired
    private ClientService clientService;  
    @Autowired
    private DetteService debtService;     
    @Autowired
    private PhotoService photoService; 
    @Autowired
    private CompteService compteService; 

    private Scanner scanner;

    public MenuConsole() {
        scanner = new Scanner(System.in);
    }

    // Menu principal
    public void start() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Utilisateur Boutiquier");
            System.out.println("2. Utilisateur Client");
            System.out.println("0. Quitter");
            System.out.print("Votre choix: ");
            choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    showBoutiquierMenu();
                    break;
                case 2:
                    showClientMenu();
                    break;
                case 0:
                    System.out.println("Merci d'avoir utilisé l'application !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    // Menu pour le rôle Boutiquier
    private void showBoutiquierMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- Menu Boutiquier ---");
            System.out.println("1. Créer un client");
            System.out.println("2. Lister les clients");
            System.out.println("3. Rechercher un client par téléphone");
            System.out.println("4. Créer une dette");
            System.out.println("5. Enregistrer un paiement");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix: ");
            choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    createClient();
                    break;
                case 2:
                    listClients();
                    break;
                case 3:
                    searchClientByPhone();
                    break;
                case 4:
                    createDebt();
                    break;
                case 5:
                    recordPayment();
                    break;
                case 0:
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    // Menu pour le rôle Client
    private void showClientMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- Menu Client ---");
            System.out.println("1. Lister mes dettes non soldées");
            System.out.println("2. Voir les paiements effectués");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    listUnpaidDebts();
                    break;
                case 2:
                    viewPayments();
                    break;
                case 0:
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    // créer un client
    private void createClient() {
        System.out.print("Entrez le nom du client: ");
        String surname = scanner.nextLine();
        System.out.print("Entrez le téléphone du client: ");
        String telephone = scanner.nextLine();
        System.out.print("Entrez l'adresse du client: ");
        String address = scanner.nextLine();
        
        System.out.print("Le client a-t-il un compte existant ? (oui/non): ");
        String hasAccount = scanner.nextLine();
        
        if (hasAccount.equalsIgnoreCase("oui")) {
            System.out.print("Entrez l'email associé au compte: ");
            String email = scanner.nextLine();
            var compte = compteService.findCompteByEmail(email);
            if (compte.isPresent()) {
                clientService.createClient(surname, telephone, address, email, null, null, null);
                System.out.println("Client créé et associé à un compte existant.");
            } else {
                System.out.println("Compte introuvable avec cet email.");
            }
        } else {
            System.out.print("Entrez l'email du nouveau compte: ");
            String email = scanner.nextLine();
            System.out.print("Entrez le login: ");
            String login = scanner.nextLine();
            System.out.print("Entrez le mot de passe: ");
            String password = scanner.nextLine();
            System.out.print("Entrez l'URL de la photo: ");
            String photoUrl = scanner.nextLine();

            clientService.createClient(surname, telephone, address, email, login, password, photoUrl);
            System.out.println("Client et compte créés avec succès !");
        }
    }


    // lister les clients
    private void listClients() {
        System.out.print("Afficher uniquement les clients avec comptes ? (true/false) : ");
        boolean avecCompte = scanner.nextBoolean();
        scanner.nextLine();
        clientService.listClients(avecCompte).forEach(System.out::println);
    }

    // rechercher un client par téléphone
    private void searchClientByPhone() {
        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine();
        clientService.findClientByTelephone(telephone).ifPresent(System.out::println);
    }

    // créer une dette
    private void createDebt() {
        System.out.print("Entrez le téléphone du client: ");
        String phone = scanner.nextLine();
        System.out.print("Entrez le montant de la dette: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 

        debtService.createDebt(phone, amount);
        System.out.println("Dette créée avec succès !");
    }

    private void recordPayment() {
        System.out.print("Téléphone du client : ");
        String telephone = scanner.nextLine();
        System.out.print("Montant du paiement : ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        debtService.recordPayment(telephone, amount);
    }

    private void listUnpaidDebts() {
        System.out.print("Téléphone du client : ");
        String telephone = scanner.nextLine();
        clientService.listClientDebts(telephone);
    }

    private void viewPayments() {
        System.out.print("Téléphone du client : ");
        String telephone = scanner.nextLine();
        clientService.listPayments(telephone).forEach(System.out::println);
    }
}

