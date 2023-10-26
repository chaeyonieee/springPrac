package sec01.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StudentAllDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public StudentAllDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/my_student");			
		}catch(Exception e) {
			e.printStackTrace();
	}
	}
	public List listStudents() {
		List studentsList = new ArrayList();
		try {
		    conn = dataFactory.getConnection();
			String query = " select * from my_student ";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String username = rs.getString("username");
				String univ = rs.getString("univ");
				String birth = rs.getString("birth");
				String email = rs.getString("email");
				StudentAllVO studentAllVO = new StudentAllVO(id, username, univ, birth, email);
				studentsList.add(studentAllVO);
				
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		return studentsList;
	}
	public void addStudent(StudentAllVO s) {
		try {	
			 conn = dataFactory.getConnection();
			String id = s.getId();
			String username = s.getUsername();
			String univ = s.getUniv();
			String birth = s.getBirth();
			String email = s.getEmail();
			System.out.println(id);
			String query = "INSERT INTO my_student(id,username,univ,birth,email)" + "VALUES(?,?,?,?,?)";
			
			System.out.println("prppareStatement: "+query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, username);
			pstmt.setString(3, univ);
			pstmt.setString(4, birth);
			pstmt.setString(5, email);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public StudentAllVO findStudent(String _id) {
		StudentAllVO studentInfo = null;
		try {	
			 conn = dataFactory.getConnection();
			 String query = "select * from my_student where id=?";
			 pstmt = conn.prepareStatement(query);
			 pstmt.setString(1, _id);
			
			System.out.println("prppareStatement: "+query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			
			String id = rs.getString("id");
			String username = rs.getString("username");
			String univ = rs.getString("univ");
			String birth = rs.getString("birth");
			String email =  rs.getString("email");
			
		
			studentInfo = new StudentAllVO(id, username, univ, birth, email);
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
	
		}
		return studentInfo;
	}
	
	
	public void delStudent(String id) {
		try {
			conn = dataFactory.getConnection();
			 String query = "delete from my_student where id=?";
			 System.out.println(query);
			 pstmt = conn.prepareStatement(query);
			 pstmt.setString(1, id);
			 pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
		}
		
	}
	
}

