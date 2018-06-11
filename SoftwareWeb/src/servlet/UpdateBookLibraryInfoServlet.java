package src.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.dao.BookDao;
import src.entity.Book;
import src.dao.BookInLibraryDao;
import src.entity.BookInLibrary;
import src.util.DBconn;
import src.dao.SearchDao;
 

public class UpdateBookLibraryInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 4974697205377180444L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 doPost(request,response);
	}

	 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		BookDao bookDao =new BookDao();
		BookInLibraryDao bookInLibraryDao =new BookInLibraryDao();
		SearchDao search =new SearchDao();
		DBconn DBconn=new DBconn();		
		String sql="select * from TempLibrary order by bo_name desc ";
		ResultSet rs=DBconn.execQuery(sql, new Object[]{"%"});
		int res=1;
		try {
			while (rs.next()&&res!=2) {
				
				BookInLibrary bookLi=new BookInLibrary();
				Book book =new Book();
				if(search.searchBook(rs.getString("bo_name"))!=null) {
				bookLi.setbo_id(rs.getInt("bo_id"));
				bookLi.setbo_liId(rs.getInt("bo_liId"));
				bookLi.setbo_status(rs.getString("bo_status"));
				boolean flag =bookInLibraryDao.addUser(bookLi);
				res = flag?1:2;
			}
				else {
					book.setbo_author(rs.getString("bo_string"));
					book.setbo_name(rs.getString("bo_name"));
					book.setbo_press(rs.getString("bo_press"));
					book.setbo_date(rs.getDate("bo_date"));
					book.setbo_introduction(rs.getString("bo_introduction"));
					book.setbo_author(rs.getString("bo_author"));
					bookLi.setbo_id(rs.getInt("bo_id"));
					bookLi.setbo_liId(rs.getInt("bo_liId"));
					bookLi.setbo_status(rs.getString("bo_status"));
					boolean flag1 =bookDao.addUser(book);
					boolean flag2 =bookInLibraryDao.addUser(bookLi);
					res = (flag1&&flag2)?1:2;
					
				}
			
		} 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			res =2;	
		}finally{
			DBconn.closeConn();
		}		
		
		response.sendRedirect("../addLibraryBook.jsp?msg=" + res);

		 
	}
}

 
