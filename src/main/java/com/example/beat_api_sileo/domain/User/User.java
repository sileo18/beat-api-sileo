    package com.example.beat_api_sileo.domain.User;

    import com.example.beat_api_sileo.domain.Beat.Beat;
    import com.example.beat_api_sileo.domain.Roles.Roles;
    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.Email;
    import lombok.AllArgsConstructor;
    import lombok.NoArgsConstructor;

    import java.util.*;


    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "usuario")
    public class User {

        @Id
        @GeneratedValue
        private UUID id;

        private String name;

        @Email
        private String email;

        private String password;

        private String surname;

        private String description;

        @Column(name = "birthdate")
        private Date birthDate;

        @Column(name = "pictureurl")
        private String profilePictureUrl;

        @OneToMany(mappedBy = "user")
        @JsonManagedReference
        private List<Beat> beats;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "usuario_roles",
                joinColumns = @JoinColumn(name = "usuario_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Roles> roles = new HashSet<>();


        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Date getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }

        public String getProfilePictureUrl() {
            return profilePictureUrl;
        }

        public void setProfilePictureUrl(String profilePictureUrl) {
            this.profilePictureUrl = profilePictureUrl;
        }

        public void setRoles(Set<Roles> roles) {
            this.roles = roles;
        }
        public Set<Roles> getRoles() {
            return roles;
        }
    }
