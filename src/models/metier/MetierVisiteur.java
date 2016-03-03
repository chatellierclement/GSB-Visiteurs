
package models.metier;

import java.sql.Date;


public class MetierVisiteur {
    private String matricule;
    private String nom;
    private String prenom;
    private String adresse; 
    private String cp;
    private String ville;
    private Date date;
    private Secteur secNom;
    private Labo labNom;

    public MetierVisiteur(String matricule, String nom, String prenom, String adresse, String cp, String ville, Date date, Secteur secNom, Labo labNom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public Date getDate() {
        return date;
    }

    public Secteur getSecNom() {
        return secNom;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }

    public Labo getLabNom() {
        return labNom;
    }

    public MetierVisiteur(String matricule, String nom, String prenom, String adresse, String cp, String ville, Date date, Secteur secNom, String labCode) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.date = date;
        this.secNom = secNom;
        this.labNom = labNom;
    }
    
}
