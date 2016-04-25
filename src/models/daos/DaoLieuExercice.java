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
import models.metier.LieuExercice;

/**
 *
 * @author clement
 */
public class DaoLieuExercice {
    public static LieuExercice getLieuExercice(String code) throws SQLException, ClassNotFoundException {
       LieuExercice unLieu = null;
       Connection con = models.Connect.Connection();      
       Statement state = con.createStatement();      
       ResultSet res = state.executeQuery("SELECT * FROM type_praticien WHERE TYP_CODE ='" + code + "'"); 
       
       while (res.next()) {
            String typ_code = res.getString("TYP_CODE");
            String libelle = res.getString("TYP_LIBELLE");
            String lieu = res.getString("TYP_LIEU");
            unLieu = new LieuExercice(typ_code, libelle, lieu);           
        }
       
       res.close();
       state.close();
       con.close();
       
       return unLieu;
    }
}
