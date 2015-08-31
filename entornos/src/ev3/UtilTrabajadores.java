/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ev3;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jlcruz-cifp
 */
public class UtilTrabajadores {
    public static final String CADENA_CONEXION_DB = "jdbc:mysql://127.0.0.1:13306/regantes?user=root&password=mysql";
    /**
     * Maneja la conexión a la base de datos.
     *
     * @since 0.0.1
     */
    protected Connection db = null;

    /**
     *
     * Conecta con el sistema gestor de la base de datos.
     *
     * Es necesario invocarlo antes de realizar cualquier
     * otra operación.
     *
     * @return True cuando la conexión se realiza correctamente.
     */
    public boolean DBConectar() {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Configurar la conexión para MySQL
            // Previamente, debe haberse instalado el SGBD y tener el esquema "regantes" creado
            // En este ejemplo, el catálogo es "regantes", el usuario "root" y la contraseña "mysql":
            this.db = DriverManager.getConnection(CADENA_CONEXION_DB);
            return true;
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR - Al conectar a BD - No se ha encontrado la clase del : " + ex.getMessage());
            return false;
        } catch (InstantiationException ex) {
            System.out.println("ERROR - Al conectar a BD - No es posible instanciar el driver de acceso a datos: " + ex.getMessage());
            return false;
        } catch (IllegalAccessException ex) {
            System.out.println("ERROR - Al conectar a BD - Acceso ilegal: " + ex.getMessage());
            return false;
        } catch (SQLException ex) {
            System.out.println("ERROR - Al conectar a BD - Error de SQL: " + ex.getMessage());
            return false;
        }
    }

    /**
     *
     * Averigua si un regante está o no dado de alta en una fecha.
     *
     * @param regante Número de regante a evaluar.
     * @param afecha Fecha en la cual se va a evaluar si está dado de alta o no.
     * @return True cuando el regante está dado de alta en la fecha indicada.
     * @throws IllegalArgumentException Desata este error cuando el número de regante no es positivo.
     */
    public boolean EnAlta(int regante, Date afecha) throws IllegalArgumentException {
        checkConexionBD();
        checkRegante(regante);
        // declaramos "resultado" para calcular en ella el valor a devolver
        boolean resultado = false;
        try {
            // obtenemos la consulta preparada "qExiste"
            PreparedStatement qExiste = db.prepareStatement("select count(*) as EN_ALTA" + " from regantes" + " where id=? and (alta<=? and ((baja is null) or (baja>=?)))");
            // cargamos los parámetros en el mismo orden de lectura
            qExiste.setInt(1, regante);
            qExiste.setDate(2, afecha);
            qExiste.setDate(3, afecha);
            // ejecutamos la consulta, obteniendo "resultado" según lo que devuelva "rs"
            ResultSet rs = qExiste.executeQuery();
            resultado = (rs.next()) && (rs.getInt("EN_ALTA") > 0);
        } catch (SQLException ex) {
            System.out.println("ERROR - Al evaluar alta de regante: " + ex.getMessage());
            resultado = false;
        }
        return resultado;
    }

    protected void checkConexionBD() throws IllegalStateException {
        // comprobar que estoy conectado
        if (this.db == null) {
            throw new IllegalStateException("Primero debe conectarse a la base de datos.");
        }
    }

    protected void checkRegante(int regante) throws IllegalArgumentException {
        // comprobamos que el regante sea un número positivo
        if (regante < 1) {
            throw new IllegalArgumentException("El número de regante debe ser mayor que cero.");
        }
    }
    
}
