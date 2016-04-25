/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import models.daos.DaoRapportVisite;
import models.metier.Praticien;
import views.VueDetailPracticien;
import views.VueMenu;
import views.VueRapportVisite;
import static sun.security.jgss.GSSUtil.login;

/**
 *
 * @author clement
 */
public class ControllersDetailPracticien implements ActionListener {

    private VueDetailPracticien vue;
    private Praticien unPracticien;
    private List<Praticien> lesPracticiens;

    private int z;
    private String login;

    public ControllersDetailPracticien(VueDetailPracticien vue, String login, int z) {
        this.vue = vue;
        this.login = login;
        this.z = z;
        afficherPracticien();
        vue.getjButtonOk().addActionListener(this);
        vue.getjButtonFermer().addActionListener(this);
        vue.getjButtonSuivant().addActionListener(this);
        vue.getjButtonPrecedent().addActionListener(this);
    }

    public void afficherPracticien() {
        try {
            lesPracticiens = DaoRapportVisite.getPracticien();
            for (Praticien unPracticien : lesPracticiens) {
                vue.getModeleListePracticien().addElement(unPracticien);
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
        if (source == vue.getjButtonOk()) {
            unPracticien = (Praticien) vue.getjComboBoxPracticien().getSelectedItem();
            vue.getjTextFieldMatricule().setText(unPracticien.getMatricule());
            vue.getjTextFieldNom().setText(unPracticien.getNom());
            vue.getjTextFieldPrenom().setText(unPracticien.getPrenom());
            vue.getjTextFieldAdresse().setText(unPracticien.getAdresse());
            vue.getjTextFieldCp().setText(unPracticien.getCp());
            vue.getjTextFieldVille().setText(unPracticien.getVille());
            vue.getjTextFieldCoef().setText(unPracticien.getCoef());
            vue.getjTextFieldLieu().setText(unPracticien.getTypeCode().getType_libelle());
        }
        if (source == vue.getjButtonSuivant()) {
            int i = vue.getjComboBoxPracticien().getSelectedIndex();
            int z = i + 1;
            if (z < vue.getjComboBoxPracticien().getItemCount()) {
                unPracticien = (Praticien) vue.getjComboBoxPracticien().getItemAt(z);
                vue.getjComboBoxPracticien().setSelectedIndex(z);
                vue.getjTextFieldMatricule().setText(unPracticien.getMatricule());
                vue.getjTextFieldNom().setText(unPracticien.getNom());
                vue.getjTextFieldPrenom().setText(unPracticien.getPrenom());
                vue.getjTextFieldAdresse().setText(unPracticien.getAdresse());
                vue.getjTextFieldVille().setText(unPracticien.getVille());
                vue.getjTextFieldCoef().setText(unPracticien.getCoef());
                vue.getjTextFieldLieu().setText(unPracticien.getTypeCode().getType_libelle());
            }

        }

        if (source == vue.getjButtonPrecedent()) {
            int i = vue.getjComboBoxPracticien().getSelectedIndex();
            int z = i - 1;
            if (z >= 0) {
                unPracticien = (Praticien) vue.getjComboBoxPracticien().getItemAt(z);
                vue.getjComboBoxPracticien().setSelectedIndex(z);
                vue.getjTextFieldMatricule().setText(unPracticien.getMatricule());
                vue.getjTextFieldNom().setText(unPracticien.getNom());
                vue.getjTextFieldPrenom().setText(unPracticien.getPrenom());
                vue.getjTextFieldAdresse().setText(unPracticien.getAdresse());
                vue.getjTextFieldVille().setText(unPracticien.getVille());
                vue.getjTextFieldCoef().setText(unPracticien.getCoef());
                vue.getjTextFieldLieu().setText(unPracticien.getTypeCode().getType_libelle());
            }

        }

        if (source == vue.getjButtonFermer()) {
            
            if (z == 0) {
                VueMenu vueMenu = new VueMenu();
                ControllersMenu c = new ControllersMenu(vueMenu, login);
                vueMenu.setVisible(true);
            } else if (z == 1) {
                VueRapportVisite vueRapportVisite = new VueRapportVisite();
                ControllersRapportVisite c = new ControllersRapportVisite(vueRapportVisite, login);
                vueRapportVisite.setVisible(true);
            }
            vue.dispose();
        }
    }
}


