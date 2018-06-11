package src.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.dao.AttentionDao;
import src.entity.User;

@SuppressWarnings("serial")
public class AttentionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		User User=(User)request.getSession().getAttribute("User");
		AttentionDao attentionDao=new AttentionDao();
		request.setAttribute("attention", attentionDao.accountAttention(User.getU_id()));
	}

}
