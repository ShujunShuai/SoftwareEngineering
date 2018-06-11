package src.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.dao.CollectDao;
import src.entity.Collect;
import src.entity.User;

public class ShowCollectServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 	
		 //���տͻ������� 	
		HttpSession session=request.getSession();
		int uid=0;
	 	if (request.getParameter("curUid")!=null) {
	 		uid=Integer.parseInt(request.getParameter("curUid"));
		}else{
			User User=(User)session.getAttribute("User");
			uid=User.getU_id();
		}	 		
	 	///////////----------��ҳ����--------////////
	 	int pageNumber = 0;  
	 	if (request.getParameter("pageNumber")!=null) {
	 		pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}else
			pageNumber=1;
	    int pageSize = 3; //��ҳ��С  
	    CollectDao collectDao=new CollectDao();
	    int totalPosts = collectDao.allCollects(uid); //��������  
	    int totalPages = (int)totalPosts/pageSize + ((totalPosts%pageSize)>0?1:0); //����ó�����ҳ��  	    
	    session.setAttribute("pageSize", pageSize);  
	    session.setAttribute("totalPosts", totalPosts);  
	    session.setAttribute("pageNumber", pageNumber);  
	    session.setAttribute("totalPages", totalPages);	 	
	 	//////////-------��ҳ����----------/////////////
	     //��������
	    List<Collect> listAll=new ArrayList<Collect>();	 
	    listAll=collectDao.getCollect(uid, pageSize, pageNumber);
	 	session.setAttribute("listAllCollect", listAll);	
	 	 //���ݴ�������Ӧ�ͻ���
	 	response.sendRedirect("/CodecoreMicroblog/collect.jsp");
	}
}
