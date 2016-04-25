/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import models.daos.DaoVisiteur;
import models.daos.DaoAuthentification;
import models.metier.Authentification;
import models.metier.MetierVisiteur;
import views.VueAuthentification;
import views.VueMenu;
import views.VueRapportVisite;
import views.VueVisiteur;

/**
 *
 * @author clement
 */
public class ControllersAuthentification implements ActionListener {

    private VueAuthentification vue;
    private VueMenu vue2;
    private Authentification unUtilisateur;
    
    public ControllersAuthentification(VueAuthentification vue) {
        this.vue = vue;
        vue.getjButtonOk().addActionListener(this); 
        vue.getjButtonFermer().addActionListener(this);
    }
    

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == vue.getjButtonOk()) {
            try {
                String login = vue.getjJTextFieldLogin().getText();                
                String pass = vue.getjJPasswordFieldMotDePasse().getText();                 
                unUtilisateur = DaoAuthentification.getAuth(login, pass);
                if (unUtilisateur == null ) {
                    JOptionPane.showMessageDialog(vue, "Login ou mot de passe incorrecte");
                } else {
                    vue2 = new VueMenu();
                    ControllersMenu controllers = new ControllersMenu(vue2, login);
                    vue2.setVisible(true);
                    vue.dispose();
                }
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(vue, "Ctrl - erreur SQL");
            }

        }
        
        if (source == vue.getjButtonFermer()) {
            vue.dispose();
        }
    }

}
