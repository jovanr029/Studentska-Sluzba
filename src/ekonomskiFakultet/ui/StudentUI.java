package ekonomskiFakultet.ui;

import java.util.List;

import ekonomskiFakultet.dao.IspitnaPrijavaDao;
import ekonomskiFakultet.dao.PohadjaDAO;
import ekonomskiFakultet.dao.StudentDAO;
import ekonomskiFakultet.model.Student;
import ekonomskiFakultet.utils.PomocnaKlasa;

public class StudentUI {
	
	public static void menu() {
		int odluka = -1;
		while(odluka != 0) {
			ispisiMenu();
			System.out.println("Izaberite opciju:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				System.out.println("Izlaz");
				break;
			case 1: 
				prikaziSveStudente();
				break;
			case 2: 
				unosStudenta();
				break;
			case 3: 
				izmenaStudenta();
				break;
			case 4:
				izbrisiStudenta();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}
	
	private static void ispisiMenu() {
		System.out.println("Rad sa studentima - opcije:");
		System.out.println("\tOpcija broj 1 - Prikaz svih Studenata");
		System.out.println("\tOpcija broj 2 - Unos novog Studenta");
		System.out.println("\tOpcija broj 3 - Izmena Studenta");
		System.out.println("\tOpcija broj 4 - Brisanje Studenta");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");
	}
	
	public static Student pronadjiStudenta() {
		System.out.print("Unesi indeks studenta: ");
		String stIndex = PomocnaKlasa.ocitajTekst();
		try {
			Student student = StudentDAO.getStudentByIndex(stIndex);
			if (student == null) {
				System.out.println("Student sa indeksom " + stIndex + " ne postoji u evidenciji");
			}
			return student;
		} catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");
			ex.printStackTrace();
		}
		return null;
	}
	
	private static void prikaziSveStudente() {
		try {
			List<Student> studenti = StudentDAO.getAll();
			for(Student s : studenti) {
				System.out.println(s);
			}
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}	
	}
	
	private static void unosStudenta() {
		System.out.println("Unesite indeks studenta: ");
		String indeks = PomocnaKlasa.ocitajTekst();		
		try {
			Student student = StudentDAO.getStudentByIndex(indeks);
			
			if(student != null) {
				System.out.println("Student sa indeksom " + indeks + " postoji u bazi podataka.");
				return;
			}
			
			System.out.println("Unesite ime studenta: ");
			String ime = PomocnaKlasa.ocitajTekst();
			
			System.out.println("Unesite prezime studenta: ");
			String prezime = PomocnaKlasa.ocitajTekst();
			
			System.out.println("Unesite grad studenta: ");
			String grad = PomocnaKlasa.ocitajTekst();
			
			student = new Student(0, ime, prezime, grad, indeks);
			if(StudentDAO.add(student)) {
				System.out.println("Student uspesno upisan");
			}
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}
		
	}
	
	private static void izmenaStudenta() {
		
		try {
			Student student = pronadjiStudenta();
			
			if(student == null) {
				return;
			}
			
			System.out.println("Unesite novi indeks studenta: ");
			String indeks = PomocnaKlasa.ocitajTekst();		
			student.setIndex(indeks);
			
			System.out.println("Unesite ime studenta: ");
			String ime = PomocnaKlasa.ocitajTekst();
			student.setIme(ime);
			
			System.out.println("Unesite prezime studenta: ");
			String prezime = PomocnaKlasa.ocitajTekst();
			student.setPrezime(prezime);
			
			System.out.println("Unesite grad studenta: ");
			String grad = PomocnaKlasa.ocitajTekst();
			student.setGrad(grad);
			
			if(StudentDAO.update(student)) {
				System.out.println("Student uspesno izmenjen");
			}
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}
		
	}
	
	private static void izbrisiStudenta () {
		Student student = pronadjiStudenta();
		try {
			if(student != null) {
				PohadjaDAO.deletePohadjanjaByStudentId(student.getId());
				IspitnaPrijavaDao.deleteAllIspitnePrijaveForStudent(student.getId());
				if(StudentDAO.delete(student.getId())) {
					System.out.println("Uspesno obrisan student");
				}
			}
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}	
	}
	
}
