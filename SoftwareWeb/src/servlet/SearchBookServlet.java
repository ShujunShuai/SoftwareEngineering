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
import src.entity.BookInLibrary;

public class SearchBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");					 	
	 	 //���ܿͻ�������
	 	String bo_name=request.getParameter("textfield3").trim();
	 	HttpSession session=request.getSession();
		Book book=(Book)session.getAttribute("Book");
		BookInLibrary bookLi =(BookInLibrary)session.getAttribute("BookLi");
		int boid=book.getbo_id(); 	
	 	 //��������
		SearchDao searchDao=new SearchDao();	
	 	List<Book> list =new ArrayList<Book>();
	 	List<BookInLibrary> listLi =new ArrayList<BookInLibrary>();
		list =searchDao.searchBook(bo_name);
		listLi =searchDao.searchBookInLibrary(boid);
		session.setAttribute("list", list);
		session.setAttribute("listLi", listLi);
		 //���ݴ�������Ӧ�ͻ���
	 	request.getRequestDispatcher("../book1.jsp").forward(request, response);	 
	}
}
