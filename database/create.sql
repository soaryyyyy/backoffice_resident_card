-- Base de données Demande de Visa

-- Tables de référence
CREATE TABLE situation_familiale (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

CREATE TABLE nationalite (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE lieu (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE categorie_demande (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE type_visa (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE type_piece (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

-- Tables principales
CREATE TABLE personne (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    nom_jeune_fille VARCHAR(100),
    date_naissance DATE NOT NULL,
    id_situation_familiale INT REFERENCES situation_familiale(id),
    id_nationalite INT REFERENCES nationalite(id),
    adresse TEXT,
    profession VARCHAR(100),
    email VARCHAR(150),
    telephone VARCHAR(30)
);

CREATE TABLE passeport (
    id SERIAL PRIMARY KEY,
    numero VARCHAR(50) NOT NULL UNIQUE,
    date_delivrance DATE NOT NULL,
    date_expiration DATE NOT NULL,
    id_personne INT NOT NULL REFERENCES personne(id)
);

CREATE TABLE visa (
    id SERIAL PRIMARY KEY,
    reference VARCHAR(50) NOT NULL,
    date_entree DATE NOT NULL,
    id_lieu_entree INT REFERENCES lieu(id),
    date_expiration DATE NOT NULL,
    id_personne INT NOT NULL REFERENCES personne(id)
);

CREATE TABLE demande_titre (
    id SERIAL PRIMARY KEY,
    id_personne INT NOT NULL REFERENCES personne(id),
    id_passeport INT NOT NULL REFERENCES passeport(id),
    id_visa INT NOT NULL REFERENCES visa(id),
    id_type_visa INT NOT NULL REFERENCES type_visa(id),
    id_categorie_demande INT NOT NULL REFERENCES categorie_demande(id),
    status VARCHAR(20) DEFAULT 'EN_COURS',
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE piece (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(150) NOT NULL,
    id_type_piece INT REFERENCES type_piece(id),
    commune BOOLEAN DEFAULT FALSE
);

CREATE TABLE piece_type_visa (
    id SERIAL PRIMARY KEY,
    id_piece INT NOT NULL REFERENCES piece(id),
    id_type_visa INT NOT NULL REFERENCES type_visa(id)
);

CREATE TABLE demande_piece (
    id SERIAL PRIMARY KEY,
    id_demande_titre INT NOT NULL REFERENCES demande_titre(id),
    id_piece INT NOT NULL REFERENCES piece(id),
    fourni BOOLEAN DEFAULT FALSE,
    valide BOOLEAN DEFAULT FALSE,
    date_depot DATE
);

-- Insertions de base
INSERT INTO situation_familiale (libelle) VALUES 
('Célibataire'), ('Marié(e)'), ('Divorcé(e)'), ('Veuf/Veuve');

INSERT INTO categorie_demande (libelle) VALUES 
('NOUVEAU_TITRE'), ('RENOUVELLEMENT'), ('DUPLICATA');