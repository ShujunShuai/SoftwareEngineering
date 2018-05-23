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

public class DeleteArticalServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4281028282262615973L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		int bid=Integer.parseInt(request.getParameter("bid"));
		int uid=Integer.parseInt(session.getAttribute("userId").toString());
		AttentionDao attentionDao=new AttentionDao();
		attentionDao.deleteArtical(uid, bid);
	 	///////////----------��ҳ����--------////////
		MyArticalDao myArticalDao=new MyArticalDao();
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
	 	session.setAttribute("curArticals", totalPosts);
		session.setAttribute("userId", uid);
		response.sendRedirect("/CodecoreMicroArtical/profile.jsp");
	}
}
