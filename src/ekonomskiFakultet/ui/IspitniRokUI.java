package ekonomskiFakultet.ui;

import java.sql.Date;
import java.util.List;

import ekonomskiFakultet.dao.IspitnaPrijavaDao;
import ekonomskiFakultet.dao.IspitniRokDAO;
import ekonomskiFakultet.model.IspitniRok;
import ekonomskiFakultet.utils.PomocnaKlasa;

public class IspitniRokUI {
	
	public static void menu() {
		int odluka = -1;
		while(odluka != 0) {
			ispisiMeni();
			System.out.println("Opcija: ");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			
			switch (odluka) {
			case 1:
				ispisiRokove();
				break;
			case 2:
				unesiIspitniRok();
				break;
			case 3:
				izmeniIspitniRok();
				break;
			case 4:
				izbrisiIspitniRok();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}			
		}
		
	}
	
	private static void ispisiMeni() {
		System.out.println("Rad sa ispitnim rokovima - opcije:");
		System.out.println("\tOpcija broj 1 - ispis svih Ispitnih rokova");
		System.out.println("\tOpcija broj 2 - unos novog Ispitnog roka");
		System.out.println("\tOpcija broj 3 - izmena Ispitnog Roka");
		System.out.println("\tOpcija broj 4 - brisanje Ispitnog Roka");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");	
	}
	
	public static IspitniRok pronadjiIspitniRok() {
		System.out.print("Unesi id ispitnog roka: ");
		int id = PomocnaKlasa.ocitajCeoBroj();
		try {
			IspitniRok rok = IspitniRokDAO.getIspitniRokById(id);
			if (rok == null) {
				System.out.println("Ispitni rok sa id-em " + id + " ne postoji u evidenciji");
			}
			return rok;
		} catch (Exception ex) {
			System.out.println("Greska u radu sa bazom!");
		}
		return null;
	}
	
	private static void ispisiRokove() {	
		try {
			List<IspitniRok> ispitniRokovi = IspitniRokDAO.getAll();
			for(IspitniRok itRok : ispitniRokovi) {
				System.out.println(itRok);
			}
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}
	}
	
	private static void izmeniIspitniRok() {
		IspitniRok ispitniRok = pronadjiIspitniRok();
		if(ispitniRok != null) {
			System.out.println("Unesite novi naziv za ispitni rok:");
			ispitniRok.setNaziv(PomocnaKlasa.ocitajTekst());
			
			System.out.println("Unesite novi datum za pocetak ispitnog roka:");
			ispitniRok.setPocetak(new Date(PomocnaKlasa.ocitajDatum().getTime()));
			
			System.out.println("Unesite novi datum za zavrsetak ispitnog roka:");
			ispitniRok.setKraj(new Date(PomocnaKlasa.ocitajDatum().getTime()));
			
			try {
				if(IspitniRokDAO.update(ispitniRok)) {
					System.out.println("Ispitni rok uspesno izmenjen");
				}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom");
				e.printStackTrace();
			}
		}
	}
	
	private static void unesiIspitniRok() {
		System.out.println("Unesite naziv ispitnog roka:");
		String naziv = PomocnaKlasa.ocitajTekst();
		System.out.println("Unesite pocetak ispitnog roka:");
		Date pocetak = new Date(PomocnaKlasa.ocitajDatum().getTime());
		System.out.println("Unesite kraj ispitnog roka:");
		Date kraj = new Date(PomocnaKlasa.ocitajDatum().getTime());
		
		IspitniRok iRok = new IspitniRok(0, naziv, pocetak, kraj);
		try {
			if(IspitniRokDAO.add(iRok)) {
				System.out.println("Ispitni rok uspesno dodat");
			};
		} catch (Exception e) {
			System.out.println("Greska u radu sa bazom");
			e.printStackTrace();
		}
		
	}
	
	private static void izbrisiIspitniRok() {
		IspitniRok ispitniRok = pronadjiIspitniRok();
		if(ispitniRok != null) {
			try {
				IspitnaPrijavaDao.deleteAllIspitnePrijaveForIspitniRok(ispitniRok.getId());
				if(IspitniRokDAO.delete(ispitniRok.getId())) {
					System.out.println("Uspesno obrisan ispitni rok");
				}
			} catch (Exception e) {
				System.out.println("Greska u radu sa bazom");
				e.printStackTrace();
			}
		}
	}

}
