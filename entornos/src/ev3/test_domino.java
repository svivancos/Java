package ev3;
public class test_domino {
	public static void main(String[] args) {

		System.out.println("Partida de Dominó: 2 vs 2");
		
		Domino domino = new Domino();
		domino.repartir();
		
		System.out.println("Se reparten las fichas: ");
	
		System.out.print("Jugador 1 del Equipo 1: ");
		String[] ju1eq1 = domino.getFichasp1t1();
		for(int i = 0; i < ju1eq1.length; i++){
			System.out.print(ju1eq1[i]+"  ");
		}
		System.out.println();
		
		System.out.print("Jugador 1 del Equipo 2: ");
		String[] ju1eq2 = domino.getFichasp1t2();
		for(int i = 0; i < ju1eq2.length; i++){
			System.out.print(ju1eq2[i]+"  ");
		}
		System.out.println();
		
		System.out.print("Jugador 2 del Equipo 1: ");
		String[] ju2eq1 = domino.getFichasp2t1();
		for(int i = 0; i < ju2eq1.length; i++){
			System.out.print(ju2eq1[i]+"  ");
		}
		System.out.println();
		
		System.out.print("Jugador 2 del Equipo 2: ");
		String[] ju2eq2 = domino.getFichasp2t2();
		for(int i = 0; i < ju2eq2.length; i++){
			System.out.print(ju2eq2[i]+"  ");
		}
		
		System.out.println();
		domino.quienAbre();
		
	}
}
	
