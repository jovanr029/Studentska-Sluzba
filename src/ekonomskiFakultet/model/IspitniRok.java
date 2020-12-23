package ekonomskiFakultet.model;

import java.sql.Date;

public class IspitniRok {
	
	private int id;
	private String naziv;
	private Date pocetak;
	private Date kraj;
	
	public IspitniRok() {
		super();
	}

	public IspitniRok(int id, String naziv, Date pocetak, Date kraj) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.pocetak = pocetak;
		this.kraj = kraj;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IspitniRok other = (IspitniRok) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IspitniRok [id=" + id + ", naziv=" + naziv + ", pocetak=" + pocetak + ", kraj=" + kraj + "]";
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

	public Date getPocetak() {
		return pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	public Date getKraj() {
		return kraj;
	}

	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}
}
