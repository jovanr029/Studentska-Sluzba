package ekonomskiFakultet.ui;

import java.util.List;

import ekonomskiFakultet.dao.IspitnaPrijavaDao;
import ekonomskiFakultet.model.IspitnaPrijava;
import ekonomskiFakultet.model.IspitniRok;
import ekonomskiFakultet.model.Predmet;
import ekonomskiFakultet.model.Student;
import ekonomskiFakultet.utils.PomocnaKlasa;

public class IspitnaPrijavaUI {
	
	public static void menu() {
		int odluka = -1;
		while (odluka != 0) {
			ispisiMenu();
			System.out.print("opcija: ");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				System.out.println("Izlaz");
				break;
			case 1:
				prikaziIspitnePrijaveStudent();		
				break;
			case 2:		
				prikaziIspitnePrijavePredmet();
				break;
			case 3:
				prikaziIspitnePrijaveIspitniRok();
				break;
			case 4:
				dodavanjeIspitnePrijave();
				break;
			case 5:
				izmenaIspitnePrijave();
				break;
			case 6:
				obrisiIspitnuPrijavu();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}
	
	private static void ispisiMenu() {
		System.out.println("Rad sa ispitnim prijavama studenta - opcije:");
		System.out.println("\tOpcija broj 1 - ispitne prijave studenta");
		System.out.println("\tOpcija broj 2 - ispitne prijave za predmet");
		System.out.println("\tOpcija broj 3 - ispitne prijave u roku");
		System.out.println("\tOpcija broj 4 - dodavanje ispitne prijave");
		System.out.println("\tOpcija broj 5 - izmena ispitne prijave");
		System.out.println("\tOpcija broj 6 - uklanjanje ispitne prijave");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");
	}
	
	private static void prikaziIspitnePrijaveStudent() {
		Student student = StudentUI.pronadjiStudenta();
		 try {
			List<IspitnaPrijava> ispitnePrijave = IspitnaPrijavaDao.getIspitnePrijaveByStudent(student);
		
			System.out.println("Ispitne prijave za studenta: " + student.getIme() + " " + student.getPrezime());
			
			for(IspitnaPrijava ip : ispitnePrijave) {
				System.out.println(ip.getPredmet().getNaziv() + " " + ip.getIspitniRok().getNaziv() + " teorija: " + ip.getTeorija() + " zadaci: " + ip.getZadaci());
			}
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}
	}
	
	private static void prikaziIspitnePrijavePredmet() {
		Predmet predmet = PredmetUI.pronadjiPredmet();
		
		 try {
				List<IspitnaPrijava> ispitnePrijave = IspitnaPrijavaDao.getIspitnePrijaveByPredmet(predmet);
				System.out.println("Ispitna prijava za predmet: " + predmet.getNaziv());
				for(IspitnaPrijava ip : ispitnePrijave) {
					System.out.println(ip.getStudent().getIme() + " " + ip.getStudent().getPrezime() + " " + ip.getIspitniRok().getNaziv() + " teorija: " + ip.getTeorija() + " zadaci: " + ip.getZadaci());
				}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom");
				e.printStackTrace();
			}
	}
	
	private static void prikaziIspitnePrijaveIspitniRok() {
		IspitniRok ispitniRok = IspitniRokUI.pronadjiIspitniRok();
		
		 try {
				List<IspitnaPrijava> ispitnePrijave = IspitnaPrijavaDao.getIspitnePrijaveByIspitniRok(ispitniRok);
				System.out.println("Ispitna prijava za ispitni rok: " + ispitniRok.getNaziv());
				for(IspitnaPrijava ip : ispitnePrijave) {
					System.out.println(ip.getStudent().getIme() + " " + ip.getStudent().getPrezime() + " " + ip.getPredmet().getNaziv() + " teorija: " + ip.getTeorija() + " zadaci: " + ip.getZadaci());
				}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom");
			}
	}
	
	private static void dodavanjeIspitnePrijave() {
		
		Student student = StudentUI.pronadjiStudenta();
		if(student == null) return;
		Predmet predmet = PredmetUI.pronadjiPredmet();
		if(predmet == null) return;
		IspitniRok ispitniRok = IspitniRokUI.pronadjiIspitniRok();
		if(ispitniRok == null) return;
		
		System.out.println("Unesite broj bodova iz teorije: ");
		int bodTeorija = PomocnaKlasa.ocitajCeoBroj();
		
		System.out.println("Unesite broj bodova iz zadataka: ");
		int bodZadatak = PomocnaKlasa.ocitajCeoBroj();
				
		IspitnaPrijava ispitnaPrijava = new IspitnaPrijava(predmet, ispitniRok, student, bodTeorija, bodZadatak);
		try {
			if(IspitnaPrijavaDao.add(ispitnaPrijava)) {
				System.out.println("Ispitna prijava uspesno dodata");
			}
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}
	}
	
	private static void izmenaIspitnePrijave() {
	
		Student student = StudentUI.pronadjiStudenta();
		if(student == null) return;
		Predmet predmet = PredmetUI.pronadjiPredmet();
		if(predmet == null) return;
		IspitniRok ispitniRok = IspitniRokUI.pronadjiIspitniRok();
		if(ispitniRok == null) return;
		
		System.out.println("Unesite izmenjen broj bodova iz teorije: ");
		int bodTeorija = PomocnaKlasa.ocitajCeoBroj();
		
		System.out.println("Unesite izmenjen broj bodova iz zadataka: ");
		int bodZadatak = PomocnaKlasa.ocitajCeoBroj();
				
		IspitnaPrijava ispitnaPrijava = new IspitnaPrijava(predmet, ispitniRok, student, bodTeorija, bodZadatak);
		try {
			if(IspitnaPrijavaDao.update(ispitnaPrijava)) {
				System.out.println("Ispitna prijava uspesno izmenjena");
			}
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}
	}
	
	private static void obrisiIspitnuPrijavu() {
		
		Student student = StudentUI.pronadjiStudenta();
		if(student == null) return;
		Predmet predmet = PredmetUI.pronadjiPredmet();
		if(predmet == null) return;
		IspitniRok ispitniRok = IspitniRokUI.pronadjiIspitniRok();
		if(ispitniRok == null) return;
		
		try {
			if(IspitnaPrijavaDao.delete(student.getId(), predmet.getId(), ispitniRok.getId())) {
				System.out.println("Ispitna prijava uspesno izmenjena");
			}
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}
		
	}

}
