package com.metropolitan.demo.entity;

import javax.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role {

    public static final String USER = "User";
    public static final String ADMIN = "Admin";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Column(name = "ime_role")
    private String imeRole;

    @OneToMany(mappedBy = "role")
    private Set<Klijent> klijents = new LinkedHashSet<>();

    @Override
    public String toString() {
        return imeRole;
    }
}