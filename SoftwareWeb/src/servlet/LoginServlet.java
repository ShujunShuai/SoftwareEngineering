
package src.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.dao.AttentionDao;
import src.dao.UploadFaceDao;
import src.entity.Artical;
import src.entity.User;


@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UploadFaceDao upload=new UploadFaceDao();
		//-------------/////////------------------//
		HttpSession session=request.getSession();
		User User=new User();
	 	User=(User)session.getAttribute("User");
	 	String u_account=null;
	 	String u_password=null;
	 	if (User==null||User.getU_account()==null) {
			u_account=request.getParameter("userid").trim();
	        u_password=request.getParameter("password").trim();
		}else{
			u_account=User.getU_account();
			u_password=User.getU_password();
		}
		User=upload.check(u_account, u_password);
		//�Ƿ��Զ�����
		String autologin=request.getParameter("save");
        if (User.getU_id()!=null) {
        	ArrayList<Artical> listAll=new ArrayList<Artical>();
    	 	AttentionDao attentionDao=new AttentionDao();
    		if ("yes".equals(autologin)) {
    			User.setU_account(u_account);
    			User.setU_password(u_password);
    			setCookie(request, response, User);
    		}
 	   		//---------//////////---��ҳ����-------------------//	 	
    	 	String pageNumberStr = request.getParameter("pageNumber");  
    	    int pageNumber = 1;  
    	    if(pageNumberStr!=null && !pageNumberStr.isEmpty())  
    	    {  
    	        pageNumber = Integer.parseInt(pageNumberStr);  
    	    }  
    	    int pageSize = 3; //��ҳ��С  
    	    int totalPosts = attentionDao.allArticals(User.getU_id()); //��������  
    	    int totalPages = (int)totalPosts/pageSize + ((totalPosts%pageSize)>0?1:0); //����ó�����ҳ��  
    	    session.setAttribute("pageSize", pageSize);  
    	    session.setAttribute("totalPosts", totalPosts);  
    	    session.setAttribute("pageNumber", pageNumber);  
    	    session.setAttribute("totalPages", totalPages);  
    	 	listAll=attentionDao.getArtical(User.getU_id(), pageSize, pageNumber);
    	 	
    	 	session.setAttribute("listAll", listAll);
     	    session.setAttribute("userId", User.getU_id());
     	    response.sendRedirect("/CodecoreMicroArtical/home.jsp");
		}else
			response.sendRedirect("/CodecoreMicroArtical/index.jsp?msg=5");
	}
	
	//����cookie
	public void setCookie(HttpServletRequest request, HttpServletResponse response, User User)
	throws ServletException, IOException {
		//����Cookie
        if(User.getU_account() !=""){
            Cookie c1 = new Cookie("userName",User.getU_account());
            Cookie c2=new Cookie("password", User.getU_password());
            c1.setMaxAge(60*60*60*12*30) ;
            c2.setMaxAge(60*60*60*12*30);
            response.addCookie(c1);
            response.addCookie(c2);
        }
	}
}

