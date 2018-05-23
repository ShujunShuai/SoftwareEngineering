package servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.AttentionDao;
import dao.CancleFollowingDao;


import entity.User;

public class CancelFollowingServlet extends HttpServlet {

	private static final long serialVersionUID = 3166884069812705339L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {		
		doPost(request,response);
}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		 //���տͻ�������
		HttpSession session=request.getSession();
		User User=(User)session.getAttribute("User");		
		int uid=User.getU_id();
		int fid=Integer.parseInt(request.getParameter("fid"));
		 //����Dao�㣬��������	
		CancleFollowingDao cancelFollowingDao = new CancleFollowingDao();
		cancelFollowingDao.cancelFollowing(uid, fid);
		 //���ûỰ���ٻ��ƣ����лỰ��Ӧ
		session.setAttribute("userId", User.getU_id());
		AttentionDao attentionDao=new AttentionDao();
 		session.setAttribute("attention", attentionDao.accountAttention(User.getU_id()));
 		session.setAttribute("fans",attentionDao.accountFans(User.getU_id()));		
 		String type=request.getParameter("type");
 		 if("following".equals(type)){
 			response.sendRedirect("servlet/ShowFollowingServlet");
 	    }
 	    else if("friend".equals(type)){
 	    	response.sendRedirect("servlet/ShowFriendsServlet");
 	    }		
	}
}
