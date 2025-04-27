package com.example.beat_api_sileo.domain.UserRole;


import com.example.beat_api_sileo.domain.Roles.Roles;
import com.example.beat_api_sileo.domain.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_roles")
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    @EmbeddedId
    private UserRoleKey id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Roles role;

    public UserRoleKey getId() {
        return id;
    }

    public void setId(UserRoleKey id) {
        this.id = id;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}