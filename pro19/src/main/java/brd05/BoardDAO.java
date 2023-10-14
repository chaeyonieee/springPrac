package brd05;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	
	public BoardDAO() {
		try {  
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/servletex");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List selectAllArticles() { 
		List articlesList = new ArrayList();
		try {
			conn= dataFactory.getConnection();
			String query =  /*"select * from t_board";*/
					 "select case when level -1 > 0 then concat(concat(repeat(' ', level -1), '<'), board.title ) else board.title end as title, board.articleNO, board.parentNO, result.level, board.content, board.id, board.writeDate "
					                                                                 
					 + " from" +
					 " (select function_hierarchical() as articleNO, @level as level " +
					   //                                           
					 " from (select @start_with:=0, @articleNO:=@start_with, @level:=0) tbl join t_board) result "
					 + " join t_board board on board.articleNO = result.articleNO";
					 
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				articlesList.add(article); 
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}
	
	private int getNewArticleNO() {
		try {
			conn= dataFactory.getConnection(); 
			String query = "select max(articleNO) from t_board ";
			                 //t_board�쓽 articleNO 理쒕�媛믪쓣 媛��졇�샂
			System.out.println(query);
			pstmt = conn.prepareStatement(query); 
			ResultSet rs = pstmt.executeQuery(query); //
			//    而ㅼ꽌�떆�옉�쐞移�         �떎吏곸쟻 泥섎━ �떎�뻾
			if(rs.next())
				return(rs.getInt(1)+1); 
			              //泥ル쾲吏몄뿴�쓽 articleNO媛� 8�씠硫�+1�빐�꽌 9媛��맖 9瑜� 由ы꽩(db�뒗 0�씤�뜳�뒪媛� �뾾�쓬)
			              //articleNO媛� int�삎�엫
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int insertNewArticle(ArticleVO article) { //db�뿉 �뿰寃고븳 �썑 crud �슂�뱾�떒�쐞濡� 泥섎━
		int articleNO = getNewArticleNO(); //9瑜� 諛쏆쓬
		try {
			conn= dataFactory.getConnection();
			int parentNO = article.getParentNO();
			                          //0
			String title = article.getTitle();
			String content = article.getContent();
			String id = article.getId();
			                    //lee
			String imageFileName = article.getImageFileName();
			String query = "insert into t_board (articleNO, parentNO, title, content, imageFileName, id) "+"  values(?,?,?,?,?,?)";
			                                   //   8                                                               諛쏆�媛믪쓣 ?瑜� �씠�슜�빐�꽌 蹂��닔議곗젙
			System.out.println(query);
			pstmt=conn.prepareStatement(query); //Statement�씪�뒗 以묎컙留ㅺ컻泥닿� �엳�뒗寃껋엫 �뿬湲곗뿉 �꽔�뿀�떎媛� �떎�떆 db�뿉 �꽔�뼱以�
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return articleNO;
	}
	
	public ArticleVO selectArticle(int articleNO) {
		ArticleVO article = new ArticleVO();
		try {
			conn= dataFactory.getConnection();
			String query = "select articleNO,parentNO,title,content,ifnull(imageFileName, 'null') as imageFileName,id,writeDate" //as별칭
							+ " from t_board"
							+ " where articleNO=?";	//1	                                                                                                
			System.out.println(query);
			pstmt=conn.prepareStatement(query); 
			pstmt.setInt(1, articleNO);
			ResultSet rs = pstmt.executeQuery();//실질적 처리->resultset에 반환
			rs.next();
			int _articleNO  = rs.getInt("articleNO");//_articleNO는 1
			int parentNO = rs.getInt("parentNO");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String imageFileName = URLEncoder.encode(rs.getString("imageFileName"), "UTF-8"); //파일 이름 한글위험
			if(imageFileName.equals("null")) {
				imageFileName = null;//ifnull일때는 string형의 null이지만 지금은 실질적 null
			}
			String id = rs.getString("id");
			Date writeDate = rs.getDate("writeDate");
			
			article.setArticleNO(_articleNO);//데이터입출력 setter를 통해 저장
			article.setParentNO(parentNO);
			article.setTitle(title);
			article.setContent(content);
			article.setImageFileName(imageFileName);
			article.setId(id);
			article.setWriteDate(writeDate);
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return article;
	}
	
	public void updateArticle(ArticleVO article) {
		int articleNO = article.getArticleNO();
		String title = article.getTitle();
		String content = article.getTitle();
		String imageFileName = article.getImageFileName();
		try {
			conn= dataFactory.getConnection();
			String query = "update t_board set title=?,content=?";
			if(imageFileName != null && imageFileName.length() !=0) {
				query += ",imageFileName=?";
			}
			query += " where articleNo=?";
			
			System.out.println(query);
			pstmt=conn.prepareStatement(query); //Statement�씪�뒗 以묎컙留ㅺ컻泥닿� �엳�뒗寃껋엫 �뿬湲곗뿉 �꽔�뿀�떎媛� �떎�떆 db�뿉 �꽔�뼱以�
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			if(imageFileName != null && imageFileName.length() !=0) {
				pstmt.setString(3, imageFileName);
				pstmt.setInt(4, articleNO);
			}else {
				pstmt.setInt(3, articleNO);
			}
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteArticle(int articleNO) {
		try {
			conn= dataFactory.getConnection();
			String query = "delete from t_board where parentNO=? or articleNO=?";		                                                                                                
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, articleNO);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Integer> selectRemovedArticles(int articleNO){
		List<Integer> articleNOList = new ArrayList<Integer>();
		try {
			conn= dataFactory.getConnection();
			String query ="select articleNO from t_board where articleNO in (select articleNO from t_board where parentNO =?)";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				articleNO = rs.getInt("articleNO");
				articleNOList.add(articleNO);
			}
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return articleNOList;
	}
}

