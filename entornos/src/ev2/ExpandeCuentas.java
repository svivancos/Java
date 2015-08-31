package ev2;
import javax.swing.JOptionPane;

/**
 *
 * @author jlcruz-cifp
 */
public class ExpandeCuentas
{
    private static final int DIG_N1 = 1;
    private static final int DIG_N2 = 3;
    private static final int DIG_N3 = 5;
    private static final int DIG_N4 = 10;

    public static void main(String[] args)
    {
        // [N1]  - <nodo>
        String cuenta = "";
        String prefijo = "";
        String sufijo = "";
        String relleno = "";
        int pospunto = 0;
        int longitud = 0;

        // [N2]  - <nodo>
        do // bucle del menú principal
        {
            // preguntamos por la cuenta
            cuenta = JOptionPane.showInputDialog("Indique la cuenta a expandir:");
            // buscamos el separador de expansión
            pospunto = cuenta.indexOf('.');
            // voy a evaluar si he puesto el separador de expansión o no
            if (pospunto>0) // si hay separador
            {
                // [N3]  - <nodo>
                // evaluamos lo que h<ay antes y después del separador
                prefijo = cuenta.substring(0,pospunto); 
                sufijo = cuenta.substring(pospunto+1);
                // vemos cuantos ceros hacen falta, completando relleno con esos ceros
                relleno = "";
                longitud = prefijo.length()+sufijo.length();
                // rellenamos para que alcance el nivel 4
                // [N4]  - <nodo>
                while (longitud<DIG_N4)
                {
                    // [N5]  - <nodo>
                    relleno = relleno+"0";
                    longitud++;
                }
                // [N6]  - <nodo>
                // recomponemos todo
                cuenta = prefijo+relleno+sufijo;
            }
            // [N7]  - <nodo>
            // mostramos el resultado
            if (!cuenta.equals(""))
                // [N8]  - <nodo>
                JOptionPane.showMessageDialog(null,"La cuenta expandida es "+cuenta,"Información",JOptionPane.INFORMATION_MESSAGE);
        }
        // [N9]  - <nodo>
        while (!cuenta.equals(""));
        // [N10]  - <nodo>
    }
}