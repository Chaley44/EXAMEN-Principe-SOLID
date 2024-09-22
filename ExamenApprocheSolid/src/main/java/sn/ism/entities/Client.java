package sn.ism.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surname;
    private String telephone;
    private String adresse;
    private Compte compte;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Dette> dettes = new ArrayList<>();

}
