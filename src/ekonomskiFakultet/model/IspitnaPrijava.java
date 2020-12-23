package ekonomskiFakultet.model;

public class IspitnaPrijava {
	
	private Predmet predmet;
	private IspitniRok ispitniRok;
	private Student student;
	
	private int teorija;
	private int zadaci;
	
	public IspitnaPrijava() {
		super();
	}

	public IspitnaPrijava(Predmet predmet, IspitniRok ispitniRok, Student student, int teorija, int zadaci) {
		super();
		this.predmet = predmet;
		this.ispitniRok = ispitniRok;
		this.student = student;
		this.teorija = teorija;
		this.zadaci = zadaci;
	}
	
	@Override
	public String toString() {
		return "IspitnaPrijava [predmet=" + predmet + ", ispitniRok=" + ispitniRok + ", student=" + student
				+ ", teorija=" + teorija + ", zadaci=" + zadaci + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof IspitnaPrijava)) return false;

		IspitnaPrijava that = (IspitnaPrijava) obj;
		return that.predmet.equals(this.predmet) && that.ispitniRok.equals(this.ispitniRok) && that.student.equals(this.student);
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public IspitniRok getIspitniRok() {
		return ispitniRok;
	}

	public void setIspitniRok(IspitniRok ispitniRok) {
		this.ispitniRok = ispitniRok;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getTeorija() {
		return teorija;
	}

	public void setTeorija(int teorija) {
		this.teorija = teorija;
	}

	public int getZadaci() {
		return zadaci;
	}

	public void setZadaci(int zadaci) {
		this.zadaci = zadaci;
	}
	
	public int izracunajOcenu() {
		int bodovi = teorija + zadaci;

		int ocena;
		if (bodovi >= 95)
			ocena = 10;
		else if (bodovi >= 85)
			ocena = 9;
		else if (bodovi >= 75)
			ocena = 8;
		else if (bodovi >= 65)
			ocena = 7;
		else if (bodovi >= 55)
			ocena = 6;
		else
			ocena = 5;

		return ocena;
	}
	
}
