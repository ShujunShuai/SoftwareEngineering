package src.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import src.util.DBconn;
/**
 * 
 * @author 
 */

public class CountLibrary {
	
	public long countLibrary(){
		String sql="select count(*) from Library";
		DBconn dbConn=new DBconn();
		ResultSet rs1=dbConn.execQuery(sql, new Object[]{});
		try {
			rs1.next();
			long num=rs1.getInt(1);
			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return e.getErrorCode();
		}finally{
			dbConn.closeConn();
		}
	}
}
