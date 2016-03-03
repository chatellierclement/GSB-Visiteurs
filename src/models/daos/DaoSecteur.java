package models.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.metier.MetierVisiteur;
import models.metier.Secteur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author clement
 */
public class DaoSecteur {
    
    public static Secteur getNomSecteur(String code) throws SQLException, ClassNotFoundException {
      Secteur unSecteur = null;
      Connection con = models.Connect.Connection();      
      Statement state = con.createStatement();
      
      ResultSet res = state.executeQuery("SELECT * FROM SECTEUR WHERE SEC_CODE='"+ code +"'");    
      
      if (res.next()) {
            String secCode = res.getString("sec_code");
            String nom = res.getString("sec_libelle");
            unSecteur = new Secteur(secCode, nom);
           
      }
    return unSecteur;
       
      
      
  }
}
