package brd05;

import java.util.List;

import brd05.ArticleVO;
import brd05.BoardDAO;

public class BoardService {
	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	public List<ArticleVO> listArticles(){ //諛섑솚�삎�씠 由ъ뒪�듃�젣�꽕由쵡rticleVO�삎�엫
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		//                �뿬湲곗뿉 ���옣                 �뿬湲곗꽌 諛쏆� 媛믪쓣
		return articlesList; //�떎�떆 �뿬湲곕줈 由ы꽩
	}
		
	public int addArticle(ArticleVO article) { //Service媛� �뾾�쑝硫� select, delete, insert into 紐⑤몢 湲곗옱�빐�빞�븿
		return boardDAO.insertNewArticle(article); 
		                     //8
	}
	
	public ArticleVO viewArticle(int articleNO) {
		ArticleVO article = null;
		article = boardDAO.selectArticle(articleNO);
		return article;
	}
	
	public void modArticle(ArticleVO article) {
		boardDAO.updateArticle(article);
	}
	
	public List<Integer> removeArticle(int articleNO){
		List<Integer> articleNOList = boardDAO.selectRemovedArticles(articleNO);
		boardDAO.deleteArticle(articleNO);
		return articleNOList;
	}

	public int addReply(ArticleVO article) {
		return boardDAO.insertNewArticle(article);
				
	}
}
