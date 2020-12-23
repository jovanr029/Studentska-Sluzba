package ekonomskiFakultet.model;

import java.util.ArrayList;
import java.util.List;

public class Profesor {
	
	private int id;
	private String ime;
	private String prezime;
	private String zvanje;
	
	private List<Predmet> predmeti = new ArrayList<>();

	public Profesor() {
		super();
	}

	public Profesor(int id, String ime, String prezime, String zvanje) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.zvanje = zvanje;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Profesor [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", zvanje=" + zvanje + ", predmeti="
				+ predmeti + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}	

}
