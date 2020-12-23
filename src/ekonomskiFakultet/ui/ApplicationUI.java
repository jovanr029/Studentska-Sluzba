package ekonomskiFakultet.ui;

import ekonomskiFakultet.dao.ConnectionManager;
import ekonomskiFakultet.utils.PomocnaKlasa;

public class ApplicationUI {

	public static void main(String[] args) {
		
		try {
			ConnectionManager.open();
		} catch (Exception e) {
			System.out.println("Neuspesna konekcija na bazu");
			e.printStackTrace();
			return;
		}
		
		int odluka = -1;
		while (odluka != 0 ) {
			ApplicationUI.ispisiMenu();
			
			System.out.println("Izaberite opciju:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			
			switch (odluka) {
			case 0: 
				System.out.println("Izlaz iz programa");
				break;
			case 1:	
				StudentUI.menu();
				break;
			case 2:
				ProfesorUI.menu();
				break;
			case 3:
				PredmetUI.menu();
				break;
			case 4: 
				IspitniRokUI.menu();
				break;
			case 5:
				PohadjaUI.menu();
				break;
			case 6:
				PredajeUI.menu();
				break;
			case 7: 
				IspitnaPrijavaUI.menu();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}

		}
	}
	public static void ispisiMenu() {
		System.out.println("Studentska Sluzba - Osnovne opcije:");
		System.out.println("\tOpcija broj 1 - Rad sa studentima");
		System.out.println("\tOpcija broj 2 - Rad sa profesorima");
		System.out.println("\tOpcija broj 3 - Rad sa predmetima");
		System.out.println("\tOpcija broj 4 - Rad sa ispitnim rokovima");
		System.out.println("\tOpcija broj 5 - Rad sa pohadjanjem predmeta");
		System.out.println("\tOpcija broj 6 - Rad sa predavanjem profesora");
		System.out.println("\tOpcija broj 7 - Rad sa ispitnim prijavama studenta");
		System.out.println("\tOpcija broj 7 - Rad sa ispitnim prijavama studenta");
		System.out.println("\tOpcija broj 7 - Rad sa ispitnim prijavama studenta");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
	}
}
