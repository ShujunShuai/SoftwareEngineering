package src.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.dao.CollectDao;
import src.entity.Artical;
import src.entity.Collect;
import src.entity.User;

public class AddCollectServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");				  
	
	    Date   co_time   =   new   Date(System.currentTimeMillis());
	      
	    HttpSession session=request.getSession();
		User userInfo=(User)session.getAttribute("userInfo");
		int u_id=userInfo.getU_id(); 	
	    int b_id=Integer.parseInt(request.getParameter("bid"));
	    CollectDao collectDao = new CollectDao();
	    Artical artical=collectDao.getBlogsById(b_id);
	    String  co_content=artical.getB_content();
	    String  co_img=artical.getB_img();
	     //�����ݷ�װ��Collect����
	    Collect collect = new Collect();	    	    
	    collect.setU_id(u_id);
	    collect.setB_id(b_id);
	    collect.setCo_time(co_time);
	    collect.setCo_content(co_content);
	    collect.setCo_img(co_img);
	    //����Dao�������ݿ��������	   
	    boolean flag = collectDao.addcollect(collect);	   
	    //���ݴ��������пͻ�����Ӧ
	    int res = flag?1:2;
	    String type=request.getParameter("type");
	    if("home".equals(type)){
	    	response.sendRedirect("../home.jsp?msg="+res);
	    }
	    else if("profile".equals(type)){
	    	response.sendRedirect("../profile.jsp?msg="+res);
	    }		
	}
}

