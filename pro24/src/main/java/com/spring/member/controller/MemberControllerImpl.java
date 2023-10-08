package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.service.MemberServiceImpl;
import com.spring.member.vo.MemberVO;

public class MemberControllerImpl extends MultiActionController implements MemberController{
	private MemberService memberService;//클래스명 필드명 ->주소가 들어감
	public void setMemberService(MemberServiceImpl memberService) {//인터페이스라서 인터페이스형
		this.memberService=memberService;
	}
	//multiactioncontroller를 extends를 해야 dispatchservlet을 사용할 수 있다 : 외부에서 생성이되면서 의존성을 주입하고 있다
	
	
	@Override
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList",membersList);
		return mav;
	}
	@Override
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberVO memberVO = new MemberVO();
		bind(request,memberVO);
		int result = 0;
		result = memberService.addMember(memberVO);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	public ModelAndView removeMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	public ModelAndView form (HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servletex.include.request_uri");
		if(uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}
		int begin = 0;
		if(!((contextPath == null) ||("".equals(contextPath))))
				{begin = contextPath.length();
	}
	
	int end;
	if(uri.indexOf(";")!=-1) {
		end = uri.indexOf(";");
	}else if(uri.indexOf("?") != -1) {
		end = uri.indexOf("?");
	}else {
		end = uri.length();
	}
	String fileName = uri.substring(begin, end);
	if(fileName.indexOf(".")!= -1) {
		fileName = fileName.substring(0,fileName.lastIndexOf("."));
		
	}
	if(fileName.lastIndexOf("/") != -1) {
		fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());
		
	}
	return fileName;
	}
	
}
