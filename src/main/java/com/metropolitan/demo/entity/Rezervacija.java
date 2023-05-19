package com.metropolitan.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "rezervacija")
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rezervacija_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "klijent_id")
    private Klijent klijent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vozilo_id")
    private Vozilo vozilo;

    @Column(name = "datum_rezervacije")
    private LocalDate datumRezervacije;

    @Column(name = "pocetni_datum")
    private LocalDate pocetniDatum;

    @Column(name = "krajnji_datum")
    private LocalDate krajnjiDatum;

    @OneToMany(mappedBy = "rezervacija")
    private Set<Transakcija> transakcijas = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "Rezervacija{" +
                "id=" + id +
                ", klijent=" + klijent +
                ", vozilo=" + vozilo +
                ", datumRezervacije=" + datumRezervacije +
                ", pocetniDatum=" + pocetniDatum +
                ", krajnjiDatum=" + krajnjiDatum +
                ", transakcijas=" + transakcijas +
                '}';
    }
}