package com.spring.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


	

	@Controller("mainController")//소문자로시작했기때문에 클래스가 아니다
	@RequestMapping("/test")//클래스 위에 있기때문에 1차 매핑이다
	public class MainController {
		@RequestMapping(value ="/main1.do" , method =  RequestMethod.GET)
		public ModelAndView main1(HttpServletRequest request, HttpServletResponse response) throws Exception{
			ModelAndView mav = new ModelAndView();
			mav.addObject("msg","main1");
			mav.setViewName("main");
			return mav;

		}

			@RequestMapping(value ="/main2.do" , method =  RequestMethod.GET)
			public ModelAndView main2(HttpServletRequest request, HttpServletResponse response) throws Exception{
				ModelAndView mav = new ModelAndView();
				mav.addObject("msg","main2");
				mav.setViewName("main");
				return mav;

			}
				
	
	
}
/*actionservice의 사용을 했기때문에 main1이 실행이될때 modelAndView()객체에 넣는다
	가능한이유는 import를 했기때문이다
	그리고 이 값을 mav에 넣는다
	그다음에 addobject-modelandview에서 지원하는 
	필드msg에는 main1앞에 ""이 있다는것은 string형이다
	msg는 string형으로 변화한다
	
	modelandview에서 지원하는 setview메서드를 호출해서 main을 넣는다 
	modelandview의 값을 리턴해주고있다
	리턴을 받는 값은 
	main1이다
	그렇다면 main1을 호출한 애는 dispatch servlet이다.
	action-servlet객체에 *.do가 있어서 가능하다
	받은값중에 main이라는 값을 이용해서 viewresolver작업을한다

	
	*/