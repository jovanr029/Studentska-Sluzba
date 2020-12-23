package ekonomskiFakultet.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ekonomskiFakultet.model.IspitniRok;

public class IspitniRokDAO {
	
	public static List<IspitniRok> getAll() throws Exception {		
		List<IspitniRok> ispitniRokovi = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT rok_id, naziv, pocetak, kraj FROM ispitni_rokovi";
			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String naziv = rset.getString(index++);
				Date pocetak = rset.getDate(index++);
				Date kraj = rset.getDate(index++);
				
				IspitniRok ir = new IspitniRok(id, naziv, pocetak, kraj);
				ispitniRokovi.add(ir);
			} 
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}
		
		return ispitniRokovi;
	}
	
	public static IspitniRok getIspitniRokById(int id) throws Exception {
		IspitniRok ispitniRok = null;
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		String sql ="SELECT rok_id, naziv, pocetak, kraj FROM ispitni_rokovi WHERE rok_id = ?";
		stmt = ConnectionManager.getConnection().prepareStatement(sql);
		int index = 1;
		stmt.setInt(index++, id);
		rset = stmt.executeQuery();
		
		if(rset.next()) {
			index = 1;
			int idRok = rset.getInt(index++);
			String naziv = rset.getString(index++);
			Date pocetak = rset.getDate(index++);
			Date kraj = rset.getDate(index++);
			
			ispitniRok = new IspitniRok(idRok, naziv, pocetak, kraj);		
		}
		
		return ispitniRok;	
	}
	
	public static boolean add (IspitniRok ispitniRok) throws Exception {
		PreparedStatement stmt = null;
		
		try {
			String sql = "INSERT INTO ispitni_rokovi (naziv, pocetak, kraj) VALUES (?, ?, ?)";

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, ispitniRok.getNaziv());
			stmt.setDate(index++, ispitniRok.getPocetak());
			stmt.setDate(index++, ispitniRok.getKraj());
			
			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		} 		
	}
	
	public static boolean update (IspitniRok ispitniRok) throws Exception {
		PreparedStatement stmt = null;
		
		try {
			String sql = "UPDATE ispitni_rokovi SET naziv = ?, pocetak = ?, kraj = ? WHERE rok_id = " + ispitniRok.getId();

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, ispitniRok.getNaziv());
			stmt.setDate(index++, ispitniRok.getPocetak());
			stmt.setDate(index++, ispitniRok.getKraj());
			
			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		} 		
	}
	
	public static boolean delete(int id) throws Exception {
		Statement stmt = null;
		try {
			String sql = "DELETE FROM ispitni_rokovi WHERE rok_id = " + id;

			stmt = ConnectionManager.getConnection().createStatement();
			return stmt.executeUpdate(sql) == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		}
	}


}
