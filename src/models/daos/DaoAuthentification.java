/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.metier.Authentification;

/**
 *
 * @author clement
 */
public class DaoAuthentification {
    
    public static Authentification getAuth(String log, String mdp) throws SQLException, ClassNotFoundException {
        Authentification unUtilisateur = null;
        Connection con = models.Connect.Connection();      
        Statement state = con.createStatement();      
        ResultSet res = state.executeQuery("SELECT * FROM visiteur WHERE VIS_MATRICULE='"+ log +"' AND VIS_NOM ='" + mdp + "'");
        
        if (res.next()) {
            String login = res.getString("VIS_MATRICULE");
            String pass = res.getString("VIS_NOM");
            unUtilisateur = new Authentification(login, pass);
        }
        
        res.close();
       state.close();
       con.close();
        
        return unUtilisateur;
    }
}
