package movie;

public class Movie {
	private int id; 
	private String title; 
	private String genre; 
	
	public Movie(String title, String genre) {
		this.title = title;
		this.genre = genre;
	}
	public Movie() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	@Override
	public String toString() {
		return "Movie [영화발급번호 = "+id+",영화제목 ="+title+",장르 ="+genre +"]";
	}

}