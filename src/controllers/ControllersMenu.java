/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.metier.Authentification;
import views.VueAuthentification;
import views.VueDetailPracticien;
import views.VueMenu;
import views.VueRapportVisite;
import views.VueVisiteur;

/**
 *
 * @author clement
 */
public class ControllersMenu implements ActionListener {
    private VueMenu vue;
    private VueVisiteur vue2;
    private VueRapportVisite vue3;    
    private VueDetailPracticien vue4;
    private String login;
    private ControllersAuthentification cAuth;
    private VueAuthentification vueAuth;
    
    public ControllersMenu(VueMenu vue, String login) {
        this.vue = vue;        
        this.login = login;
        afficherNomBienvenue();
        vue.getjButtonCompteRendu().addActionListener(this); 
        vue.getjButtonVisiteurs().addActionListener(this);
        vue.getjButtonPracticien().addActionListener(this);
        vue.getjButtonDeconnexion().addActionListener(this);
    }
    
    public void afficherNomBienvenue() {
        vue.getjLabelNomUtilisateur().setText(login);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == vue.getjButtonVisiteurs()) {
            vue2 = new VueVisiteur();
            ControllersVisiteurs controllers = new ControllersVisiteurs(vue2, login);
            vue2.setVisible(true);
            vue.hide();
            
        }
        
        if (source == vue.getjButtonCompteRendu()) {            
            vue3 = new VueRapportVisite();            
            ControllersRapportVisite controllers = new ControllersRapportVisite(vue3, login);
            vue3.setVisible(true);
            vue.hide();
        }
        
        if (source == vue.getjButtonPracticien()) {            
            vue4 = new VueDetailPracticien();
            int z = 0;
            ControllersDetailPracticien controllers = new ControllersDetailPracticien(vue4, login, z);
            vue4.setVisible(true);
            vue.hide();
        }
        
        if (source == vue.getjButtonDeconnexion()) {
            vueAuth = new VueAuthentification();
            ControllersAuthentification controllers = new ControllersAuthentification(vueAuth);
            vueAuth.setVisible(true);
            vue.hide();
        }
    }
    
    
}
