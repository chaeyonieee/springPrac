package brd05;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;


@WebServlet("/board51/*")
public class BoardController5 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
    
	BoardService boardService;
	ArticleVO articleVO;

	public void init(ServletConfig config) throws ServletException { 
		boardService = new BoardService(); 
		articleVO = new ArticleVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = ""; 
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();//2차 맵핑 하겠다는 코드
		System.out.println("action:"+action);
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>(); 
			if(action == null) { 
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board05/listArticles.jsp";
			}else if(action.equals("/listArticles.do")) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
			
				nextPage = "/board05/listArticles.jsp";
				

			}else if(action.equals("/articleForm.do")) { 
				nextPage = "/board05/articleForm.jsp"; 

			}else if(action.equals("/addArticle.do")) {
				int articleNO = 0;
				Map<String, String> articleMap = upload(request, response);//키 값이 string형
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");

				articleVO.setParentNO(0);
				articleVO.setId("bbb"); 
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				articleNO = boardService.addArticle(articleVO);
				if(imageFileName != null && imageFileName.length() !=0) {
					//temp
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
                                               //9
					destDir.mkdirs(); //파일만들기
					FileUtils.moveFileToDirectory(srcFile, destDir, true); //옮기기
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "alert('새글을 추가했습니다.');"
						+ " location.href='"
						+ request.getContextPath()
						+ "/board51/listArticles.do';" + "</script>");
				return;		
			
			}else if(action.equals("/viewArticle.do")){
				String articleNO = request.getParameter("articleNO");
				articleVO = boardService.viewArticle(Integer.parseInt(articleNO));//1
				request.setAttribute("article", articleVO);
				nextPage = "/board05/viewArticle.jsp";
			
			}else if(action.equals("/modArticle.do")) {
				Map<String, String> articleMap = upload(request, response);
				int articleNO = Integer.parseInt(articleMap.get("articleNO"));
				articleVO.setArticleNO(articleNO);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				articleVO.setParentNO(0);
				articleVO.setId("bbb"); 
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				boardService.modArticle(articleVO);
				if(imageFileName != null && imageFileName.length() !=0) { 
					//temp
					String originalFileName = articleMap.get("originalFileName");
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
                                               //9
					destDir.mkdirs(); 
					FileUtils.moveFileToDirectory(srcFile, destDir, true); 
					File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + originalFileName);
					oldFile.delete(); 
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "alert('글을 수정했습니다.');"
						+ " location.href='"
						+ request.getContextPath()
						+ "/board51/viewArticle.do?articleNO=" + articleNO + "';" + "</script>");
				return;
			
			}else if(action.equals("/removeArticle.do")){
				int articleNO = Integer.parseInt(request.getParameter("articleNO"));
				List<Integer> articleNOList = boardService.removeArticle(articleNO);
				for(int _articleNO : articleNOList) {
					File imgDir = new File(ARTICLE_IMAGE_REPO + "\\" + _articleNO);
					if(imgDir.exists()) {
						FileUtils.deleteDirectory(imgDir);
					}
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "alert('글을 삭제했습니다.');"
						+ " location.href='"
						+ request.getContextPath()
						+ "/board51/listArticles.do';" + "</script>");
				return;
				
			}else {
				nextPage = "/board05/listArticles.jsp";
			}
			
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
			dispatch.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
										
		Map<String, String> articleMap = new HashMap<String, String>();
                                   
		String encoding = "utf-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO); 
		
		DiskFileItemFactory factory = new DiskFileItemFactory();//밑에 메소ㅛ드를 사용하려고 객체 사용
		
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);//준비 단계
		ServletFileUpload upload = new ServletFileUpload(factory);//실질적인 사용을 하려고 객체 생성
		
		
		try {
			List items = upload.parseRequest(request);
		
			for(int i=0; i<items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i); ///?////
				
				if(fileItem.isFormField()) {//파일의 형태가 이미지인지 텍스트인지: getfieldname ,이미지 :else: filename
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					
				}else {
					System.out.println("파라미터이름:"+fileItem.getFieldName()); //imagaFileName !!!!!!!!!!!!
					System.out.println("파일이름:"+fileItem.getName()); //temp.jpg
					System.out.println("파일크기:"+fileItem.getSize()+"bytes"); //파일사이즈(40)
					if(fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						
						}
						String fileName = fileItem.getName().substring(idx+1);
						articleMap.put(fileItem.getFieldName(), fileName);
			             //imageFileName, temp.jpg
						File uploadFile = new File((currentDirPath)+"\\temp\\"+fileName);
													
						fileItem.write(uploadFile); 
					} //end if
				} //end if
			} //end for
		}catch(Exception e) {
			e.printStackTrace();
		}
		return articleMap; 
	}
}
