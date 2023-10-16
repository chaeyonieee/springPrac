package com.spring.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


	

	@Controller("mainController")//�ҹ��ڷν����߱⶧���� Ŭ������ �ƴϴ�
	@RequestMapping("/test")//Ŭ���� ���� �ֱ⶧���� 1�� �����̴�
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
/*actionservice�� ����� �߱⶧���� main1�� �����̵ɶ� modelAndView()��ü�� �ִ´�
	������������ import�� �߱⶧���̴�
	�׸��� �� ���� mav�� �ִ´�
	�״����� addobject-modelandview���� �����ϴ� 
	�ʵ�msg���� main1�տ� ""�� �ִٴ°��� string���̴�
	msg�� string������ ��ȭ�Ѵ�
	
	modelandview���� �����ϴ� setview�޼��带 ȣ���ؼ� main�� �ִ´� 
	modelandview�� ���� �������ְ��ִ�
	������ �޴� ���� 
	main1�̴�
	�׷��ٸ� main1�� ȣ���� �ִ� dispatch servlet�̴�.
	action-servlet��ü�� *.do�� �־ �����ϴ�
	�������߿� main�̶�� ���� �̿��ؼ� viewresolver�۾����Ѵ�

	
	*/