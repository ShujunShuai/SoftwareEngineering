package src.dao;

import java.sql.ResultSet;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.util.DBconn;
import src.entity.BookInLibrary;
import src.entity.User;

/**
 * 图书库存信息修改
 * 
 */
public class BookInLibraryDao {
	// 获取图书信息
	public List<BookInLibrary> getBookById(final int bo_id,int pageSize, int pageNo) {
		List<BookInLibrary> lstBooks=new ArrayList<BookInLibrary>();
		String strSQL = "select * from BookInLibrary where bo_id = ? limit ?,?";
		DBconn DBconn = new DBconn();
		ResultSet rs = DBconn.execQuery(strSQL, new Object[] { bo_id,pageSize*(pageNo-1), pageSize });
		BookInLibrary book = new BookInLibrary();
		try {
			while (rs.next()) {
				book.setbo_id(rs.getInt(10));
				book.setbo_liId(rs.getInt(10));
				book.setbo_status(rs.getString(5));
				lstBooks.add(book);
			}
			return lstBooks;
		} catch (Exception e) {
			return null;
		} finally {
			DBconn.closeConn();
		}
		
	}
	public List<BookInLibrary> getBookByLi(final int Li_id,int pageSize, int pageNo) {
		List<BookInLibrary> lstBooks=new ArrayList<BookInLibrary>();
		String strSQL = "select * from BookInLibrary where bo_liId= ? limit ?,?";
		DBconn DBconn = new DBconn();
		ResultSet rs = DBconn.execQuery(strSQL, new Object[] { Li_id,pageSize*(pageNo-1), pageSize });
		BookInLibrary book = new BookInLibrary();
		try {
			while (rs.next()) {
				book.setbo_id(rs.getInt(10));
				book.setbo_liId(rs.getInt(10));
				book.setbo_status(rs.getString(5));
				lstBooks.add(book);
			}
			return lstBooks;
		} catch (Exception e) {
			return null;
		} finally {
			DBconn.closeConn();
		}
		
		
	}
	public int accountBooks(final int id){
		DBconn DBconn=new DBconn();
		String sql="select count(*) from BookInLibrary where bo_liId =?";
		ResultSet rs=DBconn.execQuery(sql, new Object[]{id});
		int num=0;
		try {
			while (rs.next()) {
				num=rs.getInt("count(*)");
			}
			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}finally{
			DBconn.closeConn();
		}
	}
   
   
	// 保存图书修改完的资料
	public boolean addUser(BookInLibrary book) {
		DBconn DBconn = new DBconn();
		String sql = "insert into BookInLibrary (bo_id, bo_status, bo_library) values(?, ?, ?)";
		int result = 0;
		result = DBconn.execOther(sql, new Object[] { book.getbo_id(),
				book.getbo_status(),book.getbo_liId() });
		DBconn.closeConn();
		return result > 0 ? true : false;
	}

	



	
}
