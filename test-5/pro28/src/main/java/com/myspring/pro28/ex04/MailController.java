package com.myspring.pro28.ex04;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myspring.pro28.ex03.MailService;

//@Controller
@EnableAsync
public class MailController {
	@Autowired
	private MailService mailService;
	@RequestMapping(value="/sendMail.do",method=RequestMethod.GET)
	public void sendSimpleMail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html:charset =utf-8");
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<html><body>");
		sb.append("<meta http-equiv='Content-Type' content='text/html; charset=euc-kr'>");
		sb.append("신상 맛을 소개합니다.<br><br>");
		sb.append("<a href='https://www.baskinrobbins.co.kr/'>");
		sb.append("<img src ='https://www.baskinrobbins.co.kr/upload/product/1728094076.png' /></a><br>");
		sb.append("</a>");
		sb.append("< a href='https://www.baskinrobbins.co.kr/'>상품 보기</a>");
		sb.append("<body></html>");
		String str = sb.toString();
		mailService.sendMail("eek4756@naver.com", "신상맛을 소개합니다", str);
		out.print("메일을 보냈습니다.");
}
}