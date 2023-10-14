package movie;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/movie357/*")
public class MovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MoviesDao moviesDao;
	
	public void init(ServletConfig config) throws ServletException {
		moviesDao = new MoviesDao();
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
			nextPage = "/test01/printAllMovies.jsp";
			
		}else if(action.equals("/addMovie.do")) {
			String title = request.getParameter("title");
			String genre = request.getParameter("genre");
			Movie movie = new Movie(title,genre);
			moviesDao.save(movie);
			request.setAttribute("msg", "save");
			nextPage = "/movie357/printAllMovies.do";
		
		}else if(action.equals("/movieForm.do")) {
			nextPage="/test01/movieForm.jsp";
			
		}else if(action.equals("/deleteMovie.do")) {
			String id = request.getParameter("id");
			Integer _id = Integer.parseInt(id);
			moviesDao.deleteMovie(_id);
			request.setAttribute("msg", "deleted");
			nextPage = "/movie357/printAllMovies.do";
			
		}else {
			List<Movie> moviesList = moviesDao.printAllMovies();
			request.setAttribute("moviesList", moviesList);
			nextPage = "/test01/printAllMovies.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	
}
	}
