package models.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Connect;
import models.metier.Labo;
import models.metier.MetierVisiteur;
import models.metier.Secteur;

public class DaoVisiteur {
    
  public static List<MetierVisiteur> getAll() throws SQLException, ClassNotFoundException {         
      List<MetierVisiteur> lesVisiteurs = new ArrayList<MetierVisiteur>(); 
      MetierVisiteur unVisiteur;
      Secteur unSecteur = null;
      Labo unLabo = null;
      Connection con = models.Connect.Connection();      
      Statement state = con.createStatement();
      
      ResultSet res = state.executeQuery("SELECT * FROM visiteur");    
      
      while (res.next()) {
            String matricule = res.getString("VIS_MATRICULE");
            String nom = res.getString("VIS_NOM");
            String prenom = res.getString("Vis_PRENOM");
            String adresse = res.getString("VIS_ADRESSE");
            String cp = res.getString("VIS_CP");;
            String ville = res.getString("VIS_VILLE");
            Date date = res.getDate("VIS_DATEEMBAUCHE");
            String secCode = res.getString("SEC_CODE");
            String labCode = res.getString("LAB_CODE");
            unSecteur = DaoSecteur.getNomSecteur(secCode);
            unLabo = DaoLabo.getLabo(labCode);
            unVisiteur = new MetierVisiteur(matricule, nom, prenom, adresse, cp, ville, date, unSecteur, unLabo);
            lesVisiteurs.add(unVisiteur);
           
        }
      
      res.close();
       state.close();
       con.close();
    return lesVisiteurs;
       
      
      
  }
  
  public static MetierVisiteur getOne(String id) throws SQLException, ClassNotFoundException {
      MetierVisiteur unVisiteur = null;
      Secteur unSecteur = null;      
      Labo unLabo = null;
      Connection con = models.Connect.Connection();      
      Statement state = con.createStatement();
      
      ResultSet res = state.executeQuery("SELECT * FROM visiteur WHERE VIS_MATRICULE='"+ id +"'");    
      
      while (res.next()) {
            String matricule = res.getString("VIS_MATRICULE");
            String nom = res.getString("VIS_NOM");
            String prenom = res.getString("Vis_PRENOM");
            String adresse = res.getString("VIS_ADRESSE");
            String cp = res.getString("VIS_CP");;
            String ville = res.getString("VIS_VILLE");
            Date date = res.getDate("VIS_DATEEMBAUCHE");
            String secCode = res.getString("SEC_CODE");
            String labCode = res.getString("LAB_CODE");
            unSecteur = DaoSecteur.getNomSecteur(secCode);
            unLabo = DaoLabo.getLabo(labCode);
            unVisiteur = new MetierVisiteur(matricule, nom, prenom, adresse, cp, ville, date, unSecteur, unLabo);
           
        }
      
      res.close();
       state.close();
       con.close();
       
    return unVisiteur;
       
      
      
  }
}
