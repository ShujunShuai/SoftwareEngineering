package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AttentionDao;
import dao.MyArticalDao;
import entity.Artical;
import entity.User;

public class PageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3419721864595457070L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		MyArticalDao myArticalDao=new MyArticalDao();
		AttentionDao attentionDao=new AttentionDao();
		
		int uid=0;
	 	if (request.getParameter("curUid")!=null) {
	 		uid=Integer.parseInt(request.getParameter("curUid"));
		}else{
			User user=(User)session.getAttribute("User");
			uid=user.getU_id();
		}
	 	///////////----------��ҳ����--------////////
	 	int pageNumber = 0;  
	 	if (request.getParameter("pageNumber")!=null) {
	 		pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}else
			pageNumber=1;
	    int pageSize = 3; //��ҳ��С  
	    int totalPosts = (int)attentionDao.accountArticals(uid); //��������  
	    int totalPages = (int)totalPosts/pageSize + ((totalPosts%pageSize)>0?1:0); //����ó�����ҳ��  
	    session.setAttribute("p_pageSize", pageSize);  
	    session.setAttribute("p_totalPosts", totalPosts);  
	    session.setAttribute("p_pageNumber", pageNumber);  
	    session.setAttribute("p_totalPages", totalPages);
	    List<Artical> myArticallist  = myArticalDao.getMyArticalByUid(uid,pageSize, pageNumber);
	 	//////////-------��ҳ����----------/////////////
	 	session.setAttribute("ArticalList", myArticallist);
	 	response.sendRedirect("/CodecoreMicroArtical/profile.jsp");
//	 	request.getRequestDispatcher("/profile.jsp").forward(request, response);
	}

}
