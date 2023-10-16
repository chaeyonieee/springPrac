package com.spring.ex02;


	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;




	@Controller("loginController")
	public class LoginController {
		@RequestMapping(value ={"/test/loginForm.do" ,"test/loginForm2.do" }, method = { RequestMethod.GET})
		public ModelAndView loginForm( HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("loginForm");
			return mav;
		
		}
		
	
		@RequestMapping(value="/test/login.do" ,  method = { RequestMethod.GET, RequestMethod.POST})
		public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("result");
			String userID = request.getParameter("userID");//ex)bbb
			String userName = request.getParameter("userName");//황채연
			mav.addObject("userID",userID);
			mav.addObject("userName",userName);
			return mav;
		}
		//return mav를 하면 여기서 dispatch servlet이 받는다 
		//자원중에서 viewresolver을 사용해서 prefix와 suffix를 사용해서 
		//해당하는 것을 호출한다 
		//
		
		
		/*
		@RequestMapping(value="/test/login2.do" ,  method = { RequestMethod.GET, RequestMethod.POST})
		public ModelAndView login2(@RequestParam("userID") String userID, @RequestParam("userName") String userName, HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("result");
			//String userID = request.getParameter("userID");
			//String userName = request.getParameter("userName");
			System.out.println("userID: "+userID);
			System.out.println("userName: "+userName);
			mav.addObject("userID",userID);
			mav.addObject("userName",userName);
			return mav;
		}
		
		*/
		
		
		
		/*
		@RequestMapping(value="/test/login3.do" ,  method = { RequestMethod.GET, RequestMethod.POST})
		public ModelAndView login3(@RequestParam Map<String, String> info, HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("result");
			String userID = request.getParameter("userID");
			String userName = request.getParameter("userName");
			System.out.println("userID: "+userID);
			System.out.println("userName: "+userName);
			mav.addObject("info",info);
			mav.setViewName("result");
			return mav;
		}
		*/
		
		
		/*
		@RequestMapping(value="/test/login4.do" ,  method = { RequestMethod.GET, RequestMethod.POST})
		public ModelAndView login4(@ModelAttribute ("info") LoginVO loginVO, HttpServletRequest request, HttpServletResponse response) throws Exception {"
				
			request.setCharacterEncoding("utf-8");
			ModelAndView mav = new ModelAndView();
			System.out.println("userID: "+ loginVO.getUserID());
			System.out.println("userName: "+ loginVO.getUserName());
			System.out.println("userName: "+userName);
			mav.setViewName("result");
			return mav;
		}
		*/
		
		@RequestMapping(value="/test/login5.do" ,  method = { RequestMethod.GET, RequestMethod.POST})
		public String login5(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			model.addAttribute("userID","bbb");
			model.addAttribute("userName","황채연");
			return "request";
		}
		
		
	}
