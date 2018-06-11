package src.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.dao.LibraryRegisterDao;
import src.entity.Library;

public class LibraryRegisterServlet extends HttpServlet {

	/**
	 *注册验证֤
	 */
	private static final long serialVersionUID = -478052340582521550L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String mail=request.getParameter("email");
		String name=request.getParameter("name");
		String pwd=request.getParameter("npassword");
		String rpwd=request.getParameter("rpassword");
		String addr=request.getParameter("addr");
		String url=request.getParameter("url");
		//���
		LibraryRegisterDao registerDao=new LibraryRegisterDao();
		Library library=new Library();
		library.setLi_mail(mail);
		library.setLi_password(rpwd);
		library.setLi_name(name);
		library.setLi_addr(addr);
		library.setLi_url(url);
		library.setLi_img("/SoftwareWeb/Liface/NoName.jpg");
		boolean flag=registerDao.addUser(library);
		if (flag) {
			HttpSession session=request.getSession();
			library=registerDao.getInfoByName(name);
			 
			session.setAttribute("Id", library.getLi_id());
			request.getRequestDispatcher("/home.jsp").forward(request, response);
		}else{
		response.sendRedirect("/CodecoreMicroArtical/register.jsp?msg=6");
		}
	}

}
