-- ===== UTILISATEURS (pour se connecter) =====
INSERT INTO utilisateur (username, password, role, actif) VALUES
                                                              ('admin', '$2a$10$N.zmdr9k8mke5V7V4Y9pGe6sGJl6DsZGQV8KFMM0Bkr3pPLQ5Vhm', 'ADMIN', true),
                                                              ('secretaire', '$2a$10$N.zmdr9k8mke5V7V4Y9pGe6sGJl6DsZGQV8KFMM0Bkr3pPLQ5Vhm', 'SECRETAIRE', true),
                                                              ('docteur1', '$2a$10$N.zmdr9k8mke5V7V4Y9pGe6sGJl6DsZGQV8KFMM0Bkr3pPLQ5Vhm', 'MEDECIN', true);

-- (le mot de passe pour tous = "password123")

-- ===== MEDECINS =====
INSERT INTO medecin (nom, prenom, specialite, numero_ordre, telephone, email, actif) VALUES
                                                                                         ('Ben Ali', 'Mohamed', 'Cardiologie', 'MED001', '71000001', 'benali@cabinet.tn', true),
                                                                                         ('Trabelsi', 'Sonia', 'Pédiatrie', 'MED002', '71000002', 'trabelsi@cabinet.tn', true),
                                                                                         ('Gharbi', 'Karim', 'Généraliste', 'MED003', '71000003', 'gharbi@cabinet.tn', true);

-- ===== PATIENTS =====
INSERT INTO patient (cin, nom, prenom, date_naissance, telephone, email, antecedents) VALUES
                                                                                          ('12345678', 'Mansour', 'Ahmed', '1985-03-15', '20000001', 'ahmed@gmail.com', 'Diabète type 2'),
                                                                                          ('87654321', 'Bouazizi', 'Fatma', '1990-07-22', '20000002', 'fatma@gmail.com', 'Aucun'),
                                                                                          ('11223344', 'Chaari', 'Youssef', '1978-11-05', '20000003', 'youssef@gmail.com', 'Hypertension');