package nksnSoftSys.com.dao.aut;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nksnSoftSys.com.bean.aut.AutBean;

public class AutDao {

	final String jdbcId = "root";
	final String jdbcPass = "root";
	final String jdbcUrl = "jdbc:mysql://localhost:3306/nksnSoftSys?autoReconnect=true&useSSL=false";

	public List<AutBean> findAll(){
		Connection con = null;
		List<AutBean> autBeanList = new ArrayList<AutBean>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);

			String sql = "SELECT AUT_ID, AUT FROM AUT_TBL ORDER BY AUT_ID ASC";
			PreparedStatement ps= con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String autId = rs.getString("AUT_ID");
				String aut = rs.getString("AUT");
				AutBean autBean = new AutBean(autId,aut);
				autBeanList.add(autBean);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		} return autBeanList;
	}
}
