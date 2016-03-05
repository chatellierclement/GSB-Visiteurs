/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import models.metier.MetierVisiteur;
import ppe_gsb.VueVisiteur;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.daos.Dao;

/**
 *
 * @author btssio
 */
public class ControllersVisiteurs implements ActionListener {
    private VueVisiteur vue;
    private List<MetierVisiteur> lesVisiteurs;
    private MetierVisiteur unVisiteur;
    

    public ControllersVisiteurs(VueVisiteur vue) {
        this.vue = vue;
        afficherLesVisiteurs();
        vue.getjButtonOk().addActionListener(this);
        vue.getjButtonSuivant().addActionListener(this);        
        vue.getjButtonPrecedent().addActionListener(this);

    }

        
     public final void afficherLesVisiteurs() {
        try {
            lesVisiteurs = Dao.getAll();
            for (MetierVisiteur visiteur : lesVisiteurs){
                vue.getModeleListeVisiteurs().addElement(visiteur);                
            }
            
        } catch (SQLException ex) {
      //      JOptionPane.showMessageDialog(vue, "Ctrl - échec de sélection des adresses");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(vue, "Ctrl - erreur SQL");
        }

    }
                                               

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source ==  vue.getjButtonOk()) {
            unVisiteur = (MetierVisiteur) vue.getjComboBoxListeVisiteurs().getSelectedItem();           
            vue.getjTextFieldNom().setText(unVisiteur.getNom());     
            vue.getjTextFieldPrenom().setText(unVisiteur.getPrenom()); 
            vue.getjTextFieldAdresse().setText(unVisiteur.getAdresse()); 
            vue.getjTextFieldVille().setText(unVisiteur.getVille());   
            
            vue.getjTextFieldSecteur().setText("");                 
            vue.getjTextFieldLabo().setText(""); 
            vue.getjTextFieldSecteur().setText(unVisiteur.getSecNom().getSec_libelle());                 
            vue.getjTextFieldLabo().setText(unVisiteur.getLabNom().getLab_nom());         
       }
       
       if (source ==  vue.getjButtonSuivant()) {
           int i = vue.getjComboBoxListeVisiteurs().getSelectedIndex();
           int z = i+1;
           if (z < vue.getjComboBoxListeVisiteurs().getItemCount()) {                
                unVisiteur = (MetierVisiteur) vue.getjComboBoxListeVisiteurs().getItemAt(z);
                vue.getjComboBoxListeVisiteurs().setSelectedIndex(z);
                vue.getjTextFieldNom().setText(unVisiteur.getNom());     
                vue.getjTextFieldPrenom().setText(unVisiteur.getPrenom()); 
                vue.getjTextFieldAdresse().setText(unVisiteur.getAdresse()); 
                vue.getjTextFieldVille().setText(unVisiteur.getVille());   

                vue.getjTextFieldSecteur().setText("");                 
                vue.getjTextFieldLabo().setText(""); 
                vue.getjTextFieldSecteur().setText(unVisiteur.getSecNom().getSec_libelle());                 
                vue.getjTextFieldLabo().setText(unVisiteur.getLabNom().getLab_nom());                
                
           }
                
       }
       
        if (source ==  vue.getjButtonPrecedent()) {
           int i = vue.getjComboBoxListeVisiteurs().getSelectedIndex();
           int z = i-1;
           if (z >= 0) {                
                unVisiteur = (MetierVisiteur) vue.getjComboBoxListeVisiteurs().getItemAt(z);
                vue.getjComboBoxListeVisiteurs().setSelectedIndex(z);
                vue.getjTextFieldNom().setText(unVisiteur.getNom());     
                vue.getjTextFieldPrenom().setText(unVisiteur.getPrenom()); 
                vue.getjTextFieldAdresse().setText(unVisiteur.getAdresse()); 
                vue.getjTextFieldVille().setText(unVisiteur.getVille());   

                vue.getjTextFieldSecteur().setText("");                 
                vue.getjTextFieldLabo().setText(""); 
                vue.getjTextFieldSecteur().setText(unVisiteur.getSecNom().getSec_libelle());                 
                vue.getjTextFieldLabo().setText(unVisiteur.getLabNom().getLab_nom()); 
               
                
           }
                
       }
    }
        
}

