package src.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.dao.LibraryDao;
import src.entity.Library;
 

public class UpdateLibrarypasswordServlet extends HttpServlet {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4059301453534337662L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       doPost(request,response);
	}

	 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int li_id=Integer.parseInt(request.getParameter("id")); 
		String li_password=request.getParameter("newpassword1");
		String password=request.getParameter("password");
		Library library=new Library();
		library.setLi_password(li_password);
		LibraryDao libraryDao = new LibraryDao();
		boolean flag = libraryDao.checkPassword(password,li_id);
		if(flag==true){
			libraryDao.updatePassword(library,li_id);	
			response.sendRedirect("../Library.jsp?msg=3");
		}
		else{
			response.sendRedirect("../Limypassword.jsp?msg=4");
		}
		
		 
		 
	}

}
