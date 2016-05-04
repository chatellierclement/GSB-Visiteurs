/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.String.valueOf;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.daos.DaoVisiteur;
import models.daos.DaoRapportVisite;
import models.metier.Authentification;
import models.metier.MetierVisiteur;
import models.metier.Praticien;
import models.metier.RapportVisite;
import views.VueAuthentification;
import views.VueDetailPracticien;
import views.VueMenu;
import views.VueRapportVisite;

/**
 *
 * @author clement
 */
public class ControllersRapportVisite implements ActionListener {

    private VueRapportVisite vue;
    private VueDetailPracticien vue2;
    private VueAuthentification vueAuth;
    private List<Praticien> lesPracticiens;
    private Praticien unPracticien;
    private List<MetierVisiteur> lesVisiteurs;
    private MetierVisiteur unVisiteur;
    private ControllersAuthentification c;
    private String login;
    private List<RapportVisite> lesRapportsVisite;

    private int i = 0;

//    private Boolean boolCheckNouveau = false;

    public ControllersRapportVisite(VueRapportVisite vue, String login) {
        this.vue = vue;
        this.login = login;
        afficherPracticien();
        afficherRapport();
        vue.getjButtonDetails().addActionListener(this);
        vue.getjButtonFermer().addActionListener(this);
        vue.getjButtonOk().addActionListener(this);
        vue.getjButtonSuivant().addActionListener(this);
        vue.getjButtonPrecedent().addActionListener(this);
        vue.getjButtonNouveau().addActionListener(this);

    }
    
    /* Afficher les rapports à l'initialisation */
    public void afficherRapport() {

        try {

            lesRapportsVisite = DaoRapportVisite.getRapportVisite(login);
            if (lesRapportsVisite.size() == 0) {
                JOptionPane.showMessageDialog(vue, "Pas de rapport pour ce visiteur");
            } else {
                RapportVisite unRapportVisite = lesRapportsVisite.get(i);
                vue.getjTextFieldNumRapport().setText(valueOf(unRapportVisite.getRapport()));
                vue.getjTextFieldNumRapport().setEditable(false);

                String id = unRapportVisite.getUnPraticien().getMatricule();
                int id2 = Integer.parseInt(id) - 1;
                vue.getjComboBoxPracticien().setSelectedIndex(id2);
                vue.getjDateChooser1().setDate(lesRapportsVisite.get(i).getDate());

                vue.getjTextFieldMotif().setText(unRapportVisite.getMotif());
                vue.getjTextFieldBilan().setText(unRapportVisite.getBilan());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(vue, "Ctrl - erreur SQL");
        }
    }
    
     /* Afficher les praticiens dans la comboxbox à l'initialisation */
    public void afficherPracticien() {
        try {
            lesPracticiens = DaoRapportVisite.getPracticien();
            for (Praticien unPracticien : lesPracticiens) {
                vue.getModeleListePracticiens().addElement(unPracticien);
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
        
         /* Afficher la vue des praticiens au clic du bouton detail */
        if (source == vue.getjButtonDetails()) {
            vue2 = new VueDetailPracticien();
            int z = 1;
            ControllersDetailPracticien controllers = new ControllersDetailPracticien(vue2, login, z);
            vue2.setVisible(true);
            unPracticien = (Praticien) vue.getjComboBoxPracticien().getSelectedItem();
            int i = vue.getjComboBoxPracticien().getSelectedIndex();
            vue2.getjTextFieldMatricule().setText(unPracticien.getMatricule());
            vue2.getjTextFieldNom().setText(unPracticien.getNom());
            vue2.getjTextFieldPrenom().setText(unPracticien.getPrenom());
            vue2.getjTextFieldAdresse().setText(unPracticien.getAdresse());
            vue2.getjTextFieldCp().setText(unPracticien.getCp());
            vue2.getjTextFieldVille().setText(unPracticien.getVille());
            vue2.getjTextFieldCoef().setText(unPracticien.getCoef());
            vue2.getjTextFieldLieu().setText(unPracticien.getTypeCode().getType_libelle());

            try {
                lesPracticiens = DaoRapportVisite.getPracticien();
                for (Praticien unPracticien : lesPracticiens) {
                    vue2.getModeleListePracticien().addElement(unPracticien);
                }

            } catch (SQLException ex) {
                //      JOptionPane.showMessageDialog(vue, "Ctrl - échec de sélection des adresses");
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(vue, "Ctrl - erreur SQL");
            }

            vue2.getjComboBoxPracticien().setSelectedIndex(i);
            vue.hide();

        }
        
        /* Inserer les infos en bdd au clic du bouton ok */
        if (source == vue.getjButtonOk()) {         
                Boolean bool = false;
                unPracticien = (Praticien) vue.getjComboBoxPracticien().getSelectedItem();
                String numRapport = vue.getjTextFieldNumRapport().getText();
                String id = unPracticien.getMatricule();
                Date date = vue.getjDateChooser1().getDate(); 
                System.out.println(date);
                String motif = vue.getjTextFieldMotif().getText();
                String bilan = vue.getjTextFieldBilan().getText();

                try {
                    lesRapportsVisite = DaoRapportVisite.getRapportVisite(login);
                    for (RapportVisite unRapportVisite : lesRapportsVisite) {
                        int numero = unRapportVisite.getRapport();
                        if (numero == Integer.parseInt(numRapport)) {
                            JOptionPane.showMessageDialog(vue, "Déja rempli - Cliquer Nouveau pour effectuer un nouveau rapport");
                            bool = true;
                        }
                    }
                    if (bool == false) {
                        if (id.isEmpty() || date == null || motif.isEmpty() || bilan.isEmpty()) {                            
                            JOptionPane.showMessageDialog(vue, "Tous les champs doivent être remplis");

                        } else {                            
                            DaoRapportVisite.insertRapport(numRapport, login, id, motif, date, bilan);
                            JOptionPane.showMessageDialog(vue, "Rapport visite enregistré");
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControllersRapportVisite.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ControllersRapportVisite.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        /* Afficher le rapport suivant au clic du bouton suivant */
        if (source == vue.getjButtonSuivant()) {
            try {

                lesRapportsVisite = DaoRapportVisite.getRapportVisite(login);
                if (lesRapportsVisite != null) {
                    String numRapp = vue.getjTextFieldNumRapport().getText();
                    if (i < lesRapportsVisite.size() - 1) {
                        i++;
                        vue.getjTextFieldNumRapport().setText(valueOf(lesRapportsVisite.get(i).getRapport()));
                        vue.getjTextFieldNumRapport().setEditable(false);
                        vue.getjDateChooser1().setDate(lesRapportsVisite.get(i).getDate());
                        String id = lesRapportsVisite.get(i).getUnPraticien().getMatricule();
                        int id2 = Integer.parseInt(id) - 1;
                        vue.getjComboBoxPracticien().setSelectedIndex(id2);
                        vue.getjTextFieldMotif().setText(lesRapportsVisite.get(i).getMotif());
                        vue.getjTextFieldBilan().setText(lesRapportsVisite.get(i).getBilan());

                    }
                }
            } catch (SQLException ex) {
                //      JOptionPane.showMessageDialog(vue, "Ctrl - échec de sélection des adresses");
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(vue, "Ctrl - erreur SQL");
            }

        }

        /* Afficher le rapport précédent au clic du bouton suivant */
        if (source == vue.getjButtonPrecedent()) {
            try {

                lesRapportsVisite = DaoRapportVisite.getRapportVisite(login);
                if (lesRapportsVisite != null) {
                    String numRapp = vue.getjTextFieldNumRapport().getText();
                    if (i > 0) {
                        i--;
                        vue.getjTextFieldNumRapport().setText(valueOf(lesRapportsVisite.get(i).getRapport()));
                        vue.getjTextFieldNumRapport().setEditable(false);
                        vue.getjDateChooser1().setDate(lesRapportsVisite.get(i).getDate());
                        String id = lesRapportsVisite.get(i).getUnPraticien().getMatricule();
                        int id2 = Integer.parseInt(id) - 1;
                        vue.getjComboBoxPracticien().setSelectedIndex(id2);
                        vue.getjTextFieldMotif().setText(lesRapportsVisite.get(i).getMotif());
                        vue.getjTextFieldBilan().setText(lesRapportsVisite.get(i).getBilan());

                    }
                }
            } catch (SQLException ex) {
                //      JOptionPane.showMessageDialog(vue, "Ctrl - échec de sélection des adresses");
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(vue, "Ctrl - erreur SQL");
            }
        }
        
        /* vider les champs au clic du bouton nouveau */
        if (source == vue.getjButtonNouveau()) {
            try {

                lesRapportsVisite = DaoRapportVisite.getRapportVisite(login);
                if (lesRapportsVisite.size() == 0) {
                    vue.getjTextFieldNumRapport().setText("1");
                } else {
                    int numero = lesRapportsVisite.size() - 1;
                    vue.getjTextFieldNumRapport().setText(valueOf(lesRapportsVisite.get(numero).getRapport() + 1));

                }
            } catch (SQLException ex) {
                Logger.getLogger(ControllersRapportVisite.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControllersRapportVisite.class.getName()).log(Level.SEVERE, null, ex);
            }

            vue.getjComboBoxPracticien().setSelectedIndex(0);
            vue.getjDateChooser1().setDate(null);
            vue.getjTextFieldBilan().setText("");
            vue.getjTextFieldMotif().setText("");
        }
        
        /* fermer la vue et ouvrir le menu au clic du bouton fermer */
        if (source == vue.getjButtonFermer()) {            
            VueMenu vue2 = new VueMenu();
            ControllersMenu c = new ControllersMenu(vue2, login);
            vue2.setVisible(true);
            vue.dispose();
        }

    }
}
