// VERSIÓN 1.0
package ev1;
import java.util.Scanner;
public class ExpandeCuenta {
	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("NIVELES");
		System.out.println("1. Cuenta infantil (1x)");
		System.out.println("2. Cuenta oro (3x)");
		System.out.println("3. Cuenta platinum (5x)");
		System.out.println("4. Cuenta black (10x)");
		System.out.println("5. Salir");
		System.out.print("Escoge un nivel> ");
		int opc = teclado.nextInt();
		
		// Códigos y su número de dígitos y demás variables
		int cod1 = 1, cod2 = 3, cod3 = 5, cod4 = 10;
		int ent, dec, n_largo, n_ceros;
		double notacion;
		String cod = "";
		String str_num = "";
		String[] cortar;
		String ceros = "";
		String salida = "";
		
		switch(opc){
			case 1:
				System.out.print("Introduce la notación: ");
				notacion = teclado.nextDouble();
				
				str_num = Double.toString(notacion);
				cortar = str_num.split("\\.");
				ent = Integer.parseInt(cortar[0]);
				dec = Integer.parseInt(cortar[1]);

				n_largo = cortar[0].length() + cortar[1].length();
				n_ceros = cod1 - n_largo;
				for(int i = 0; i < n_ceros; i++){
					ceros += "0";
				}
				
				salida = cortar[0] + ceros + cortar[1];
				System.out.print(salida);
				break;
			case 2:
				System.out.print("Introduce la notación: ");
				notacion = teclado.nextDouble();
				
				str_num = Double.toString(notacion);
				cortar = str_num.split("\\.");
				ent = Integer.parseInt(cortar[0]);
				dec = Integer.parseInt(cortar[1]);

				n_largo = cortar[0].length() + cortar[1].length();
				n_ceros = cod2 - n_largo;
				for(int i = 0; i < n_ceros; i++){
					ceros += "0";
				}

				salida = cortar[0] + ceros + cortar[1];
				System.out.print(salida);
				break;
			case 3:
				System.out.print("Introduce la notación: ");
				notacion = teclado.nextDouble();
				
				// Pasar a <str>, dividir en 2 y asignar a <int>
				str_num = Double.toString(notacion);
				cortar = str_num.split("\\.");
				ent = Integer.parseInt(cortar[0]);
				dec = Integer.parseInt(cortar[1]);
				
				// Contar <0> restantes y añadirlos
				n_largo = cortar[0].length() + cortar[1].length();
				n_ceros = cod3 - n_largo;
				for(int i = 0; i < n_ceros; i++){
					ceros += "0";
				}
				
				// Unión final y salida
				salida = cortar[0] + ceros + cortar[1];
				System.out.print(salida);
				break;
			case 4:
				System.out.print("Introduce la notación: ");
				notacion = teclado.nextDouble();

				str_num = Double.toString(notacion);
				cortar = str_num.split("\\.");
				ent = Integer.parseInt(cortar[0]);
				dec = Integer.parseInt(cortar[1]);

				n_largo = cortar[0].length() + cortar[1].length();
				n_ceros = cod4 - n_largo;
				for(int i = 0; i < n_ceros; i++){
					ceros += "0";
				}

				salida = cortar[0] + ceros + cortar[1];
				System.out.print(salida);
				break;
			case 5:
				System.out.println("Aplicación cerrada con éxito");
				break;
			default:
				System.out.println("La opción no es válida");
				break;
			}
	}
}
