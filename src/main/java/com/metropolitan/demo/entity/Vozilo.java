package com.metropolitan.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "vozilo")
public class Vozilo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vozilo_id", nullable = false)
    private Integer id;

    @Column(name = "marka")
    private String marka;

    @Column(name = "model")
    private String model;

    @Column(name = "godina_proizvodnje")
    private Integer godinaProizvodnje;

    @Column(name = "registarska_tablica", length = 20)
    private String registarskaTablica;

    @Column(name = "cena_po_danu", precision = 10, scale = 2)
    private BigDecimal cenaPoDanu;

    @Override
    public String toString() {
        return "Vozilo{" +
                "id=" + id +
                ", marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", godinaProizvodnje=" + godinaProizvodnje +
                ", registarskaTablica='" + registarskaTablica + '\'' +
                ", cenaPoDanu=" + cenaPoDanu +
                '}';
    }
}