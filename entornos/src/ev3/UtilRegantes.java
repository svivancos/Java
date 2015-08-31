/**
 * Regantes paquete.
 */
package ev3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
/**
 * Clase destinada a la gestión de los regantes.
 * 
 * Esta clase sirve para realizar operaciones sobre
 * regantes, concretamente referidas al cálculo de
 * <a href="http://www.google.es">hectáreas</a> de <strong>riego</strong>, <strong>votos</strong>, etc.
 * 
 * @author Jos&eacute; Luis Cruz
 * @version 0.0.1
 * @see Regantes
 */
public class UtilRegantes extends UtilTrabajadores
{
    
    /**
     * 
     * Calcula las hectáreas de riego que posee un regante en una fecha dada.
     * 
     * @param regante Número de regante a evaluar.
     * @param afecha Fecha en la que el regante debe poseer las tierras.
     * @return La cantidad de hectáreas que posee el regante en esa fecha.
     */
    public double HaReganteEnFecha(int regante, Date afecha, Boolean conDerechoRiego)
    {
        checkConexionBD();
        checkRegante(regante);        
        // declaramos "resultado" para calcular en ella el valor a devolver
        double resultado = 0.0;
        try
        {
            // obtenemos la consulta preparada "qHas"
            PreparedStatement qHas = db.prepareStatement(
                "select sum(superficie) as superficie_total" +
                "  from parcelas" +
                "  where regante=?" +
                "    and alta<=?" +
                "    and ((baja>=?) or (baja is null))");
            // cargamos los parámetros en el mismo orden de lectura
            qHas.setInt (1, regante);
            qHas.setDate(2, afecha);
            qHas.setDate(3, afecha);
            // ejecutamos la consulta, obteniendo "resultado" según lo que devuelva "rs"
            ResultSet rs = qHas.executeQuery();
            // obtener el primer registro devuelto por la consulta
            rs.next();
            // accedo al valor del campo "superficie_total", de ese primer registro
            resultado = rs.getDouble("superficie_total");
        }
        catch (SQLException ex)
        {
            System.out.println("ERROR - Al evaluar las hectáreas de un regante: "+ex.getMessage());
            resultado = 0.0;            
        }
        return resultado;
    }
    
    // VotosSegunHa
    // 
    public int VotosSegunHa (double has)
    {
        checkConexionBD();
        // declaramos "resultado" para calcular en ella el valor a devolver
        int resultado = 0;
        try
        {
            // obtenemos la consulta preparada "qHas"
            PreparedStatement qVotos = db.prepareStatement(
                "select max(num_votos) as num_votos from votos" +
                " where sup_minima <= ?");
            // cargamos los parámetros en el mismo orden de lectura
            qVotos.setDouble (1, has);
            // ejecutamos la consulta, obteniendo "resultado" según lo que devuelva "rs"
            ResultSet rs = qVotos.executeQuery();
            // obtener el primer registro devuelto por la consulta
            rs.next();
            // accedo al valor del campo "num_votos", de ese primer registro
            resultado = rs.getInt("num_votos");
        }
        catch (SQLException ex)
        {
            System.out.println("ERROR - Al evaluar el número de votos: "+ex.getMessage());
            resultado = 0;            
        }
        return resultado;
    }

    
    // VotosSegunReganteYFecha
    //
     public int VotosSegunReganteYFecha (int regante,Date afecha)
     {
        checkConexionBD();
        checkRegante(regante);        
         // primera comprobación: ver si está dado de alta en esa fecha
         if (EnAlta(regante,afecha))
         {
             double has;
             has = HaReganteEnFecha(regante, afecha, true);
             int votos;
             votos = VotosSegunHa(has);
             return votos;
         }
         else // si no está dado de alta, devuelvo cero votos
             return 0;
     }

     
   // VotosTotalesAFecha: suma los votos de todos los regantes o de unos en concreto
   //
   public int VotosTotalesAFecha (Date afecha, String soloregantes)
   {
        checkConexionBD();
        int resultado = 0;
        String filtroreg = " and id in ("+soloregantes+")";
        try
        {
            // obtenemos la consulta preparada que recorre los regantes en alta
            PreparedStatement qRegantes = db.prepareStatement(
                "select id from regantes" +
                " where alta <= ? and ((baja is null) or (baja>=?))"+filtroreg);
            // cargamos los parámetros en el mismo orden de lectura
            qRegantes.setDate (1, afecha);
            qRegantes.setDate (2, afecha);
            // ejecutamos la consulta, 
            ResultSet rsRegantes = qRegantes.executeQuery();
            // recorremos los regantes y acumulamos en "resultado" sus votos
            while (rsRegantes.next())
            {
                resultado += VotosSegunReganteYFecha(rsRegantes.getInt("id"), afecha);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("ERROR - Al evaluar el número de votos de varios regantes: "+ex.getMessage());
            resultado = 0;            
        }
        return resultado;
   }
    
    
}
