package com.example.beat_api_sileo.domain.Beat;

public enum Genre {

    HIP_HOP("Hip Hop"),
    RNB("R&B"),
    POP("Pop"),
    ROCK("Rock"),
    JAZZ("Jazz"),
    CLASSICAL("Classical"),
    ELECTRONIC("Electronic"),
    REGGAE("Reggae"),
    COUNTRY("Country"),
    BLUES("Blues"),
    FOLK("Folk"),
    METAL("Metal"),
    PUNK("Punk"),
    FUNK("Funk"),
    DISCO("Disco");

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
