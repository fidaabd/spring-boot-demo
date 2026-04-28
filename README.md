# 🏥 Gestion d'un Cabinet Médical

## Description
Application web de gestion d'un cabinet médical développée avec Spring Boot 3, Thymeleaf et MySQL 8.

## 👥 Équipe
- Fidaa Abdennabi — Backend (Entités, Repositories, Services, Security)
- Ghofrane Boumaiza — Frontend (Vues Thymeleaf, Navbar + UI ,Dashboard)
- Ikram mansour Frontend (Controllers MVC ,login , Dashboard)


## 🛠️ Technologies
- Spring Boot 3
- Thymeleaf 3
- MySQL 8
- Spring Security
- Flyway
- Lombok
- Bootstrap 5

## 📦 Prérequis
- Java 17
- Maven
- MySQL 8

## 🚀 Installation
1. Cloner le projet :
   git clone https://github.com/fidaabd/spring-boot-demo.git

2. Créer la base de données MySQL :
   CREATE DATABASE cabinet_medical;

3. Configurer application.properties :
   spring.datasource.password=123456

4. Lancer l'application :
   mvn spring-boot:run

## 🔐 Connexion
| Utilisateur | Mot de passe | Rôle |
|-------------|-------------|------|
| admin | fida123 | ADMIN |
| secretaire | fida123 | SECRETAIRE |
| docteur1 | fida123 | MEDECIN |

## 📅 Sprints
### Sprint 1 — Fondations
- Scripts Flyway V1/V2
- Entités JPA
- Spring Security

### Sprint 2 — CRUD
- CRUD Patients & Médecins
- Recherche patients
- Vues Thymeleaf

## 📊 Fonctionnalités
- ✅ Login avec rôles
- ✅ Dashboard
- ✅ CRUD Patients
- ✅ CRUD Médecins
- ✅ Recherche patients
- ✅ Fiche patient
