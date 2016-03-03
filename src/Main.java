import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.Connect;
import controllers.ControllersVisiteurs;
import ppe_gsb.VueVisiteur;

/**
 *
 * @author btssio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        VueVisiteur vue = new VueVisiteur();
        ControllersVisiteurs controllers = new ControllersVisiteurs(vue);
        // afficher la vue
        vue.setVisible(true);
        
    }
    
}
