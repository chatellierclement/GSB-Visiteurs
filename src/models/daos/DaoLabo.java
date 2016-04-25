package models.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.metier.Labo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author clement
 */
public class DaoLabo {
     public static Labo getLabo(String code) throws SQLException, ClassNotFoundException {
      Labo unLabo = null;
      Connection con = models.Connect.Connection();      
      Statement state = con.createStatement();
      
      ResultSet res = state.executeQuery("SELECT * FROM LABO WHERE LAB_CODE='"+ code +"'");    
      
      if (res.next()) {
            String lab_code = res.getString("lab_code");
            String lab_nom = res.getString("lab_nom");
            String lab_chef = res.getString("lab_chefvente");
            unLabo = new Labo(lab_code, lab_nom, lab_chef);
           
      }
      
      res.close();
       state.close();
       con.close();
    return unLabo;
       
      
      
  }
}
