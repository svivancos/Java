package ev3;
/* Importamos Date y Calendar para tener el formato
y calcular las fechas correctamente. */
import java.util.Date;
import java.util.Calendar;
/**
 * Clase orientada al manejo del valor de un DNI
 * y su tratamiento o corrección
 * @author JLC y revisado por SVV
 * @version 1.0
 */
public class UtilidadesDNI
{
    private static final String alfabetoDNI = "TRWAGMYFPDXBNJZSQVHLCKET";

	/**
     * Calcula la letra a partir del número.
     * 
     * @param dni (String numérico del DNI) 
     * 
     * @return letra (String con únicamente la letra generada)
     */
    public String getLetra (String dni)
    {
    	dni = limpiarDNI(dni); // Limpiamos el DNI de , . -
        String tablaLetras=alfabetoDNI;  // Posibles letras en un determinado orden
        int modulo = Integer.parseInt(dni) % 23; // Función para calcular la posición
        String letra = tablaLetras.substring(modulo,modulo+1);
        return letra; 
    }    
  
    /**
     * Comprueba que el DNI introducido y su letra
     * se corresponde. En caso de que al número le falten dígitos <BR>
     * los completa con 0's a la izquierda y, en caso de que la
     * letra no sea la correcta, la corrige.
     * 
     * @param dni (String numérico del DNI)
     * 
     * @return dni (String DNI completado o tal cual estaba)
     * @return letra (Generada o si era correcta, la anterior)
     * @return ERRDNI-1 (DNI tiene más de 9 dígitos)
     * @return ERRDNI-2 (Letra incorrecta)
     * 
     * @throws IllegalArgumentException (Se espera que pueda no entrar un String)
     */
    public String check (String dni) throws IllegalArgumentException
    {
        String letra="";
        
       // Comprueba la longitud y rellena con 0's si es necesario
        while (dni.length()<8)
            dni = "0" + dni;

        // Quita puntos, comas y guiones
        dni = limpiarDNI(dni);
        
        if (dni.length()>9)
            return "ERRDNI-1";
        else if (dni.length()==9) // Separar la letra y comprobar si es correcta
        {
            letra = dni.substring(8);
            dni = dni.substring(0,8);
            if (letra.equals(getLetra(dni)))
                return dni+letra;
            else
                return "ERRDNI-2";
        }
        else // Si no lo es, se calcula y se devuelve todo
        {
            letra = getLetra(dni);
            return dni+letra;
        }
    }

	private String limpiarDNI(String dni) {
		if ((dni.contains("."))||(dni.contains(","))||(dni.contains("-")))
        {
            dni = dni.replace(".", "");
            dni = dni.replace(",", "");
            dni = dni.replace("-", "");
        }
		return dni;
	}
    
    /**
     * Calcula la diferencia de años entre una fecha introducida
     * y la actual, es decir, la edad.
     * 
     * @param nacimiento (Se ingresa la fecha con la clase "Date")
     * 
     * @return dif (Edad del sujeto)
     */
    /*
    edad --- Calcula la edad de una persona según el momento actual
      - nacimiento: Fecha de nacimiento en base a la cual se hace el cálculo
      - devuelve: El número entero de años ya cumplidos, que deberá ser...
        ... siempre un valor positivo, cero o -1 si no ha nacido aún.
    */
    public int getEdad (Date nacimiento)
    {
        Calendar cal = Calendar.getInstance();
        // año, mes y día de nacimiento
        cal.setTime(nacimiento);
        int an = cal.get(Calendar.YEAR);
        int mn = cal.get(Calendar.MONTH);
        int dn = cal.get(Calendar.DAY_OF_MONTH);
        // año, mes y día actuales
        cal.setTime(new Date());
        int aa = cal.get(Calendar.YEAR);
        int ma = cal.get(Calendar.MONTH);
        int da = cal.get(Calendar.DAY_OF_MONTH);
        /* dif será la edad, que apriori es la diferencia entre el año actual...
        ... y el de nacimiento, aunque habrá que hacer algunas correccioens: */
        int dif = aa-an;
        // si la fecha de nacimiento es futura, el valor a devolver es -1
        if (an>=aa && mn>=ma && dn>=da)
           dif=-1;
        /* sino, si el mes de nacimiento es menor que el actual, ...
        ... entonces hay que restar un año */
        else if (mn<ma) 
            dif--;
        /* sino, si el mes de nacimiento coincide con el actual y el día de...
        ... nacimiento es anterior, también hay que restar un año */
        else if (mn==ma && dn<da) 
            dif--;
        // devolvemos los años calculados
        return dif;
    }  
}