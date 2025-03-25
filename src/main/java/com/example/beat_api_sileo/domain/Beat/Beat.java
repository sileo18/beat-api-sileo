package com.example.beat_api_sileo.domain.Beat;

import com.example.beat_api_sileo.domain.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "beats")
public class Beat {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private int BPM;
    private Key key;
    private String imageUrl;
    private String audioUrl;
    private String genre;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
