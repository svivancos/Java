package ev3;
/**
 * 
 * @author Salvy
 * 
 * Prototipo de aplicación para jugar al dominó
 *
 */
public class Domino {

	public Domino(){};
	
	// Fichas disponibles
	private String[] fichas =	{"[Blanca Doble]","[Blanca-Pito]","[Blanca-Dos]","[Blanca-Tres]","[Blanca-Cuatro]","[Blanca-Cinco]","[Blanca-Seis]",
								"[Doble Pito]","[Pito-Dos]","[Pito-Tres]","[Pito-Cuatro]","[Pito-5]","[Pito-6]",
								"[Dos Doble]","[Dos-Tres]","[Dos-Cuatro]","[Dos-5]","[Dos-Seis]",
								"[Tres Doble]","[Tres-Cuatro]","[Tres-Cinco]","[Tres-Seis]",
								"[Cuatro Doble]","[Cuatro-Cinco]","[Cuatro-Seis]",
								"[Cinco Doble]","[Cinco-Seis]","[Seis Doble]"};
	
	// Jugadores y equipos
	public String[] p1t1 = new String[7];
	public String[] p1t2 = new String[7];
	public String[] p2t1 = new String[7];
	public String[] p2t2 = new String[7];
	
	public void repartir(){
		
		// Player 1 Team 1
		for(int i = 0; i < 7; i++){
			// Elegir un número al azar
			int ran = (int) (Math.random()*28);
			
			// Comprobar que no ha salido ya
			if(fichas[ran] == null){
				i--; // Contrarresta el i++
			}else{
				p1t1[i] = fichas[ran]; // Añade al equipo
				fichas[ran] = null; // Anula esa ficha del saco
			}
		}
		
//		System.out.print("Jugador 1 Equipo 1: ");
//		for(int i = 0; i < p1t1.length; i++){
//			System.out.print(p1t1[i]+"  ");
//		}
//		
//		System.out.println();
		
		// Player 1 Team 2
				for(int i = 0; i < 7; i++){
					// Elegir un número al azar
					int ran = (int) (Math.random()*28);
					
					// Comprobar que no ha salido ya
					if(fichas[ran] == null){
						i--; // Contrarresta el i++
					}else{
						p1t2[i] = fichas[ran]; // Añade al equipo
						fichas[ran] = null; // Anula esa ficha del saco
					}
				}
				
//				System.out.print("Jugador 1 Equipo 2: ");
//				for(int i = 0; i < p1t2.length; i++){
//					System.out.print(p1t2[i]+"  ");
//				}
//				
//		System.out.println();
		
		// Player 2 Team 1
				for(int i = 0; i < 7; i++){
					// Elegir un número al azar
					int ran = (int) (Math.random()*28);
					
					// Comprobar que no ha salido ya
					if(fichas[ran] == null){
						i--; // Contrarresta el i++
					}else{
						p2t1[i] = fichas[ran]; // Añade al equipo
						fichas[ran] = null; // Anula esa ficha del saco
					}
				}
				
//				System.out.print("Jugador 2 Equipo 1: ");
//				for(int i = 0; i < p2t1.length; i++){
//					System.out.print(p2t1[i]+"  ");
//				}
//		
//		System.out.println();
		
		// Player 2 Team 2
				for(int i = 0; i < 7; i++){
					// Elegir un número al azar
					int ran = (int) (Math.random()*28);
					
					// Comprobar que no ha salido ya
					if(fichas[ran] == null){
						i--; // Contrarresta el i++
					}else{
						p2t2[i] = fichas[ran]; // Añade al equipo
						fichas[ran] = null; // Anula esa ficha del saco
					}
				}
				
//				System.out.print("Jugador 2 Equipo 2: ");
//				for(int i = 0; i < p2t2.length; i++){
//					System.out.print(p2t2[i]+"  ");
//				}
		}
	
	public String[] getFichasp1t1(){
		return p1t1;
	}
	
	public String[] getFichasp1t2(){
		return p1t2;
	}
	
	public String[] getFichasp2t1(){
		return p2t1;
	}
	
	public String[] getFichasp2t2(){
		return p2t2;
	}
	
	public void quienAbre(){
		for(int i = 0; i < 7; i++){
			if(p1t1[i] == "[Seis Doble]"){
				System.out.println("Abre el Jugador 1 del Equipo 1");
			}else if(p1t2[i] == "[Seis Doble]"){
				System.out.println("Abre el Jugador 1 del Equipo 2");
			}else if(p2t1[i] == "[Seis Doble]"){
				System.out.println("Abre el Jugador 2 del Equipo 1");
			}else if(p2t2[i] == "[Seis Doble]"){
				System.out.println("Abre el Jugador 2 del Equipo 2");
			}
		}
	}
}
