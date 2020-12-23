package ekonomskiFakultet.ui;

import java.util.List;

import ekonomskiFakultet.dao.PredmetDAO;
import ekonomskiFakultet.model.Predmet;
import ekonomskiFakultet.utils.PomocnaKlasa;

public class PredmetUI {
	
	public static void menu() {
		int odluka = -1;
		while (odluka != 0) {
			ispisiMeni();
			System.out.print("opcija: ");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				System.out.println("Izlaz");
				break;
			case 1:
				prikaziSvePredmete();
				break;
			case 2:
				prikaziSvePredmeteSortirano();
				break;
			case 3:
				unosPredmeta();
				break;
			case 4:
				izmenaPredmeta();
				break;
			case 5:
				brisanjePredmeta();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
		
	}

	private static void ispisiMeni() {
		System.out.println("Rad sa predmetima - opcije:");
		System.out.println("\tOpcija broj 1 - ispis svih Predmeta");
		System.out.println("\tOpcija broj 2 - ispis svih Predmeta sortiranih po nazivu");
		System.out.println("\tOpcija broj 3 - unos novog Predmeta");
		System.out.println("\tOpcija broj 4 - izmena naziva Predmeta");
		System.out.println("\tOpcija broj 5 - brisanje Predmeta");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");	
	}
	
	public static Predmet pronadjiPredmet() {
		System.out.print("Unesite id predmeta: ");
		int id = PomocnaKlasa.ocitajCeoBroj();
		try {
			Predmet predmet = PredmetDAO.getPredmetById(id);
			if (predmet == null) {
				System.out.println("Predmet sa id-jem " + id + " ne postoji u evidenciji");
			}
			return predmet;
		} catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");
			ex.printStackTrace();
		}
		return null;
	}
	
	private static void prikaziSvePredmete() {
		try {
			List<Predmet> predmeti = PredmetDAO.getAll();
			for(Predmet p : predmeti) {
				System.out.println(p);
			}
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}
	}
	
	private static void prikaziSvePredmeteSortirano() {
		try {
			List<Predmet> predmeti = PredmetDAO.getAllSortedByNaziv();
			for(Predmet p : predmeti) {
				System.out.println(p);
			}
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}
	}
	
	private static void unosPredmeta() {
		
		System.out.println("Unesite naziv predmeta");
		String naziv = PomocnaKlasa.ocitajTekst();
		
		try {
			Predmet predmet = PredmetDAO.getPredmetByNaziv(naziv);
			
			if(predmet != null) {
				System.out.println("Predmet sa nazivom " + naziv + " vec postoji u bazi podataka.");
				return;
			}	
			predmet = new Predmet(0, naziv);
			
			if(PredmetDAO.add(predmet)) {
				System.out.println("Predmet uspesno sacuvan");
			}
		} catch (Exception e) {		
			e.printStackTrace();
		}
	}
	
	private static void izmenaPredmeta() {
		Predmet predmet = pronadjiPredmet();
		if(predmet == null) {
			return;
		}
		try {
			System.out.println("Unesite novo ime za predmet:");
			predmet.setNaziv(PomocnaKlasa.ocitajTekst());
			
			if(PredmetDAO.update(predmet)) {
				System.out.println("Uspesno izmenjen naziv predmeta");
			}	
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}
	}
	
	private static void brisanjePredmeta() {
		
		System.out.println("Unesite id predmeta koji zelite da izbrisete");
		int id = PomocnaKlasa.ocitajCeoBroj();
		try {
			Predmet predmet = PredmetDAO.getPredmetById(id);
			
			if(predmet == null) {
				System.out.println("Ne postoji predmet sa zadatim id-jem" + id);
				return;
			}
			if(PredmetDAO.delete(id)) {
				System.out.println("Uspesno izbrisan predmet");
			}	
			
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}
		
	}
}
