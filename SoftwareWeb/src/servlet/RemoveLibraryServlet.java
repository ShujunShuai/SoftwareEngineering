package src.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.entity.Library;

public class RemoveLibraryServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8031870257334626588L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		request.getSession().invalidate();
		setCookie(request, response, null);
//		request.getRequestDispatcher("../index.jsp").forward(request, response);
		response.sendRedirect("/SoftwareWeb/index.jsp");
	}

	//����cookie
	public void setCookie(HttpServletRequest request, HttpServletResponse response, Library library)
	throws ServletException, IOException {
        Cookie c1 = new Cookie("Name","");
        Cookie c2=new Cookie("password", "");
        c1.setMaxAge(0) ;
        c2.setMaxAge(0);
        response.addCookie(c1);
        response.addCookie(c2);
	}
}
