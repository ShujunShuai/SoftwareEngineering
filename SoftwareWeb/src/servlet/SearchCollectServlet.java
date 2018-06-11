 package src.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.dao.SearchDao;
import src.entity.Collect;
import src.entity.User;

public class SearchCollectServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");			 		 
	 	SearchDao searchDao=new SearchDao();	
	 	 //���ܿͻ�������
	 	String co_content=request.getParameter("textfield4").trim();	
	 	HttpSession session=request.getSession();
		User user=(User)session.getAttribute("User");
		int uid=user.getU_id(); 	
		 //��ҳ��ʼ
	 	int pageNumber = 0;  
	 	if (request.getParameter("pageNumber")!=null) {
	 		pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}else
			pageNumber=1;
	    int pageSize = 3; //��ҳ��С  
	    int totalPosts = searchDao.allSearchCollects(co_content,uid); //��������  
	    int totalPages = (int)totalPosts/pageSize + ((totalPosts%pageSize)>0?1:0); //����ó�����ҳ��  
	    session.setAttribute("pageSize", pageSize);  
	    session.setAttribute("totalPosts", totalPosts);  
	    session.setAttribute("pageNumber", pageNumber);  
	    session.setAttribute("totalPages", totalPages);
	 	 //��ҳ����
	     //��������
	 	List<Collect> list =new ArrayList<Collect>();
		list =searchDao.searchCollect(co_content,uid,pageSize, pageNumber); 		 	 
		session.setAttribute("listSearchCollect", list);
		 //ҳ����ת
	 	request.getRequestDispatcher("../collect1.jsp").forward(request, response);	 		 
	}
}
