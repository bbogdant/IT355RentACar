package com.metropolitan.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transakcija")
public class Transakcija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transakcija_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rezervacija_id")
    private Rezervacija rezervacija;

    @Column(name = "datum_transakcije")
    private LocalDate datumTransakcije;

    @Column(name = "ukupan_iznos", precision = 10, scale = 2)
    private BigDecimal ukupanIznos;

    @Override
    public String toString() {
        return "Transakcija{" +
                "id=" + id +
                ", rezervacija=" + rezervacija +
                ", datumTransakcije=" + datumTransakcije +
                ", ukupanIznos=" + ukupanIznos +
                '}';
    }
}