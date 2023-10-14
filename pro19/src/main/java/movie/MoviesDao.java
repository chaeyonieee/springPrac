package movie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class MoviesDao {
	List<Movie> movielist = new ArrayList();
	public Connection conn;
	
	public MoviesDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn= DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/servletex?useUnicode=true&characterEncoding =utf8",
					"root",
					"1234"
					);
		}catch(Exception e) {
			e.printStackTrace();
			}
					
		}
	public List printAllMovies() {
		movielist.clear();
		try {
			String sql = "SELECT id, title, genre From movie ORDER BY id DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Movie movie = new Movie();
			
						movie.setId(rs.getInt("id"));
						movie.setTitle(rs.getString("title"));
						movie.setGenre(rs.getString("genre"));
						movielist.add(movie);
								
					}
					rs.close();
					pstmt.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
					
				}
				return movielist;
			}
			public Movie findByMovieId(int _movie) {
				Movie movie = new Movie();
				try {
					String sql = "SELECT id, title, genre From movie where id=?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, _movie);
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
						movie.setId(rs.getInt("id"));
						movie.setTitle(rs.getString("title"));
						movie.setGenre(rs.getString("genre"));
					}
					rs.close();
					pstmt.close();
					
					
				}
					catch(SQLException e) {
					e.printStackTrace();
				}
				return movie;
			}
				
				public boolean deleteMovie(int _movie) {
					
					
					try {
						String sql = "delete from movie where id =?";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1,_movie);
						pstmt.executeUpdate();
						pstmt.close();
						
					}catch(Exception e) {
						e.printStackTrace();
						
					}	return true;
				
				}
				
			
		
		public int save( Movie movie) {
			System.out.println(movie.getTitle());
			int _id = (int)(Math.random()*100000)+1;
			try {
				String sql = "INSERT INTO movie (id, title, genre)VALUES (?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,_id);
				pstmt.setString(2,movie.getTitle());
				pstmt.setString(3,movie.getGenre());
				pstmt.executeUpdate();
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
				
			}
			return _id;
		
	}

}
