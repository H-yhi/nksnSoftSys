package nksnSoftSys.com.dao.hand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nksnSoftSys.com.bean.hand.HandBean;

public class HandDao {

	final String jdbcId = "root";
	final String jdbcPass = "root";
	final String jdbcUrl = "jdbc:mysql://localhost:3306/nksnSoftSys?autoReconnect=true&useSSL=false";

	public List<HandBean> handFind() {
		Connection con = null;
		List<HandBean> handBeanList = new ArrayList<HandBean>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
			String sql = "SELECT HAND_ID, HAND FROM HAND_TBL ORDER BY HAND_ID ASC";
			PreparedStatement ps= con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String handId = rs.getString("HAND_ID");
				String hand = rs.getString("HAND");
				HandBean handBean = new HandBean(handId, hand);
				handBeanList.add(handBean);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}return handBeanList;
	}
}
