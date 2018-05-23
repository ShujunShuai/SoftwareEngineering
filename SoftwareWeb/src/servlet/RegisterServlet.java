package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AttentionDao;
import dao.RegisterDao;
import entity.Artical;
import entity.User;

public class RegisterServlet extends HttpServlet {

	/**
	 * ע����֤
	 */
	private static final long serialVersionUID = -478052340582521550L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String mail=request.getParameter("email");
		String nick=request.getParameter("nickname");
		String pwd=request.getParameter("npassword");
		String rpwd=request.getParameter("rpassword");
		String sex=request.getParameter("sex");
		String addr=request.getParameter("city");
		String jsEmail=request.getParameter("jsemail");
		//���
		RegisterDao registerDao=new RegisterDao();
		User User=new User();
		User.setU_mail(mail);
		User.setU_password(rpwd);
		User.setU_nick(nick);
		User.setU_addr(addr);
		User.setU_sex(sex);
		User.setU_account(mail);
		User.setU_img("/CodecoreMicroArtical/face/NoName.jpg");
		boolean flag=registerDao.addUser(User);
		if (flag) {
			HttpSession session=request.getSession();
			User=registerDao.getInfoByAccount(mail);
			//---------//////////---��ҳ����-------------------//	 
			AttentionDao attentionDao=new AttentionDao();
			ArrayList<Artical> listAll=new ArrayList<Artical>();
    	 	//��ҳ
    	 	String pageNumberStr = request.getParameter("pageNumber");  
    	    int pageNumber = 1;  
    	    if(pageNumberStr!=null && !pageNumberStr.isEmpty())  
    	    {  
    	        pageNumber = Integer.parseInt(pageNumberStr);  
    	    }  
    	    int pageSize = 3; //��ҳ��С  
    	    int totalPosts = attentionDao.allArticals(User.getU_id()); //��������  
    	    int totalPages = (int)totalPosts/pageSize + ((totalPosts%pageSize)>0?1:0); //����ó�����ҳ��  
    	    session.setAttribute("pageSize", pageSize);  
    	    session.setAttribute("totalPosts", totalPosts);  
    	    session.setAttribute("pageNumber", pageNumber);  
    	    session.setAttribute("totalPages", totalPages);  
    	 	listAll=attentionDao.getArtical(User.getU_id(), pageSize, pageNumber);
    	 	
    	 	session.setAttribute("listAll", listAll);
			
			session.setAttribute("userId", User.getU_id());
			request.getRequestDispatcher("/home.jsp").forward(request, response);
		}else{
		response.sendRedirect("/CodecoreMicroArtical/register.jsp?msg=6");
		}
	}

}
