CREATE TABLE medecin (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nom VARCHAR(100) NOT NULL,
                         prenom VARCHAR(100) NOT NULL,
                         specialite VARCHAR(100) NOT NULL,
                         numero_ordre VARCHAR(50) UNIQUE NOT NULL,
                         telephone VARCHAR(20),
                         email VARCHAR(100),
                         actif BOOLEAN DEFAULT TRUE
);

CREATE TABLE patient (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         cin VARCHAR(20) UNIQUE NOT NULL,
                         nom VARCHAR(100) NOT NULL,
                         prenom VARCHAR(100) NOT NULL,
                         date_naissance DATE NOT NULL,
                         telephone VARCHAR(20),
                         email VARCHAR(100),
                         antecedents TEXT,
                         date_creation DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE rendez_vous (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             patient_id BIGINT NOT NULL,
                             medecin_id BIGINT NOT NULL,
                             date_heure DATETIME NOT NULL,
                             duree_minutes INT DEFAULT 30,
                             statut VARCHAR(20) DEFAULT 'PLANIFIE',
                             motif TEXT,
                             FOREIGN KEY (patient_id) REFERENCES patient(id),
                             FOREIGN KEY (medecin_id) REFERENCES medecin(id)
);

CREATE TABLE ordonnance (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            rendez_vous_id BIGINT UNIQUE NOT NULL,
                            date_emission DATE NOT NULL,
                            observations TEXT,
                            FOREIGN KEY (rendez_vous_id) REFERENCES rendez_vous(id)
);

CREATE TABLE ligne_medicament (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  ordonnance_id BIGINT NOT NULL,
                                  nom_medicament VARCHAR(200) NOT NULL,
                                  posologie VARCHAR(200),
                                  duree VARCHAR(100),
                                  FOREIGN KEY (ordonnance_id) REFERENCES ordonnance(id)
);

CREATE TABLE utilisateur (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             username VARCHAR(50) UNIQUE NOT NULL,
                             password VARCHAR(255) NOT NULL,
                             role VARCHAR(20) NOT NULL,
                             actif BOOLEAN DEFAULT TRUE
);