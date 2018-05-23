package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CollectDao;
import entity.User;

public class CancelCollectServlet extends HttpServlet {

	 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 doPost(request,response);
	}

	 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//��ȡ��ǰ�û�����
		int co_id=Integer.parseInt(request.getParameter("co_id"));
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("User");
		int uid=user.getU_id(); 							 
		//---------//////////---��ҳ����-------------------//	 	
	 	//��ҳ
	 	String pageNumberStr = request.getParameter("pageNumber");  
	    int pageNumber = 1;  
	    if(pageNumberStr!=null && !pageNumberStr.isEmpty())  
	    {  
	        pageNumber = Integer.parseInt(pageNumberStr);  
	    }  
	    int pageSize = 3; //��ҳ��С  
	    CollectDao collectDao=new CollectDao();
	    int totalPosts =collectDao.allCollects(uid); //��������  
	    int totalPages = (int)totalPosts/pageSize + ((totalPosts%pageSize)>0?1:0); //����ó�����ҳ��  	    
	    session.setAttribute("pageSize", pageSize);  
	    session.setAttribute("totalPosts", totalPosts);  
	    session.setAttribute("pageNumber", pageNumber);  
	    session.setAttribute("totalPages", totalPages);  
	     //��ҳ����
	     //��������	    
		boolean flag=collectDao.cancelCollect(co_id);
		 //ҳ����ת
 	    request.getRequestDispatcher("/servlet/ShowCollectServlet").forward(request, response); 	    		 
	}
}
