 package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AttentionDao;
import dao.SearchDao;
import entity.Artical;
import entity.User;

public class SearchProfileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");				 	 	
	 	 //���ܿͻ�������
	 	String b_content=request.getParameter("textfield2").trim();
	 	HttpSession session=request.getSession();
		User User=(User)session.getAttribute("User");
		int uid=User.getU_id(); 
	 	 
        ///////////----------��ҳ����--------////////
	 	int pageNumber = 0;  
	 	if (request.getParameter("pageNumber")!=null) {
	 	    pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}else
			pageNumber=1;
	    int pageSize = 3; //��ҳ��С  
	    SearchDao searchDao=new SearchDao();
	    int totalPosts =  searchDao.allSearchMyArticals(b_content,uid); //��������  
	    int totalPages = (int)totalPosts/pageSize + ((totalPosts%pageSize)>0?1:0); //����ó�����ҳ��  	    
	    session.setAttribute("p_pageSize", pageSize);  
	    session.setAttribute("p_totalPosts", totalPosts);  
	    session.setAttribute("p_pageNumber", pageNumber);  
	    session.setAttribute("p_totalPages", totalPages);
	    
	 	//////////-------��ҳ����----------/////////////
	 	  //��������
	 	List<Artical> list =new ArrayList<Artical>();
		list =searchDao.searchProfile(b_content,uid,pageSize, pageNumber); 		 	 
		session.setAttribute("list", list);
		  //���ݴ�������Ӧ�ͻ���
	 	request.getRequestDispatcher("../profile1.jsp").forward(request, response);	 		 
	}
}
