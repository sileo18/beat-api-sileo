CREATE TABLE beats (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    bpm INT NOT NULL,
    key VARCHAR(20) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    audio_url VARCHAR(255) NOT NULL,
    genre VARCHAR(100) NOT NULL,
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES usuario(id)
);