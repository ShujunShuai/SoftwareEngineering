 package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AttentionDao;
import dao.SearchDao;
import entity.Artical;
import entity.User;

public class SearchFriendServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");					 	
	 	 //���ܿͻ�������
	 	String u_nick=request.getParameter("textfield3").trim();
	 	HttpSession session=request.getSession();
		User User=(User)session.getAttribute("User");
		int uid=User.getU_id(); 	
	 	 //��������
		SearchDao searchDao=new SearchDao();	
	 	List<User> list =new ArrayList<User>();
		list =searchDao.searchFriend(u_nick,uid); 
		session.setAttribute("list", list);
		 //���ݴ�������Ӧ�ͻ���
	 	request.getRequestDispatcher("../friend1.jsp").forward(request, response);	 
	}
}
