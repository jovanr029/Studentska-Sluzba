package ekonomskiFakultet.ui;

import java.util.List;

import ekonomskiFakultet.dao.PohadjaDAO;
import ekonomskiFakultet.model.Predmet;
import ekonomskiFakultet.model.Student;
import ekonomskiFakultet.utils.PomocnaKlasa;

public class PohadjaUI {
	
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
				ispisiPredmeteZaStudenta();
				break;
			case 2: 
				ispisiStudenteZaPredmet();
				break;
			case 3:
				dodajStudentaNaPredmet();
				break;
			case 4:
				obrisiStudentaSaPredmeta();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}
	
	private static void ispisiMenu() {
		System.out.println("Rad sa pohadjanjem predmeta studenta - opcije:");
		System.out.println("\tOpcija broj 1 - predmeti koje student pohadja");
		System.out.println("\tOpcija broj 2 - studenti koji pohadjaju predmet");
		System.out.println("\tOpcija broj 3 - dodavanje studenta na predmet");
		System.out.println("\tOpcija broj 4 - uklanjanje studenta sa predmeta");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");
	}
	
	private static void ispisiPredmeteZaStudenta () {
		Student student = StudentUI.pronadjiStudenta();
		if(student != null) {
			try {
				List<Predmet> predmeti = PohadjaDAO.getPredmetiByStudentId(student.getId());
				System.out.println("Predmeti koje pohadja student: " + student.getIme() + " " + student.getPrezime());
				for(Predmet itPredmet : predmeti) {
					System.out.println(itPredmet.getNaziv());
				}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom");
				e.printStackTrace();
			}
		}
	}
	
	private static void ispisiStudenteZaPredmet() {
		Predmet predmet = PredmetUI.pronadjiPredmet();
		if(predmet != null) {
			try {
				List<Student> studenti = PohadjaDAO.getStudentiByPredmetId(predmet.getId());
				System.out.println("Spisak studenata na predmetu: " + predmet.getNaziv());
				for(Student itStudent : studenti) {
					System.out.println(itStudent.getIme() + " " + itStudent.getPrezime() + " " + itStudent.getIndex());
				}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom");
				e.printStackTrace();
			}
		}
	}
	
	private static void dodajStudentaNaPredmet() {
		Student student = StudentUI.pronadjiStudenta();
		Predmet predmet = PredmetUI.pronadjiPredmet();
		
		if(student != null && predmet != null) {
			try {
				if(PohadjaDAO.add(student.getId(), predmet.getId())) {
					System.out.println("Dodavanje uspesno");
				};
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom");
				e.printStackTrace();
			}
		}
	}
	
	private static void obrisiStudentaSaPredmeta() {
		Student student = StudentUI.pronadjiStudenta();
		Predmet predmet = PredmetUI.pronadjiPredmet();
		
		if(student != null && predmet != null) {
			try {
				if(PohadjaDAO.delete(student.getId(), predmet.getId())) {
					System.out.println("Brisanje uspesno");
				};
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom");
				e.printStackTrace();
			}
		}
	}

}
