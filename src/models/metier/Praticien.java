/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.metier;

/**
 *
 * @author clement
 */
public class Praticien {
    private String matricule;
    private String nom;
    private String prenom;
    private String adresse; 
    private String cp;
    private String ville;
    private String coef;
    private LieuExercice typeCode;

    public Praticien(String matricule, String nom, String prenom, String adresse, String cp, String ville, String coef, LieuExercice typeCode) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.coef = coef;
        this.typeCode = typeCode;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCp() {
        return cp;
    }

    public String getVille() {
        return ville;
    }

    public String getCoef() {
        return coef;
    }

    public LieuExercice getTypeCode() {
        return typeCode;
    }
    
    @Override
    public String toString() {
        return nom + " " + prenom;
    }
    
}
