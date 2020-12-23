package ekonomskiFakultet.ui;

import java.util.List;

import ekonomskiFakultet.dao.ProfesorDAO;
import ekonomskiFakultet.model.Profesor;
import ekonomskiFakultet.utils.PomocnaKlasa;

public class ProfesorUI {
	
	public static void menu() {
		int odluka = -1;
		while(odluka != 0) {
			ispisiMeni();
			System.out.println("Izaberite opciju: ");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				System.out.println("Izlaz");
				break;
			case 1:
				prikaziProfesore();
				break;
			case 2:
				unosProfesora();
				break;
			case 3:
				izmenaProfesora();
				break;
			case 4:
				brisanjeProfesora();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}
	
	private static void ispisiMeni() {
		System.out.println("Rad sa profesorima - opcije:");
		System.out.println("\tOpcija broj 1 - Prikazi sve profesore");
		System.out.println("\tOpcija broj 2 - Unos novog Profesora");
		System.out.println("\tOpcija broj 3 - Izmena Profesora");
		System.out.println("\tOpcija broj 4 - Brisanje Profesora");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");
	}
	
	public static Profesor pronadjiProfesora() {
		System.out.println("Unesite id profesora kog zelite da pronadjete:");
		int id = PomocnaKlasa.ocitajCeoBroj();
		try {
			Profesor profesor = ProfesorDAO.getProfesorById(id);
			if(profesor == null) {
				System.out.println("Profesor sa id-jem " + id + " ne postoji." );		
			}
			return profesor;
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}
		return null;
	}
	
	private static void prikaziProfesore() {
		try {
			List<Profesor> profesori = ProfesorDAO.getAll();
			for(Profesor p : profesori) {
				System.out.println(p);
			}
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}
	}
	
	private static void unosProfesora() {
		System.out.println("Unesite ime profesora: ");
		String ime = PomocnaKlasa.ocitajTekst();
		System.out.println("Unesite prezime profesora");
		String prezime = PomocnaKlasa.ocitajTekst();
		System.out.println("Unesite zvanje profesora");
		String zvanje = PomocnaKlasa.ocitajTekst();
		
		Profesor profesor = new Profesor(0, ime, prezime, zvanje);
		try {
			if(ProfesorDAO.add(profesor)) {
				System.out.println("Profesor uspesno dodat");
			}
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}
	}
	
	private static void izmenaProfesora() {
		Profesor profesor = pronadjiProfesora();
		if(profesor != null) {
			System.out.println("Unesite izmenjeno ime za profesora:");
			String ime = PomocnaKlasa.ocitajTekst();
			profesor.setIme(ime);
			System.out.println("Unesite izmenjeno prezime za profesora:");
			//String prezime = PomocnaKlasa.ocitajTekst();
			profesor.setPrezime(PomocnaKlasa.ocitajTekst());
			System.out.println("Unesite izmenjeno zvanje za profesora: ");
			String zvanje = PomocnaKlasa.ocitajTekst();
			profesor.setZvanje(zvanje);
			
			try {
				if(ProfesorDAO.update(profesor)) {
					System.out.println("Profesor uspesno izmenjen");
				}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom");
				e.printStackTrace();
			}
		}
	}
	
	private static void brisanjeProfesora() {
		Profesor profesor = pronadjiProfesora();
		if(profesor != null) {
			try {
				if(ProfesorDAO.delete(profesor.getId())) {
					System.out.println("Profesor uspesno obrisan");
				}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom");
				e.printStackTrace();
			}
		}
	}

}
