 package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBconn;
import entity.Artical;
/**
 * ��ȡ��ǰ�û��Լ��Ĳ�����Ϣ
 * @author Vincent
 *
 */

public class MyArticalDao {
	//�����û���id�õ�������΢����Ϣ
	public List<Artical> getMyArticalByUid(final int uid, int pageSize, int pageNo){
		List<Artical> myArticallist = new ArrayList<Artical>();
		String strSQL = "SELECT * FROM Artical where u_id=(select u_id from User where u_id=?) order by b_time desc limit ?, ?";
		DBconn DBconn = new DBconn();
		ResultSet rs = DBconn.execQuery(strSQL, new Object[]{uid, pageSize*(pageNo-1), pageSize});
		try {
		
			while(rs.next()) {
				Artical myArtical = new Artical();
				myArtical.setB_content(rs.getString("b_content"));
				myArtical.setB_time(rs.getDate("b_time"));
				myArtical.setB_id(rs.getInt("b_id"));
				myArtical.setB_img(rs.getString("b_img"));
				myArtical.setB_date(rs.getTime("b_time"));
				myArticallist.add(myArtical);
			}
		   	return myArticallist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			DBconn.closeConn();
		}
	
	}
	
}
