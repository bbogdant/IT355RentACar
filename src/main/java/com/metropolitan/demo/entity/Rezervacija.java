package com.metropolitan.demo.entity;

import javax.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "datum_rezervacije")
    private LocalDate datumRezervacije;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "pocetni_datum")
    private LocalDate pocetniDatum;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "krajnji_datum")
    private LocalDate krajnjiDatum;

    @Column(name = "placeno")
    private Boolean placeno;

    @OneToMany(mappedBy = "rezervacija")
    private Set<Transakcija> transakcijas = new LinkedHashSet<>();


}