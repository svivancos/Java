package ev2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
/*
import java.sql.ResultSet;
import java.sql.Statement;
*/
public class UtilRegantes
{
    private Connection db = null; // para manejar la conexión a la base de datos

    public boolean DBConecta()
    {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Configurar la conexión para MySQL
            // Previamente, debe haberse instalado el SGBD y tener el esquema "regantes" creado
            // En este ejemplo, el catálogo es "regantes", el usuario "root" y la contraseña "mysql":
            this.db = DriverManager.getConnection("jdbc:mysql://127.0.0.1:13306/regantes?user=root&password=mysql");            
            return true;
        }
        catch (ClassNotFoundException ex) {
            System.out.println("ERROR - Al conectar a BD - No se ha encontrado la clase del : "+ex.getMessage());
            return false;
        }
        catch (InstantiationException ex) {
            System.out.println("ERROR - Al conectar a BD - No es posible instanciar el driver de acceso a datos: "+ex.getMessage());
            return false;
        }
        catch (IllegalAccessException ex) {
            System.out.println("ERROR - Al conectar a BD - Acceso ilegal: "+ex.getMessage());
            return false;
        }
        catch (SQLException ex) {
            System.out.println("ERROR - Al conectar a BD - Error de SQL: "+ex.getMessage());
            return false;
        }
    }
    
    // EnAlta() - método para averiguar si un regante está o no
    // dado de alta en una determinada fecha
    //
    // - regante: nº de regante a evaluar
    // - afecha: fecha en la cual se va a evaluar si está dado de alta o no
    //
    // - devuelve: un valor booleano
    //   - true: si existe y está dado de alta en la fecha indicada
    //   - false: en caso contrario
    //
    public boolean EnAlta(int regante, Date afecha) {
        // declaramos "resultado" para calcular en ella el valor a devolver
        boolean resultado = false;
        try
        {
            // obtenemos la consulta preparada "qExiste"
            PreparedStatement qExiste = db.prepareStatement(
                "select count(*) as EN_ALTA"
                + " from regantes"
                + " where id=? and (alta<=? and ((baja is null) or (baja>=?)))");
            // cargamos los parámetros en el mismo orden de lectura
            qExiste.setInt (1, regante);
            qExiste.setDate(2, afecha);
            qExiste.setDate(3, afecha);
            // ejecutamos la consulta, obteniendo "resultado" según lo que devuelva "rs"
            ResultSet rs = qExiste.executeQuery();
            resultado = (rs.next()) && (rs.getInt("EN_ALTA")>0);
        }
        catch (SQLException ex)
        {
            System.out.println("ERROR - Al evaluar alta de regante: "+ex.getMessage());
            resultado = false;            
        }
        return resultado;
    }
    
    // HaReganteEnFecha()
    public double HaReganteEnFecha(int regante,Date afecha)
    {
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
         // primera comprobación: ver si está dado de alta en esa fecha
         if (EnAlta(regante,afecha))
         {
             double has;
             has = HaReganteEnFecha(regante, afecha);
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