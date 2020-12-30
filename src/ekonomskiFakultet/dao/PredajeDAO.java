package ekonomskiFakultet.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ekonomskiFakultet.model.Predmet;
import ekonomskiFakultet.model.Profesor;

public class PredajeDAO {
	
	public static List<Predmet> getPredmetiByProfesorId(int profesorId) throws Exception {
		List<Predmet> predmeti = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT predmet_id FROM predaje WHERE profesor_id = " + profesorId;

			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				int predmetID = rset.getInt(1);
				predmeti.add(PredmetDAO.getPredmetById(predmetID));
			}
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}

		return predmeti;
	}
	
	public static List<Profesor> getProfesoriByPredmetId(int predmetId) throws Exception {
		List<Profesor> profesori = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rset = null;	
		try {
			String sql = "SELECT profesor_id FROM predaje WHERE predmet_id = " + predmetId;
			
			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				int profesorId = rset.getInt(1);
				profesori.add(ProfesorDAO.getProfesorById(profesorId));
			}
			
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}
		
		return profesori;
	}
	
	public static boolean add(int profesorID, int predmetID) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO predaje (profesor_id, predmet_id) VALUES (?, ?)";

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setInt(index++, profesorID);
			stmt.setInt(index++, predmetID);
			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		}
	}
	
	public static boolean delete(int profesorID, int predmetID) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM predaje WHERE profesor_id = ? AND predmet_id = ?";

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setInt(index++, profesorID);
			stmt.setInt(index++, predmetID);
			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		}
	}
	
	public static boolean deleteAllPredavanjaByProfesorId(int profesorID) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM predaje WHERE profesor_id = ?";

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setInt(index++, profesorID);
			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		}
	}
	
	public static boolean deleteAllPredavanjaByPredmetId(int predmetID) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM predaje WHERE predmet_id = ?";

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setInt(index++, predmetID);
			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		}
	}
}
