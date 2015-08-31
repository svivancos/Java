package ev3;
/* Importamos Date y Calendar para tener el formato
y calcular las fechas correctamente. */
import java.util.Date;
import java.util.Calendar;
/**
 * Clase orientada al manejo del valor de un DNI
 * y su tratamiento o correcci�n
 * @author JLC y revisado por SVV
 * @version 1.0
 */
public class UtilidadesDNI
{
    private static final String alfabetoDNI = "TRWAGMYFPDXBNJZSQVHLCKET";

	/**
     * Calcula la letra a partir del n�mero.
     * 
     * @param dni (String num�rico del DNI) 
     * 
     * @return letra (String con �nicamente la letra generada)
     */
    public String getLetra (String dni)
    {
    	dni = limpiarDNI(dni); // Limpiamos el DNI de , . -
        String tablaLetras=alfabetoDNI;  // Posibles letras en un determinado orden
        int modulo = Integer.parseInt(dni) % 23; // Funci�n para calcular la posici�n
        String letra = tablaLetras.substring(modulo,modulo+1);
        return letra; 
    }    
  
    /**
     * Comprueba que el DNI introducido y su letra
     * se corresponde. En caso de que al n�mero le falten d�gitos <BR>
     * los completa con 0's a la izquierda y, en caso de que la
     * letra no sea la correcta, la corrige.
     * 
     * @param dni (String num�rico del DNI)
     * 
     * @return dni (String DNI completado o tal cual estaba)
     * @return letra (Generada o si era correcta, la anterior)
     * @return ERRDNI-1 (DNI tiene m�s de 9 d�gitos)
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
     * Calcula la diferencia de a�os entre una fecha introducida
     * y la actual, es decir, la edad.
     * 
     * @param nacimiento (Se ingresa la fecha con la clase "Date")
     * 
     * @return dif (Edad del sujeto)
     */
    /*
    edad --- Calcula la edad de una persona seg�n el momento actual
      - nacimiento: Fecha de nacimiento en base a la cual se hace el c�lculo
      - devuelve: El n�mero entero de a�os ya cumplidos, que deber� ser...
        ... siempre un valor positivo, cero o -1 si no ha nacido a�n.
    */
    public int getEdad (Date nacimiento)
    {
        Calendar cal = Calendar.getInstance();
        // a�o, mes y d�a de nacimiento
        cal.setTime(nacimiento);
        int an = cal.get(Calendar.YEAR);
        int mn = cal.get(Calendar.MONTH);
        int dn = cal.get(Calendar.DAY_OF_MONTH);
        // a�o, mes y d�a actuales
        cal.setTime(new Date());
        int aa = cal.get(Calendar.YEAR);
        int ma = cal.get(Calendar.MONTH);
        int da = cal.get(Calendar.DAY_OF_MONTH);
        /* dif ser� la edad, que apriori es la diferencia entre el a�o actual...
        ... y el de nacimiento, aunque habr� que hacer algunas correccioens: */
        int dif = aa-an;
        // si la fecha de nacimiento es futura, el valor a devolver es -1
        if (an>=aa && mn>=ma && dn>=da)
           dif=-1;
        /* sino, si el mes de nacimiento es menor que el actual, ...
        ... entonces hay que restar un a�o */
        else if (mn<ma) 
            dif--;
        /* sino, si el mes de nacimiento coincide con el actual y el d�a de...
        ... nacimiento es anterior, tambi�n hay que restar un a�o */
        else if (mn==ma && dn<da) 
            dif--;
        // devolvemos los a�os calculados
        return dif;
    }  
}