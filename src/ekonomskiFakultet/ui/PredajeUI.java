package ekonomskiFakultet.ui;

import java.util.List;

import ekonomskiFakultet.dao.PredajeDAO;
import ekonomskiFakultet.model.Predmet;
import ekonomskiFakultet.model.Profesor;
import ekonomskiFakultet.utils.PomocnaKlasa;

public class PredajeUI {
	
	public static void menu() {
		int odluka = -1;
		while (odluka != 0) {
			ispisiMenu();
			System.out.print("opcija:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				System.out.println("Izlaz");
				break;
			case 1:
				predmetiZaProfesora();
				break;
			case 2: 
				profesoriNaPredmetu();
				break;
			case 3:
				dodavanjeProfesoraNaPredmet();
				break;
			case 4:
				brisanjeProfesoraSaPredmeta();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}
	
	private static void ispisiMenu() {
		System.out.println("Rad sa predavanjima profesora - opcije:");
		System.out.println("\tOpcija broj 1 - predmeti koje profesor predaje");
		System.out.println("\tOpcija broj 2 - profesori koji predaju predmet");
		System.out.println("\tOpcija broj 3 - dodavanje profesora na predmet");
		System.out.println("\tOpcija broj 4 - uklanjanje profesora sa predmeta");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");
	}
	
	private static void predmetiZaProfesora() {
		Profesor profesor = ProfesorUI.pronadjiProfesora();
		
		if(profesor != null) {
			try {
				List<Predmet> predmeti = PredajeDAO.getPredmetiByProfesorId(profesor.getId());
				System.out.println("Profesor " + profesor.getIme() + " " + profesor.getPrezime() + " predaje: ");
				for(Predmet itpr : predmeti) {
					System.out.println(itpr.getNaziv());
				}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom");
				e.printStackTrace();
			}
		}
	}
	
	private static void profesoriNaPredmetu() {
		Predmet predmet = PredmetUI.pronadjiPredmet();
		
		if(predmet != null) {
			try {
				List<Profesor> profesori = PredajeDAO.getProfesoriByPredmetId(predmet.getId());
				System.out.println("Predmet: " + predmet.getNaziv() + " predaju:");
				for(Profesor itpr : profesori) {
					System.out.println(itpr.getZvanje() + " " + itpr.getIme() + " " + itpr.getPrezime());
				}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom");
				e.printStackTrace();
			}
		}
	}
	
	private static void dodavanjeProfesoraNaPredmet() {
		Profesor profesor = ProfesorUI.pronadjiProfesora();
		Predmet predmet = PredmetUI.pronadjiPredmet();
		if(predmet != null && profesor != null) {
			try {
				if (PredajeDAO.add(profesor.getId(), predmet.getId())) {
					System.out.println("Uspesno dodavanje");
				}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom");
				e.printStackTrace();
			}
		}
	}
	
	private static void brisanjeProfesoraSaPredmeta() {
		Profesor profesor = ProfesorUI.pronadjiProfesora();
		Predmet predmet = PredmetUI.pronadjiPredmet();
		if(predmet != null && profesor != null) {
			try {
				if (PredajeDAO.delete(profesor.getId(), predmet.getId())) {
					System.out.println("Uspesno brisanje");
				}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom");
				e.printStackTrace();
			}
		}
	}
	

}
