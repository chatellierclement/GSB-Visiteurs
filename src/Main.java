import controllers.ControllersAuthentification;
import controllers.ControllersRapportVisite;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.Connect;
import controllers.ControllersVisiteurs;
import views.VueAuthentification;
import views.VueDetailPracticien;
import views.VueRapportVisite;
import views.VueVisiteur;

/**
 *
 * @author btssio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {     
//      VueRapportVisite vue = new VueRapportVisite();
//      ControllersRapportVisite controllers = new ControllersRapportVisite(vue);
     VueAuthentification vue = new VueAuthentification();
       ControllersAuthentification controllers = new ControllersAuthentification(vue);
//        // afficher la vue
        vue.setVisible(true);
        
    }
    
}
