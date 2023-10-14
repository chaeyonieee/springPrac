package movie;

public class movieVO {
	private int id;
	private String title;
	private String genre;
	

	public movieVO() {
		System.out.println("movie 생성자 호출");
	}
	public movieVO(int id, String  title, String genre) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		
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
	
	
}
