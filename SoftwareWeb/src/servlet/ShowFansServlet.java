package src.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.dao.AttentionDao;
import src.dao.FansDao;
import src.entity.User;

public class ShowFansServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8348181881090708263L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

	doPost(request,response);
}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		HttpSession session=request.getSession();
		AttentionDao attentionDao=new AttentionDao();
		///////////----------��ҳ����--------////////
	 	//��ȡ��ǰ�û�id
	 	int uid=0;
	 	if (request.getParameter("u_id")!=null) {
	 		uid=Integer.parseInt(request.getParameter("u_id"));
		}else{
			User User=(User)session.getAttribute("User");
			uid=User.getU_id();
		}
	 	int pageNumber = 0;  
	 	if (request.getParameter("pageNumber")!=null) {
	 		pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}else
			pageNumber=1;
	    int pageSize = 10; //��ҳ��С  
	    int totalPosts = (int)attentionDao.accountFans(uid); //��������  
	    int totalPages = (int)totalPosts/pageSize + ((totalPosts%pageSize)>0?1:0); //����ó�����ҳ��  
	    session.setAttribute("f_pageSize", pageSize);  
	    session.setAttribute("f_totalPosts", totalPosts);  
	    session.setAttribute("f_pageNumber", pageNumber);  
	    session.setAttribute("f_totalPages", totalPages);
	 	//////////-------��ҳ����----------/////////////
		List<User> fanslist  = new FansDao().getFansByUid(uid, pageSize, pageNumber);
		request.setAttribute("fanslist", fanslist );
		session.setAttribute("pageFlag", "fans");
		request.getRequestDispatcher("../friend.jsp").forward(request, response);
	}
}
