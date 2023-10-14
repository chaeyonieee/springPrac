package movie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {
	List<Reservation> reservationlist = new ArrayList();
	private Connection conn;
	
	public ReservationDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection( 
					"jdbc:mysql://localhost:3306/servletex?useUnicode=true&characterEncoding=utf8",
					"root",
					"1234"
					);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Reservation> findById(String moviename){
		reservationlist.clear();
		Reservation reservation = new Reservation();
		try {
			String sql = "SELECT resid,seat,moviename,movieid FROM reservation WHERE moviename=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, moviename);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				reservation.setResid(rs.getInt("resid"));
				reservation.setSeat(rs.getString("seat"));
				reservation.setMoviename(rs.getString("moviename"));
				reservation.setMovieid(rs.getInt("movieid"));
				reservationlist.add(reservation);
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return reservationlist;
	}
	
	public Reservation findByResId(int parseInt) {
		Reservation reservation = new Reservation();
		try {
			String sql = "SELECT resid,seat,moviename,movieid FROM reservation WHERE resid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, parseInt);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				reservation.setResid(rs.getInt("resid"));
				reservation.setSeat(rs.getString("seat"));
				reservation.setMoviename(rs.getString("moviename"));
				reservation.setMovieid(rs.getInt("movieid"));
			}
			rs.close();
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return reservation;
	}
	
	public int save(Reservation r) {
		int _resid = (int)(Math.random()*100000)+1;
		try {
			String sql = "INSERT INTO reservation(resid,seat,moviename,movieid) VALUES(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, _resid);
			pstmt.setString(2, r.getSeat());
			pstmt.setString(3, r.getMoviename());
			pstmt.setInt(4, r.getMovieid());
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return _resid;
	}
	
	public boolean cancel(int cancel) {
		try {
			String sql = "DELETE FROM reservation WHERE resid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, cancel);
					pstmt.executeUpdate();
					pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}