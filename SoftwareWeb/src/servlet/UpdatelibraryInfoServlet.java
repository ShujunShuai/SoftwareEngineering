package src.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.dao.LibraryDao;
import src.entity.Library;
 

public class UpdatelibraryInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 4974697205377180444L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 doPost(request,response);
	}

	 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int li_id=Integer.parseInt(request.getParameter("id")); 
		String li_mail=request.getParameter("mail");
	    String li_name=request.getParameter("name");
	    String li_img=request.getParameter("img");
	    String li_url=request.getParameter("irl");
	    

	    String li_date=request.getParameter("date");	  		 
	    String li_password=request.getParameter("password");	 
		String li_addr=request.getParameter("addr");

		Library library=new Library();
		 
		library.setLi_date(li_date); 
		library.setLi_img(li_img);
		library.setLi_mail(li_mail);
		library.setLi_name(li_name);
		library.setLi_password(li_password);
		library.setLi_addr(li_addr);
		library.setLi_url(li_url);

		LibraryDao libraryDao = new LibraryDao();
		boolean flag = libraryDao.updateUser(library,li_id);
		int res = flag?1:2;
		response.sendRedirect("../userinfo.jsp?msg=" + res);

		 
	}
	}

 
