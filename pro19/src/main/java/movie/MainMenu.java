package movie;

import java.io.IOException;
import java.util.List;


class MainMenu extends AbstractMenu {
	MoviesDao moviesDao = new MoviesDao();
	ReservationDao reservationDao = new ReservationDao();
	private static final MainMenu instance = new MainMenu(null);
	private static final String MAIN_MENU_TEXT = 
			"1: 영화 예매하기\n"+
			 "2: 예매 확인하기\n"+
			 "3: 예매 취소하기\n"+
			 "4: 관리자 메뉴로 이동\n"+
			 "q: 종료\n\n" +
			 "메뉴를 선택하세요: ";
	private MainMenu(Menu prevMenu) {
		super(MAIN_MENU_TEXT, prevMenu); 
	}
	public static MainMenu getInstance() {
		return instance;
	}
	public Menu next() {
		switch(scanner.nextLine()) {
		case "1":
			reserve(); 
			return this; 
		case "2":
			checkReservation(); 
			return this; 
		case "3":
			cancelReservation(); 
			return this; 
		case "4":
			if(!checkAdminPassword()) { 
				System.out.println(">> 비밀번호가 틀렸습니다.");
				return this; 
			}
			AdminMenu adminMenu = AdminMenu.getInstance();
			adminMenu.setPrevMenu(this);
			return adminMenu;
		case "q": return prevMenu; 
		default: return this;
		}
	}
	
	 private boolean checkAdminPassword() {
		 System.out.println("관리자 비밀번호를 입력하세요: ");
		 return "admin1234".equals(scanner.nextLine());
	 }
	
	private void checkReservation() {
		 System.out.println("예매발급번호를 입력하세요: ");
		 try {
			 Reservation r = 
					 reservationDao.findByResId(Integer.parseInt(scanner.next()));
			 if(r != null) {
				 System.out.println(">>[확인 완료]"+"예매발급번호: "+r.getResid()+"좌석: "+
			 r.getSeat()+"영화이름: "+r.getMoviename());
			 }else{
				 System.out.println(">>예매 내역이 없습니다.");
				 
			 }
			 
		 }catch (Exception e) {
			 System.out.println(">> 파일 입출력 문제가 생겼습니다.");
		 
		 }

	}
	
	 private void cancelReservation() {
		 System.out.println("예매발급 번호를 입력하세요: ");
		 int cancel = Integer.parseInt(scanner.nextLine());
		 boolean canceled = reservationDao.cancel(cancel);
		 if(canceled != false) {
			 System.out.printf(">>취소됨",
			 cancel);
		 }else {
			 System.out.println(">>예매 내역이 없습니다.");
		 }
	 }
	
	private void reserve() { 
		try {
			List<Movie> movies = moviesDao.printAllMovies();
			for(int i=0; i<movies.size(); i++) {
				System.out.printf("%s\n",movies.get(i).toString());
			}
			System.out.print("예매할 영화발급번호를 입력하세요: ");
			 int movieId = Integer.parseInt(scanner.nextLine());
			 Movie m = moviesDao.findByMovieId(movieId);
			 System.out.println(m);
			 List<Reservation>reservations = reservationDao.findById(m.getTitle());
			 Seats seats = new Seats(reservations);
			 seats.show();
			 System.out.println("좌석을 선택하세요(예: E-9):");
			 String seatName = scanner.nextLine();
			 seats.mark(seatName);
			 Reservation r = new Reservation(seatName, m.getTitle(),m.getId());
			 int _resid=reservationDao.save(r);
			 System.out.println(">>예매 완료됨");
			 System.out.printf(">>발급번호: %d\n",_resid);
		 }catch(IOException e) {
			 System.out.println(">>파일 입출력에 문제가 생겼습니다");
		 }catch(Exception e) {
			 System.out.printf(">>예매 실패했습니다:%s\n", e.getMessage());
		 }

	}
	 }