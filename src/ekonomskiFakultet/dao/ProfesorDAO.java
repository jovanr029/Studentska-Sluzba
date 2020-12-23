package ekonomskiFakultet.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ekonomskiFakultet.model.Profesor;

public class ProfesorDAO {
	
	public static List<Profesor> getAll() throws Exception {
		List<Profesor> profesori = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT profesor_id, ime, prezime, zvanje FROM profesori";

			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String zvanje = rset.getString(index++);

				Profesor profesor = new Profesor(id, ime, prezime, zvanje);
				profesori.add(profesor); 
			}
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}

		return profesori;
	}
	
	public static Profesor getProfesorById(int id) throws Exception {
		Profesor profesor = null;
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT ime, prezime, zvanje FROM profesori WHERE profesor_id = ?";
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			rset = stmt.executeQuery();
			if (rset.next()) {
				int index = 1;
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String zvanje = rset.getString(index++);

				profesor = new Profesor(id, ime, prezime, zvanje);
			} 
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}
		return profesor;
	}
	
	public static boolean add(Profesor profesor) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO profesori (ime, prezime, zvanje) VALUES (?, ?, ?)";

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, profesor.getIme());
			stmt.setString(index++, profesor.getPrezime());
			stmt.setString(index++, profesor.getZvanje());

			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		}
	}

	public static boolean update(Profesor profesor) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE profesori SET ime = ?, prezime = ?, zvanje = ? WHERE profesor_id = " + profesor.getId();

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, profesor.getIme());
			stmt.setString(index++, profesor.getPrezime());
			stmt.setString(index++, profesor.getZvanje());

			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		}
	}
	
	public static boolean delete(int id) throws Exception {
		Statement stmt = null;
		try {
			String sql = "DELETE FROM profesori WHERE profesor_id = " + id;

			stmt = ConnectionManager.getConnection().createStatement();
			return stmt.executeUpdate(sql) == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		}
	}

	

}
