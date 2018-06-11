package src.servlet;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.dao.AttentionDao;
import src.entity.Artical;
import src.entity.User;

public class AddFollowingServlet extends HttpServlet {
	private static final long serialVersionUID = -5355669106462303247L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		//���տͻ�������
		User User=(User)session.getAttribute("User");		
		int uid=User.getU_id();
		int fid=Integer.parseInt(request.getParameter("goal_uid"));
				
		ArrayList<Artical> listAll=new ArrayList<Artical>();
		//---------//////////---��ҳ����-------------------//	 	
	 	//��ҳ
	 	String pageNumberStr = request.getParameter("pageNumber");  
	    int pageNumber = 1;  
	    if(pageNumberStr!=null && !pageNumberStr.isEmpty())  
	    {  
	        pageNumber = Integer.parseInt(pageNumberStr);  
	    }  
	    int pageSize = 3; //��ҳ��С  
	    AttentionDao attentionDao=new AttentionDao();
	    int totalPosts = attentionDao.allArticals(User.getU_id()); //��������  
	    int totalPages = (int)totalPosts/pageSize + ((totalPosts%pageSize)>0?1:0); //����ó�����ҳ��  
	    session.setAttribute("pageSize", pageSize);  
	    session.setAttribute("totalPosts", totalPosts);  
	    session.setAttribute("pageNumber", pageNumber);  
	    session.setAttribute("totalPages", totalPages);  
	     //��ҳ����
	     //��������
		boolean flag=attentionDao.addAttention(uid, fid);
	 	listAll=attentionDao.getArtical(User.getU_id(), pageSize, pageNumber);	 	
	 	session.setAttribute("listAll", listAll);
 	    session.setAttribute("userId", User.getU_id()); 	    
 	    request.getRequestDispatcher("/user.jsp").forward(request, response);
		System.out.println(flag);
	}

}
