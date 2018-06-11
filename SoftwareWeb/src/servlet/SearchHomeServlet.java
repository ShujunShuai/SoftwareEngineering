 package src.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.dao.AttentionDao;
import src.dao.SearchDao;
import src.entity.Artical;
import src.entity.User;

public class SearchHomeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");				 	
	 	 //���ܿͻ�������
	 	String b_content=request.getParameter("textfield1").trim();	
	 	HttpSession session=request.getSession();
	 	User User=(User)session.getAttribute("User");
		int uid=User.getU_id();
		 //��ҳ��ʼ
	 	int pageNumber = 0;  
	 	if (request.getParameter("pageNumber")!=null) {
	 		pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}else
			pageNumber=1;
	    int pageSize = 3; //��ҳ��С  
	    SearchDao searchDao=new SearchDao();	
	    int totalPosts = searchDao.allSearchArticals(b_content,uid); //��������  
	    int totalPages = (int)totalPosts/pageSize + ((totalPosts%pageSize)>0?1:0); //����ó�����ҳ�� 
	    session.setAttribute("pageSize", pageSize);  
	    session.setAttribute("totalPosts", totalPosts);  
	    session.setAttribute("pageNumber", pageNumber);  
	    session.setAttribute("totalPages", totalPages);//��ҳ����
	     //��������	
	 	List<Artical> list =new ArrayList<Artical>();
		list =searchDao.searchHome(b_content,uid,pageSize, pageNumber); 	
		session.setAttribute("list", list);
         //���ݴ�������Ӧ�ͻ���
	 	request.getRequestDispatcher("../home1.jsp").forward(request, response);	 	 
	}
}
