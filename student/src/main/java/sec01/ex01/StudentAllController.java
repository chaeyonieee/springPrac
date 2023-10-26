package sec01.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/student151/*")
public class StudentAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudentAllDAO studentAllDAO;

		public void init()throws ServletException{
			studentAllDAO = new StudentAllDAO();
		}	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html:charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action:"+action);
		if(action==null || action.equals("/listStudents.do")) {
			List studentsList = studentAllDAO.listStudents();
			request.setAttribute("studentsList", studentsList);
			nextPage = "/test01/studentresult.jsp";
			
		}else if(action.equals("/addStudent.do")){
			String id = request.getParameter("id");
			String username = request.getParameter("username");
			String univ = request.getParameter("univ");
			String birth = request.getParameter("birth");
			String email = request.getParameter("email");
			StudentAllVO studentAllVO = new StudentAllVO(id, username, univ, birth,email);
			studentAllDAO.addStudent(studentAllVO);
			
			request.setAttribute("msg", "addStudent");
			nextPage = "/student151/listStudents.do";

		}else if (action.equals("/studentInfo.do")) {
			nextPage ="/test01/studentInfo.jsp";
			
		}else if (action.equals("/delStudent.do")) {
			String id = request.getParameter("id");
			studentAllDAO.delStudent(id);
			request.setAttribute("msg", "deleted");
			nextPage = "/student151/listStudents.do";
				
		}else  {
			List studentsList = studentAllDAO.listStudents();
			request.setAttribute("studentsList", studentsList);
			nextPage = "/test01/studentresult.jsp";

		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
	}