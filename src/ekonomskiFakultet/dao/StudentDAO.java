package ekonomskiFakultet.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ekonomskiFakultet.model.Predmet;
import ekonomskiFakultet.model.Student;

public class StudentDAO {
	
	public static List<Student> getAll() throws  Exception {
		List <Student> studenti = new ArrayList<>(); 
		
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			String sql = "SELECT student_id, indeks, ime, prezime, grad FROM studenti";
			stmt = ConnectionManager.getConnection().createStatement();
			rset = stmt.executeQuery(sql);
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String indeks = rset.getString(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String grad = rset.getString(index++);			
				List<Predmet> predmeti = PohadjaDAO.getPredmetiByStudentId(id);

				Student student = new Student(id, ime, prezime, grad, indeks);
				student.getPredmeti().addAll(predmeti);
			
				studenti.add(student);
			} 
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}
		return studenti;
	}
	
	public static Student getStudentById(int id) throws Exception {
		Student student = null;
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
	
		try {
			String sql = "SELECT indeks, ime, prezime, grad FROM studenti WHERE student_id = ? ";
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setInt(index++, id);
			rset = stmt.executeQuery();
			
			if (rset.next()) {
				index = 1;
				String indeks = rset.getString(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String grad = rset.getString(index++);
				
				student = new Student(id, ime, prezime, grad, indeks);
			}
			
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}
		return student;		
	}
		
	
	public static Student getStudentByIndex(String indeks) throws Exception {
		Student student = null;
		
		PreparedStatement stmt = null;
		ResultSet rset = null;
	
		try {
			String sql = "SELECT student_id, indeks, ime, prezime, grad FROM studenti WHERE indeks = ? ";
			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, indeks);
			rset = stmt.executeQuery();
			
			if (rset.next()) {
				index = 1;
				int id = rset.getInt(index++);
				String ind = rset.getNString(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String grad = rset.getString(index++);
				
				student = new Student(id, ime, prezime, grad, ind);
			}
			
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
			try {rset.close();} catch (Exception ex) {ex.printStackTrace();}
		}
		return student;		
	}

	public static boolean add (Student student) throws Exception {
		PreparedStatement stmt = null;
		
		try {
			String sql = "INSERT INTO studenti (indeks, ime, prezime, grad) VALUES (?, ?, ?, ?)";

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, student.getIndex());
			stmt.setString(index++, student.getIme());
			stmt.setString(index++, student.getPrezime());
			stmt.setString(index++, student.getGrad());

			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		} 		
	}
	
	public static boolean update(Student student) throws Exception {
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE studenti SET indeks = ?, ime = ?, prezime = ?, grad = ? WHERE student_id = " + student.getId();

			stmt = ConnectionManager.getConnection().prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, student.getIndex());
			stmt.setString(index++, student.getIme());
			stmt.setString(index++, student.getPrezime());
			stmt.setString(index++, student.getGrad());

			return stmt.executeUpdate() == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		}
	}

	public static boolean delete(int id) throws Exception {
		Statement stmt = null;
		try {
			String sql = "DELETE FROM studenti WHERE student_id = " + id;

			stmt = ConnectionManager.getConnection().createStatement();
			return stmt.executeUpdate(sql) == 1;
		} finally {
			try {stmt.close();} catch (Exception ex) {ex.printStackTrace();}
		}
	}
}
