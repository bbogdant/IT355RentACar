package com.metropolitan.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rezervacija")
public class Rezervacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rezervacija_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "klijent_id")
    private Klijent klijent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vozilo_id")
    private Vozilo vozilo;

    @Column(name = "datum_rezervacije")
    private LocalDate datumRezervacije;

    @Column(name = "pocetni_datum")
    private LocalDate pocetniDatum;

    @Column(name = "krajnji_datum")
    private LocalDate krajnjiDatum;

    @Override
    public String toString() {
        return "Rezervacija{" +
                "id=" + id +
                ", klijent=" + klijent +
                ", vozilo=" + vozilo +
                ", datumRezervacije=" + datumRezervacije +
                ", pocetniDatum=" + pocetniDatum +
                ", krajnjiDatum=" + krajnjiDatum +
                '}';
    }
}