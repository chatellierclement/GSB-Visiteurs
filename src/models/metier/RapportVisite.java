/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.metier;

import java.util.Date;

/**
 *
 * @author clement
 */
public class RapportVisite {
    private int rapport;
    private Praticien unPraticien;
    private Date date;
    private String motif;
    private String bilan;

    public int getRapport() {
        return rapport;
    }

    public Praticien getUnPraticien() {
        return unPraticien;
    }

    public Date getDate() {
        return date;
    }

    public String getMotif() {
        return motif;
    }

    public String getBilan() {
        return bilan;
    }
    

    

    public RapportVisite(int rapport, Praticien unPracticien, Date date, String motif, String bilan) {
        this.rapport = rapport;
        this.unPraticien = unPracticien;
        this.date = date;
        this.motif = motif;
        this.bilan = bilan;
    }

    
}
