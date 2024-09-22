package sn.ism.entities;

import lombok.Data;

@Data
public class Compte {
    private Long id;
    private String email;
    private String login;
    private String password;
    private String photoUrl;
    private Client client;
    
}
