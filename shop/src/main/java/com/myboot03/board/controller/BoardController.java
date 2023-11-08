
package com.myboot03.board.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;



public interface BoardController {
	public ModelAndView listArticles(HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartReq, HttpServletResponse resp) throws Exception;
	public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO, HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public ResponseEntity modArticle(MultipartHttpServletRequest multipartReq, HttpServletResponse resp) throws Exception;
	public ResponseEntity removeArticle(@RequestParam("articleNO") int articleNO, HttpServletRequest req, HttpServletResponse resp) throws Exception;
}