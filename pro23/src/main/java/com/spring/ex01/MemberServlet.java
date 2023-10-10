package com.spring.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mem.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html:charset=utf-8");
		MemberDAO dao = new MemberDAO(); //memberDAO 객체 생성
		List<MemberVO> membersList = dao.selectAllMemberList(); //selectAllMemberList()를 호출합니다, list형인이유는 dao의 셀렉트가 list형이어서
		request.setAttribute("membersList", membersList);//밑에 listmembers.jsp에 변수를 사용하기위해서 setAttribute를 사용한다
		RequestDispatcher dispatch = request.getRequestDispatcher("test01/listMembers.jsp");
				dispatch.forward(request, response);
	}

}
