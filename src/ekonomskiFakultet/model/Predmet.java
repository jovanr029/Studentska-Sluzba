package ekonomskiFakultet.model;

import java.util.ArrayList;
import java.util.List;

public class Predmet {
	
	private int id;
	private String naziv;
	
	private List<Student> studenti = new ArrayList<>();
	private List<Profesor> profesori = new ArrayList<>();
	
	public Predmet() {
		super();
	}

	public Predmet(int id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Predmet other = (Predmet) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Predmet [id=" + id + ", naziv=" + naziv + ", studenti=" + studenti + ", profesori=" + profesori + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}

	public List<Profesor> getProfesori() {
		return profesori;
	}

	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}

}
