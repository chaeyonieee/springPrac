package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.spring.member.service.MemberService;
import com.spring.member.vo.MemberVO;


@Controller("memberController")
public class MemberControllerImpl implements MemberController{
	@Autowired
	private MemberService memberService;//클래스명 필드명 ->주소가 들어감
	@Autowired
	private MemberVO memberVO;
	
	
	@Override
	@RequestMapping(value="/member/listMembers.do" , method = RequestMethod.GET)//@RequestMapping가 2차 매핑 :/member/listMembers.do, 그리고 get방식으로 받아야한다.
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		request.setCharacterEncoding("utf-8");
		String viewName = getViewName(request);//
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList",membersList);
		return mav;
	}
		
	@Override
	@RequestMapping(value="/member/addMember.do" , method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;//초기화
		result = memberService.addMember(member);//1
		System.out.println(result);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	//개발자가 만든 저장소Vo만 가능 = ModelAttribute
	
	@Override
	@RequestMapping(value="/member/modMemberForm.do" , method = RequestMethod.GET)//폼이니까
	public ModelAndView modMemberForm(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
	request.setCharacterEncoding("utf-8");
	memberVO =memberService.modMemberForm(id);
	String viewName = getViewName(request);
	ModelAndView mav = new ModelAndView(viewName);
	mav.addObject("memberVO",memberVO);

	return mav;
	}


	@Override
	@RequestMapping(value="/member/modMember.do" , method = RequestMethod.POST)
	public ModelAndView modember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception {
	request.setCharacterEncoding("utf-8");
	int result = 0;//초기화
	result = memberService.modMember(member);
	ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
	return mav;
	}


	@Override
	@RequestMapping(value="/member/removeMember.do" , method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@RequestMapping(value="/member/*Form.do" , method = RequestMethod.GET)
	public ModelAndView form (HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
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

	String viewName = uri.substring(begin, end);
	if(viewName.indexOf(".")!= -1) {
		viewName = viewName.substring(0,viewName.lastIndexOf("."));
		
	}
	if(viewName.lastIndexOf("/") != -1) {
		viewName=viewName.substring(viewName.lastIndexOf("/"),viewName.length());
		
	}
	return viewName;
	}


}
/*
 * @Controller는 membercontroller에 값을 저장해주라는 의미이다
 *
 *
 * 
 */

