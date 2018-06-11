package src.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.dao.BookInLibraryDao;
import src.entity.BookInLibrary;
import src.entity.Library;

public class ShowBookLibrarytoLibraryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 	
		 //接收客户端数据 	
		HttpSession session=request.getSession();
		int liId=0;
	 	if (request.getParameter("Liid")!=null) {
	 		liId=Integer.parseInt(request.getParameter("Liid"));
		}else{
			Library library=(Library)session.getAttribute("library");
			liId=library.getLi_id();
		}	 		
	 	///////////----------分页处理--------////////
	 	int pageNumber = 0;  
	 	if (request.getParameter("pageNumber")!=null) {
	 		pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}else
			pageNumber=1;
	    int pageSize = 3; //分页大小  
	    BookInLibraryDao bookInLibraryDao =new BookInLibraryDao();
	    int totalPosts = bookInLibraryDao.accountBooks(liId); //总书籍数  
	    int totalPages = (int)totalPosts/pageSize + ((totalPosts%pageSize)>0?1:0); //计算得出的总页数  	    
	    session.setAttribute("pageSize", pageSize);  
	    session.setAttribute("totalPosts", totalPosts);  
	    session.setAttribute("pageNumber", pageNumber);  
	    session.setAttribute("totalPages", totalPages);	 	
	 	//////////-------分页结束----------/////////////
	     //处理数据

	    List<BookInLibrary> bookLi = new ArrayList<BookInLibrary>();
	    bookLi =bookInLibraryDao.getBookByLi(liId, pageSize, pageNumber);	
	 	session.setAttribute("BookLi", bookLi);
	 	 //根据处理结果响应客户端
	 	response.sendRedirect("/SoftwareWeb/Library2.jsp");
	}
}
