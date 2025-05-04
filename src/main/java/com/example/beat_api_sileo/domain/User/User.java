    package com.example.beat_api_sileo.domain.User;

    import com.example.beat_api_sileo.domain.Beat.Beat;
    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.Email;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.NoArgsConstructor;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;


    import java.time.LocalDate;
    import java.time.LocalDateTime;
    import java.util.*;



    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "usuario")
    public class User implements UserDetails {

        public static User create(String name, String surname, String email, String password) {
            User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPassword(password);
            return user;
        }

        @Id
        @GeneratedValue
        private UUID id;

        private String name;

        @Email
        private String email;

        private String password;

        private String surname;

        @Column(length = 255)
        private String description;

        @Column(name = "birthdate")
        @Temporal(TemporalType.DATE)
        private LocalDate birthDate;

        @Column(name = "pictureurl")
        private String profilePictureUrl;

        @OneToMany(mappedBy = "user")
        @JsonManagedReference
        private List<Beat> beats;

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

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return null;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
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

        public LocalDate getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
        }

        public String getProfilePictureUrl() {
            return profilePictureUrl;
        }

        public void setProfilePictureUrl(String profilePictureUrl) {
            this.profilePictureUrl = profilePictureUrl;
        }


    }
