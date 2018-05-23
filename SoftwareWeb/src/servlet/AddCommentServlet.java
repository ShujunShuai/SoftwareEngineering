package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentDao;
import entity.Artical;

public class AddCommentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6282004198328297575L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		int uid=Integer.parseInt(request.getParameter("curUid").toString());
		int bid=Integer.parseInt(request.getParameter("bid").toString());
		String content=request.getParameter("txtContent");
		CommentDao commentDao=new CommentDao();
		Artical artical=new Artical();
		artical.setB_id(bid);
		commentDao.postComment(artical, uid, content);
		session.setAttribute("c_degree", commentDao.accountComment(bid));
		response.sendRedirect("/CodecoreMicroArtical/home.jsp");
	}

}
