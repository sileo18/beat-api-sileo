CREATE TABLE beats_genre (
    beats_id UUID,
    genre_id INTEGER,
    CONSTRAINT fk_beats_genre_beats FOREIGN KEY(beats_id) REFERENCES beats(id),
    CONSTRAINT fk_beats_genre_genre FOREIGN KEY(genre_id) REFERENCES genre(id)
);