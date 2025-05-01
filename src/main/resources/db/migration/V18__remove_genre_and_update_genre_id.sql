-- Remove a coluna genre da tabela beats
ALTER TABLE beats
DROP COLUMN genre;

-- Define genre_id como NOT NULL
ALTER TABLE beats
ALTER COLUMN genre_id SET NOT NULL;