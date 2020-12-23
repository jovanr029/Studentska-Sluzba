package ekonomskiFakultet.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ekonomskiFakultet.model.Predmet;
import ekonomskiFakultet.model.Student;

public class PohadjaDAO {
	
	public static List<Predmet> getPredmetiByStudentId(int studentId) throws Exception {
		List<Predmet> predmeti = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT predmet_id FROM pohadja WHERE student_id = " + studentId;

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
	
	public static List<Student> getStudentiByPredmetId(int predmetId) throws Exception {
		List<Student> studenti = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rset = null;	
		try {
			String sql = "SELECT student_id FROM pohadja WHERE predmet_id = " + predmetId;
			
			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);
			
			while (rset.next()) {
				int studentId = rset.getInt(1);
				studenti.add(StudentDAO.getStudentById(studentId));
			}
			
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}
		
		return studenti;
	}
	
	public static boolean add(int studentID, int predmetID) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO pohadja (student_id, predmet_id) VALUES (?, ?)";

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setInt(index++, studentID);
			stmt.setInt(index++, predmetID);
			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		}
	}
	
	public static boolean delete(int studentID, int predmetID) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM pohadja WHERE student_id = ? AND predmet_id = ?";

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setInt(index++, studentID);
			stmt.setInt(index++, predmetID);
			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		}
	}
	
	

}
