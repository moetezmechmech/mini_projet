package com.moetez.formateur.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormateurDto {
    private Long id;
    private String nom;
    private String prenom;
    private String codeCours;
    private String nomCours;
    private String email;
    private String mobileNumber;
    private boolean communicationDone;
}