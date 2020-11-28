package nksnSoftSys.com.dao.con;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nksnSoftSys.com.bean.con.ConBean;

public class ConDao {
	final String jdbcId = "root";
	final String jdbcPass = "root";
	final String jdbcUrl = "jdbc:mysql://localhost:3306/nksnSoftSys?autoReconnect=true&useSSL=false";

	public List<ConBean> conFindAll(){
		Connection con = null;
		List<ConBean> conBeanList = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
			String sql = "select con_id, con from con_tbl";
			PreparedStatement ps= con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String conId = rs.getString("con_id");
				int count = rs.getInt("con");
				ConBean conBean = new ConBean(conId,count);
				conBeanList.add(conBean);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}return conBeanList;
	}
}
