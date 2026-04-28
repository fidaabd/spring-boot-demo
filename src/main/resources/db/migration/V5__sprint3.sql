CREATE TABLE formation (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           diplome VARCHAR(255),
                           etablissement VARCHAR(255),
                           annee INT,
                           medecin_id BIGINT,
                           FOREIGN KEY (medecin_id) REFERENCES medecin(id)
);

CREATE TABLE experience (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            poste VARCHAR(255),
                            etablissement VARCHAR(255),
                            git status              annee_debut INT,
                            annee_fin INT,
                            medecin_id BIGINT,
                            FOREIGN KEY (medecin_id) REFERENCES medecin(id)
);