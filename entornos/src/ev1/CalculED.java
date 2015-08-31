package ev1;
import java.util.Scanner;
public class CalculED {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int opc, n1, n2, res;
		
		do{
			System.out.println("1. Sumar");
			System.out.println("2. Restar");
			System.out.println("3. Salir");
			System.out.print("Indique una opción> ");
			opc = teclado.nextInt();
			
			switch(opc){
				case 1:
					System.out.print("Dame un número: ");
					n1 = teclado.nextInt();
					System.out.print("Dame otro: ");
					n2 = teclado.nextInt();
					res = n1 + n2;
					System.out.println("El resultado es "+res);
					break;
				case 2:
					System.out.print("Dame un número: ");
					n1 = teclado.nextInt();
					System.out.print("Dame otro: ");
					n2 = teclado.nextInt();
					res = n1 - n2;
					System.out.println("El resultado es "+res);
					break;
				case 3:
					break;
				default:
					System.out.println("Opción hincorrecta");
					break;
			}
		}while(opc != 3);
	}
}
