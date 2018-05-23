package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBconn;
import entity.Artical;
public class IntersetArticalDao {
	

		//���ݸ���Ȥ���˵�ID�������ṩ����ǰ�û����Ҽӹ�ע
		public List<Artical> getInterestArticalByUid(final int uid){
			List<Artical> interestArticallist = new ArrayList<Artical>();
			String strSQL = "SELECT * from Artical where u_id=? limit 0,5";
			DBconn DBconn = new DBconn();
			ResultSet rs = DBconn.execQuery(strSQL, new Object[]{uid});
			try {
			
				while(rs.next()) {
					Artical interestArtical=new Artical();
					interestArtical.setB_content(rs.getString("b_content"));
					interestArtical.setB_time(rs.getDate("b_time"));
					interestArtical.setU_id(rs.getInt("u_id"));
					interestArtical.setB_img(rs.getString("b_img"));
					interestArtical.setB_degree(rs.getInt("b_degree"));
					interestArtical.setB_id(rs.getInt("b_id"));
					interestArtical.setB_date(rs.getTime("b_time"));
					interestArticallist.add(interestArtical);
				}
			   	return interestArticallist;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}finally{
				DBconn.closeConn();
			}
			
		}

	}

