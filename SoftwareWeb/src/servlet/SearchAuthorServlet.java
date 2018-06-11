 package src.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.dao.SearchDao;
import src.entity.Book;

public class SearchAuthorServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");					 	
	 	 //���ܿͻ�������
	 	String bo_author=request.getParameter("textfield3").trim();
	 	HttpSession session=request.getSession();
		Book book=(Book)session.getAttribute("Book");
		//int boid=book.getbo_id(); 	
	 	 //��������
		SearchDao searchDao=new SearchDao();	
	 	List<Book> list =new ArrayList<Book>();
		list =searchDao.searchAuthor(bo_author); 
		session.setAttribute("list", list);
		 //���ݴ�������Ӧ�ͻ���
	 	request.getRequestDispatcher("../book1.jsp").forward(request, response);	 
	}
}
