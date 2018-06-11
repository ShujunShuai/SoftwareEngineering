package src.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.dao.CommentBookDao;
import src.entity.Book;

public class AddBookCommentServlet extends HttpServlet {

	


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		int uid=Integer.parseInt(request.getParameter("curUid").toString());
		int boid=Integer.parseInt(request.getParameter("boid").toString());
		String content=request.getParameter("txtContent");
		CommentBookDao commentbookDao=new CommentBookDao();
		Book book=new Book();
		book.setbo_id(boid);
		commentbookDao.postComment(book, uid, content);
		session.setAttribute("c_degree", commentbookDao.accountComment(boid));
		response.sendRedirect("/CodecoreMicroArtical/home.jsp");
	}

}
