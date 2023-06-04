package com.metropolitan.demo.entity;

import javax.persistence.*;
import lombok.Data;


import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
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

    @Column(name = "slika", length = 1000)
    private String slika;

    @Column(name = "boja")
    private String boja;

    @Column(name = "tip")
    private String tip;

    @OneToMany(mappedBy = "vozilo")
    private Set<Rezervacija> rezervacijas = new LinkedHashSet<>();





    @Override
    public String toString() {
        return "Vozilo{" +
                "id=" + id +
                ", marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", godinaProizvodnje=" + godinaProizvodnje +
                ", registarskaTablica='" + registarskaTablica + '\'' +
                ", cenaPoDanu=" + cenaPoDanu +
                ", rezervacijas=" + rezervacijas +
                ", slikas=" + slika +
                '}';
    }
}