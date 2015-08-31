package ev2;
import java.util.Date;
import java.util.Calendar;
public class DNIUtils
{
    /**
    letraDNI --- Calcula la letra de la parte numérica de un DNI
      - dni: parte numérica del DNI al que hay que calcularle la letra
      - devuelve: letra vinculada a ese número de DNI
    */
    public String letraDNI (String dni)
    {
        String tablaLetras="TRWAGMYFPDXBNJZSQVHLCKET";
        int modulo = Integer.parseInt(dni) % 23;
        String letra = tablaLetras.substring(modulo,modulo+1);
        return letra; 
    }    
    /**
    checkDNI --- Comprobar si un DNI es correcto
      - dni: DNI de origen, puede contener la letra al final o sólo los números
      - devuelve: DNI completo (con letra) o un código de error
    
    Cuando el DNI tiene menos de 8 dígitos debe completar con ceros por la izquierda.
    Si tiene más de 9 caracteres, devuelve el código de error 1.
    Si posee letra pero no es la correcta, devuelve el código de error 2.
    Si no posee letra, se la pone al final.
    Si el DNI es correcto, lo devuelve tal cual.
    */
    public String checkDNI (String dni) throws IllegalArgumentException
    {
        String letra=""; /** usaremos "letra" para alojar la letra del DNI */
        
        /** rellenar la longitud mínima de 8 dígitos, con ceros por la izquierda */
        while (dni.length()<8)
            dni = "0" + dni;

        /** limpiar puntos, comas y guiones */
        if ((dni.contains("."))||(dni.contains(","))||(dni.contains("-")))
        {
            dni = dni.replace(".", "");
            dni = dni.replace(",", "");
            dni = dni.replace("-", "");
        }
        
        if (dni.length()>9) /** Error en DNI tipo 1 - Demasiado largo */
            return "ERRDNI-1";
        else if (dni.length()==9) /** Separar la letra y comprobar si es correcta */
        {
            letra = dni.substring(8);
            dni = dni.substring(0,8);
            if (letra.equals(letraDNI(dni))) /** si la letra coincide con la ok */
                return dni+letra;
            else
                return "ERRDNI-2"; /** Error en DNI tipo 2 - Letra incorrecta */
        }
        else /** calcular la letra y devolver el DNI con la letra */
        {
            letra = letraDNI(dni);
            return dni+letra;
        }
    }
    
    /**
    edad --- Calcula la edad de una persona según el momento actual
      - nacimiento: Fecha de nacimiento en base a la cual se hace el cálculo
      - devuelve: El número entero de años ya cumplidos, que deberá ser...
        ... siempre un valor positivo, cero o -1 si no ha nacido aún.
    */
    public int edad (Date nacimiento)
    {
        Calendar cal = Calendar.getInstance();
        /** año, mes y día de nacimiento */
        cal.setTime(nacimiento);
        int an = cal.get(Calendar.YEAR);
        int mn = cal.get(Calendar.MONTH);
        int dn = cal.get(Calendar.DAY_OF_MONTH);
        /** año, mes y día actuales */
        cal.setTime(new Date());
        int aa = cal.get(Calendar.YEAR);
        int ma = cal.get(Calendar.MONTH);
        int da = cal.get(Calendar.DAY_OF_MONTH);
        /** dif será la edad, que apriori es la diferencia entre el año actual...
        ... y el de nacimiento, aunque habrá que hacer algunas correccioens: */
        int dif = aa-an;
        /** si la fecha de nacimiento es futura, el valor a devolver es -1 */
        if (an>=aa && mn>=ma && dn>=da){
           dif=-1;
           System.out.println(dif);
        /** sino, si el mes de nacimiento es menor que el actual, ...
        ... entonces hay que restar un año */
        }else if (mn>ma){
            dif--;
            System.out.println(dif);
        /** sino, si el mes de nacimiento coincide con el actual y el día de...
        ... nacimiento es anterior, también hay que restar un año */
        }else if (mn==ma && dn>da){
            dif--;
            System.out.println(dif);
        }/** devolvemos los años calculados */
        return dif;
    }  
}