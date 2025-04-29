CREATE TABLE beats_key (
    beats_id UUID,
    key_id INTEGER,
    CONSTRAINT fk_beats_key_beats FOREIGN KEY(beats_id) REFERENCES beats(id),
    CONSTRAINT fk_beats_key_key FOREIGN KEY(key_id) REFERENCES key(id)
);