package ekonomskiFakultet.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ekonomskiFakultet.model.IspitnaPrijava;
import ekonomskiFakultet.model.IspitniRok;
import ekonomskiFakultet.model.Predmet;
import ekonomskiFakultet.model.Student;

public class IspitnaPrijavaDao {
	
	public static List<IspitnaPrijava> getIspitnePrijaveByStudent(Student student) throws Exception {
		List<IspitnaPrijava> ispitnePrijaveStudent = new ArrayList<>();
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			
			String sql = "SELECT predmet_id, rok_id, teorija, zadaci FROM ispitne_prijave WHERE student_id = ?";
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setInt(index++, student.getId());
			rset = stmt.executeQuery();
			
			while(rset.next()) {
				index = 1;
				int predmetId = rset.getInt(index++);
				int rokId = rset.getInt(index++);
				int teorija = rset.getInt(index++);
				int zadaci = rset.getInt(index++);
				
				Predmet predmet = PredmetDAO.getPredmetById(predmetId);
				IspitniRok ispitniRok = IspitniRokDAO.getIspitniRokById(rokId);
				
				IspitnaPrijava ispitnaPrijava = new IspitnaPrijava(predmet, ispitniRok, student, teorija, zadaci);
				ispitnePrijaveStudent.add(ispitnaPrijava);
			}				
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}
		return ispitnePrijaveStudent;	
	}

	public static List<IspitnaPrijava> getIspitnePrijaveByPredmet(Predmet predmet) throws Exception {
		List<IspitnaPrijava> ispitnePrijavePredmet = new ArrayList<>();
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			
			String sql = "SELECT student_id, rok_id, teorija, zadaci FROM ispitne_prijave WHERE predmet_id = ?";
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setInt(index++, predmet.getId());
			rset = stmt.executeQuery();
			
			while(rset.next()) {
				index = 1;
				int studentId = rset.getInt(index++);
				int rokId = rset.getInt(index++);
				int teorija = rset.getInt(index++);
				int zadaci = rset.getInt(index++);
				
				Student student = StudentDAO.getStudentById(studentId);
				IspitniRok ispitniRok = IspitniRokDAO.getIspitniRokById(rokId);
				
				IspitnaPrijava ispitnaPrijava = new IspitnaPrijava(predmet, ispitniRok, student, teorija, zadaci);
				ispitnePrijavePredmet.add(ispitnaPrijava);
			}				
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}
		return ispitnePrijavePredmet;	
	}

	public static List<IspitnaPrijava> getIspitnePrijaveByIspitniRok(IspitniRok ispitniRok) throws Exception {
		List<IspitnaPrijava> ispitnePrijaveIspitniRok = new ArrayList<>();
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			
			String sql = "SELECT student_id, predmet_id, teorija, zadaci FROM ispitne_prijave WHERE rok_id = ?";
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setInt(index++, ispitniRok.getId());
			rset = stmt.executeQuery();
			
			while(rset.next()) {
				index = 1;
				int studentId = rset.getInt(index++);
				int predmetId = rset.getInt(index++);
				int teorija = rset.getInt(index++);
				int zadaci = rset.getInt(index++);
				
				Student student = StudentDAO.getStudentById(studentId);
				Predmet predmet = PredmetDAO.getPredmetById(predmetId);
				
				IspitnaPrijava ispitnaPrijava = new IspitnaPrijava(predmet, ispitniRok, student, teorija, zadaci);
				ispitnePrijaveIspitniRok.add(ispitnaPrijava);
			}				
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}
		return ispitnePrijaveIspitniRok;	
	}

	public static boolean add(IspitnaPrijava ispitnaPrijava) throws Exception {
		PreparedStatement stmt = null;
		
		try {
			String sql = "INSERT INTO ispitne_prijave (student_id, predmet_id, rok_id, teorija, zadaci) VALUES (?, ?, ?, ?, ?)";

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setInt(index++, ispitnaPrijava.getStudent().getId());
			stmt.setInt(index++, ispitnaPrijava.getPredmet().getId());
			stmt.setInt(index++, ispitnaPrijava.getIspitniRok().getId());
			stmt.setInt(index++, ispitnaPrijava.getTeorija());
			stmt.setInt(index++, ispitnaPrijava.getZadaci());

			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		} 		
		
	}

	public static boolean update(IspitnaPrijava ispitnaPrijava) throws Exception {
		PreparedStatement stmt = null;
		
		try {
			String sql = "UPDATE ispitne_prijave SET teorija = ?, zadaci = ? WHERE student_id = ? AND predmet_id =? AND rok_id = ?";

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setInt(index++, ispitnaPrijava.getTeorija());
			stmt.setInt(index++, ispitnaPrijava.getZadaci());
			stmt.setInt(index++, ispitnaPrijava.getStudent().getId());
			stmt.setInt(index++, ispitnaPrijava.getPredmet().getId());
			stmt.setInt(index++, ispitnaPrijava.getIspitniRok().getId());		
			
			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		} 		
	}

	public static boolean delete(int studentId, int predmetId, int rokId) throws Exception {
		PreparedStatement stmt = null;
		
		try {
			String sql = "DELETE from ispitne_prijave WHERE student_id = ? AND predmet_id = ? AND rok_id = ?";

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setInt(index++, studentId);
			stmt.setInt(index++, predmetId);
			stmt.setInt(index++, rokId);

			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		} 		
	}

}
