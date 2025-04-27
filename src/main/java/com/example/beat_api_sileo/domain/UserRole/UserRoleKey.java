package com.example.beat_api_sileo.domain.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.UUID;

@Embeddable
public class UserRoleKey {
    @Column(name = "usuario_id")
    private UUID usuarioId;
    @Column(name = "role_id")
    private UUID roleId;

    public UserRoleKey() {
    }

    public UserRoleKey(UUID usuarioId, UUID roleId) {
        this.usuarioId = usuarioId;
        this.roleId = roleId;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleKey)) return false;
        UserRoleKey that = (UserRoleKey) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, roleId);
    }
}