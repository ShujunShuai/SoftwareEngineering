package src.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import src.dao.UploadLibraryFaceDao;
import src.entity.Library;


@SuppressWarnings("serial")
public class LibraryLoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UploadLibraryFaceDao upload=new UploadLibraryFaceDao();
		//-------------/////////------------------//
		HttpSession session=request.getSession();
		Library library=new Library();
	 	library=(Library)session.getAttribute("Library");
	 	String li_name=null;
	 	String li_password=null;
	 	if (library==null||library.getLi_name()==null) {
			li_name=request.getParameter("name").trim();
	        li_password=request.getParameter("password").trim();
		}else{
			li_name=library.getLi_name();
			li_password=library.getLi_password();
		}
		library=upload.check(li_name, li_password);
		//�Ƿ��Զ�����
		String autologin=request.getParameter("save");
        if (library.getLi_id()!=null) {
    		if ("yes".equals(autologin)) {
    			library.setLi_name(li_name);
    			library.setLi_password(li_password);
    			setCookie(request, response, library);
    		}	
    	 
    	 	
     	    session.setAttribute("libraryId", library.getLi_id());
     	    response.sendRedirect("/home.jsp");
		}else
			response.sendRedirect("/index.jsp?msg=5");
	}
	
	//保存cookie
	public void setCookie(HttpServletRequest request, HttpServletResponse response, Library library)
	throws ServletException, IOException {
		//保存Cookie
        if(library.getLi_name() !=""){
            Cookie c1 = new Cookie("Name",library.getLi_name());
            Cookie c2=new Cookie("password", library.getLi_password());
            c1.setMaxAge(60*60*60*12*30) ;
            c2.setMaxAge(60*60*60*12*30);
            response.addCookie(c1);
            response.addCookie(c2);
        }
	}
}