package com.example.beat_api_sileo.domain.Beat;

import com.example.beat_api_sileo.domain.Genre.Genre;
import com.example.beat_api_sileo.domain.Key.Key;
import com.example.beat_api_sileo.domain.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "beats")
public class Beat {

    public static Beat create(String name, int BPM) {
        Beat beat = new Beat();
        beat.setName(name);
        beat.setBPM(BPM);
        return beat;
    }

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @Column(name = "bpm")
    private int bpm;

    @ManyToOne
    @JoinColumn(name = "key_id", nullable = false)
    private Key key;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "audio_url")
    private String audioUrl;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }


    @ManyToMany
    @JoinTable(name = "beats_genre",
            joinColumns = @JoinColumn(name = "beats_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genre;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

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

    public int getBPM() {
        return bpm;
    }

    public void setBPM(int BPM) {
        this.bpm = BPM;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genres) {
        this.genre = genres;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
