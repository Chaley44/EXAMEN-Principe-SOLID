package sn.ism.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sn.ism.entities.Client;
import sn.ism.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
    @Autowired
    private ClientService clientService;

    @PostMapping
//    public ResponseEntity<Client> createClient(@RequestBody Client client) {
//        return ResponseEntity.ok(clientService.createClient(client));
//    }

    @GetMapping
    public ResponseEntity<List<Client>> listClients(@RequestParam boolean withAccount) {
        return ResponseEntity.ok(clientService.listClients(withAccount));
    }
}

