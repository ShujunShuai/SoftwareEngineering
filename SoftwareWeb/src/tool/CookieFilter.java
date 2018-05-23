package tool;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AttentionDao;
import dao.UploadFaceDao;
import entity.User;

public class CookieFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;
		HttpSession session = request.getSession();
		// ���session��û��user�����򴴽�һ����
		User User=(User)session.getAttribute("User");
		if (User==null) {
			if (session!=null) {
				User=getCookie(request, response);
				if (User!=null) {
					if (User.getU_account()!=null) {
						session.setAttribute("User", User);
						response.sendRedirect("/CodecoreMicroblog/LoginServlet");
						return;
					}
				}
			}
		}
		chain.doFilter(req, res);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	//��ȡcookie
	public User getCookie(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//��ȡCookie
        Cookie cookies[] = request.getCookies() ;
        Cookie c = null ;
        User User=new User();
        UploadFaceDao upload=new UploadFaceDao();
        if(cookies != null){
            for(int i=0;i<cookies.length;i++){
                c = cookies[i] ;
                if("userName".equals(c.getName())){
                    User.setU_account(c.getValue());
                }
                if ("password".equals(c.getName())) {
					User.setU_password(c.getValue());
				}
            }
         return User;
        }else{
        	return null;
        }
	}

}
