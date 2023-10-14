package movie;

public class ReservationVO {
	private int resid;
	private String seat;
	private String moviename;
	private int movieid;
	
	
	public ReservationVO() {
		System.out.println("Reservation 생성자 호출");
	}
	
	public ReservationVO(int resid, String seat, String moviename, int movieid) {
		super();
		this.resid =resid;
		this.seat = seat;
		this.moviename = moviename;
		this.movieid =movieid;
	}

	public int getResid() {
		return resid;
	}

	public void setResid(int resid) {
		this.resid = resid;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public int getMovieid() {
		return movieid;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}

}
