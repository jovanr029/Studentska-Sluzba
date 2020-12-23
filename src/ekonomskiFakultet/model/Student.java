package ekonomskiFakultet.model;

import java.util.ArrayList;
import java.util.List;

public class Student {

	private int id;
	private String ime;
	private String prezime;
	private String grad;
	private String index;
	
	private List<Predmet> predmeti = new ArrayList<>();
	private List<IspitnaPrijava> prijave = new ArrayList<>();
	
	public Student() {
		super();
	}

	public Student(int id, String ime, String prezime, String grad, String index) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.grad = grad;
		this.index = index;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", grad=" + grad + ", index=" + index
				+ ", predmeti=" + predmeti + ", prijave=" + prijave + "]";
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

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	public List<IspitnaPrijava> getPrijave() {
		return prijave;
	}

	public void setPrijave(List<IspitnaPrijava> prijave) {
		this.prijave = prijave;
	}
	
}
