package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;
 

public class UpdateuserInfoServlet extends HttpServlet {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -933745162118546757L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 doPost(request,response);
	}

	 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int u_id=Integer.parseInt(request.getParameter("id")); 
		String u_account=request.getParameter("account");
		String u_mail=request.getParameter("mail");
	    String u_nick=request.getParameter("nick");
	    String u_img=request.getParameter("img");
	    String u_sex=request.getParameter("sex");
	    

	    String u_date=request.getParameter("date");
	    
		 
	    String u_name=request.getParameter("name");
	    String u_password=request.getParameter("password");
		 
		 String u_addr=request.getParameter("addr");
		 
		 String u_qq=request.getParameter("qq");
		 String u_msn=request.getParameter("msn");
		 String u_sign=request.getParameter("sign");
		
		User User=new User();
		 
		User.setU_account(u_account);
		User.setU_sex(u_sex);
		User.setU_date(u_date); 
		User.setU_img(u_img);
		User.setU_mail(u_mail);
		User.setU_nick(u_nick);
		User.setU_name(u_name);
		User.setU_password(u_password);
		User.setU_addr(u_addr);
		User.setU_qq(u_qq);
		User.setU_msn(u_msn);
		User.setU_sign(u_sign);

		UserDao userDao = new UserDao();
		boolean flag = userDao.updateUserInfo(User,u_id);
		int res = flag?1:2;
		response.sendRedirect("../User.jsp?msg=" + res);

		 
	}
	}

 
