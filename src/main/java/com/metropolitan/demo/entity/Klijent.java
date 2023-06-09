package com.metropolitan.demo.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "klijent")
public class Klijent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "klijent_id", nullable = false)
    private Integer id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "password")
    private String password;

    @Column(name = "adresa")
    private String adresa;

    @Column(name = "broj_telefona", length = 20)
    private String brojTelefona;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "klijent")
    private Set<Rezervacija> rezervacijas = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "Klijent{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", password='" + password + '\'' +
                ", adresa='" + adresa + '\'' +
                ", brojTelefona='" + brojTelefona + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}