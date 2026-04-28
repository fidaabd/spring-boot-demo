package com.fst.cabinet.dto;

import lombok.Data;

@Data
public class RdvPublicDto {
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String motif;
    private String dateHeure;
}