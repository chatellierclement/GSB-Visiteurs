/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Connect;
import models.metier.LieuExercice;
import models.metier.Praticien;
import models.metier.RapportVisite;

/**
 *
 * @author clement
 */
public class DaoRapportVisite {
    
    public static List<Praticien> getPracticien() throws SQLException, ClassNotFoundException {
       Praticien unPracticien;
       LieuExercice unLieu = null;
       List<Praticien> lesPracticiens = new ArrayList<Praticien>();
       Connection con = models.Connect.Connection();      
       Statement state = con.createStatement();      
       ResultSet res = state.executeQuery("SELECT * FROM praticien"); 
       
       while (res.next()) {
            String matricule = res.getString("PRA_NUM");
            String nom = res.getString("PRA_NOM");
            String prenom = res.getString("PRA_PRENOM");
            String adresse = res.getString("PRA_ADRESSE");
            String cp = res.getString("PRA_CP");;
            String ville = res.getString("PRA_VILLE");
            String coef = res.getString("PRA_COEFNOTORIETE");
            String typeCode = res.getString("TYP_CODE");
            unLieu = DaoLieuExercice.getLieuExercice(typeCode);
            unPracticien = new Praticien(matricule, nom, prenom, adresse, cp, ville, coef, unLieu);
            lesPracticiens.add(unPracticien);
           
        }
       res.close();
       state.close();
       con.close();
       
       return lesPracticiens;
        
    }
    
    public static Praticien getOneById(String id) throws SQLException, ClassNotFoundException {
       Praticien unPracticien = null;
       LieuExercice unLieu = null;
       Connection con = models.Connect.Connection();      
       Statement state = con.createStatement();      
       ResultSet res = state.executeQuery("SELECT * FROM praticien WHERE PRA_NUM ='" + id + "'"); 
       
       while (res.next()) {
            String matricule = res.getString("PRA_NUM");
            String nom = res.getString("PRA_NOM");
            String prenom = res.getString("PRA_PRENOM");
            String adresse = res.getString("PRA_ADRESSE");
            String cp = res.getString("PRA_CP");;
            String ville = res.getString("PRA_VILLE");
            String coef = res.getString("PRA_COEFNOTORIETE");
            String typeCode = res.getString("TYP_CODE");
            unLieu = DaoLieuExercice.getLieuExercice(typeCode);
            unPracticien = new Praticien(matricule, nom, prenom, adresse, cp, ville, coef, unLieu);
            
           
        }
       
       res.close();
       state.close();
       con.close();
       
       return unPracticien;
        
    }
    
    public static void insertRapport(String numRapport, String login, String id, String motif, String date, String bilan) throws SQLException, ClassNotFoundException {
       Connection con = models.Connect.Connection();      
       Statement state = con.createStatement();      
       state.executeUpdate("INSERT INTO rapport_visite (VIS_MATRICULE, RAP_NUM, PRA_NUM, RAP_DATE, RAP_BILAN, RAP_MOTIF) VALUES ('"+login+"','"+numRapport+"','"+id+"','"+date+"','"+bilan+"','"+motif+"')");
 
       
       state.close();
       con.close();
    }
    
    public static List<RapportVisite> getRapportVisite(String login) throws SQLException, ClassNotFoundException {
       RapportVisite unRapportVisite = null;
       List<RapportVisite> lesRapportsVisite = new ArrayList<RapportVisite>(); 
       Connection con = models.Connect.Connection();      
       Statement state = con.createStatement();  
       ResultSet res = state.executeQuery("SELECT * FROM rapport_visite WHERE VIS_MATRICULE ='" + login + "'");
       
       while (res.next()) {
           int rapport = res.getInt("RAP_NUM");
           String pra_num = res.getString("PRA_NUM");
           Praticien unPracticien = DaoRapportVisite.getOneById(pra_num);
           Date date = res.getDate("RAP_DATE");
           String bilan = res.getString("RAP_BILAN");
           String motif = res.getString("RAP_MOTIF");
           unRapportVisite = new RapportVisite(rapport, unPracticien, date, motif, bilan);
           lesRapportsVisite.add(unRapportVisite);
       }
       
       res.close();
       state.close();
       con.close();
       
       return lesRapportsVisite;
    }
    
}
