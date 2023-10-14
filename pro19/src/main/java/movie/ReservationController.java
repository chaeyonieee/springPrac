package movie;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reservation357/*")
public class ReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MoviesDao moviesDao;
	ReservationDao reservationDao;
	
	public void init(ServletConfig config) throws ServletException {
		moviesDao = new MoviesDao();
		reservationDao = new ReservationDao();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action:"+action);
		
		if(action == null || action.equals("/printAllMovies.do")) {
			List<Movie> moviesList = moviesDao.printAllMovies();
			request.setAttribute("moviesList", moviesList);
			nextPage = "/test01/reservation.jsp";
			
		}else if(action.equals("/reservation.do")) {
			String seat = request.getParameter("seat");
			String moviename = request.getParameter("moviename");
			String movieid = request.getParameter("movieid");
			int _movieid = Integer.parseInt(movieid);
			Reservation reservation = new Reservation(seat,moviename,_movieid);
			reservationDao.save(reservation);
			request.setAttribute("msg", "save");
			nextPage = "/reservation357/reservation.do";
			
		}else if(action.equals("/checkReservation.do")) {
			nextPage = "/reservation357/reservation.do";
		
		}else if(action.equals("/cancelReservation.do")) {
			String resid = request.getParameter("resid");
			Integer _resid = Integer.parseInt(resid);
			reservationDao.cancel(_resid);
			request.setAttribute("msg", "cancel");
			nextPage = "/reservation357/reservation.do";
			
		}else {
			List<Movie> movieList = moviesDao.printAllMovies();
			request.setAttribute("movieList", movieList);
			nextPage = "/test01/reservation.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
