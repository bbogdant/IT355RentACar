package com.metropolitan.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Column(name = "ime_role")
    private String imeRole;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", imeRole='" + imeRole + '\'' +
                '}';
    }
}