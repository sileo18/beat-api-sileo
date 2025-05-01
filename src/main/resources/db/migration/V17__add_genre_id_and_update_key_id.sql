-- Adiciona a coluna genre_id à tabela beats
ALTER TABLE beats
ADD COLUMN genre_id INTEGER;

-- Define key_id como NOT NULL
ALTER TABLE beats
ALTER COLUMN key_id SET NOT NULL;

-- Adiciona a restrição de chave estrangeira para genre_id
ALTER TABLE beats
ADD CONSTRAINT fk_beats_genre FOREIGN KEY (genre_id) REFERENCES genre (id);