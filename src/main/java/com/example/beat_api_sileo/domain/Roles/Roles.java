package com.example.beat_api_sileo.domain.Roles;

import jakarta.persistence.*;


import java.util.UUID;


@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    private RoleEnum name;

    public Roles() {
    }

    public Roles(RoleEnum name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }
}
