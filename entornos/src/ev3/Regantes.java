package ev3;

import javax.swing.JOptionPane;
import java.sql.Date;
/**
 *
 * @author jlcruz-cifp
 */
public class Regantes {

    public static void main(String[] args) {
        // Abrir conexión a la base de datos y comprobar que se hace ok:
        UtilRegantes ur = new UtilRegantes();
        if (ur.DBConectar())
        {
                    
            // Cosas a hacer cuando la conexión funciona ok

            /* Pruebas preliminares de "EnAlta"
            
            if (ur.EnAlta(10, Date.valueOf("2012-10-10")))                
                JOptionPane.showMessageDialog(null,"Test 1 en alta.");
            else
                JOptionPane.showMessageDialog(null,"Test 1 en baja.");
            if (ur.EnAlta(1, Date.valueOf("2014-10-10")))                
                JOptionPane.showMessageDialog(null,"Test 1 en alta.");
            else
                JOptionPane.showMessageDialog(null,"Test 1 en baja.");
            if (ur.EnAlta(1, Date.valueOf("2015-10-10")))                
                JOptionPane.showMessageDialog(null,"Test 1 en alta.");
            else
                JOptionPane.showMessageDialog(null,"Test 1 en baja.");
            */
            
            /* pruebas preliminares del método "HaReganteEnFecha"
            
            double suptest;
            suptest = ur.HaReganteEnFecha(2, Date.valueOf("2015-01-01"));
            JOptionPane.showMessageDialog(null,"Resultado 1: "+Double.toString(suptest));
            suptest = ur.HaReganteEnFecha(2, Date.valueOf("2012-01-01"));
            JOptionPane.showMessageDialog(null,"Resultado 2: "+Double.toString(suptest));
            */
            
            /* pruebas preliminares del método "VotosSegunHa" 
            System.out.println(ur.VotosSegunHa(11));
            System.out.println(ur.VotosSegunHa(10));
            System.out.println(ur.VotosSegunHa(9));
            */
            /* pruebas preliminares de los métodos "VotosSegunReganteYFecha" y "VotosTotalesAFecha" */
            System.out.println("HaSegunRegante 1: "+ur.HaReganteEnFecha(1,Date.valueOf("2012-01-01"), true));
            System.out.println("Reg 1: "+ur.VotosSegunReganteYFecha(1,Date.valueOf("2012-01-01")));
            System.out.println("Reg 2: "+ur.VotosSegunReganteYFecha(2,Date.valueOf("2012-01-01")));
            System.out.println("Reg 3: "+ur.VotosSegunReganteYFecha(3,Date.valueOf("2012-01-01")));
            System.out.println("Total: "+ur.VotosTotalesAFecha(Date.valueOf("2012-01-01"),"1,2,3"));
            

        }
        else
            JOptionPane.showMessageDialog(null,"Se ha producido un error"
                    +" en la onexión a la base de datos. Consulte la consola"
                    +" de la aplicación para ampliar información.");
    }
    
}
