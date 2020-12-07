package nksnSoftSys.com.dao.posi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nksnSoftSys.com.bean.posi.PosiBean;

public class PosiDao {
	final String jdbcId = "root";
	final String jdbcPass = "root";
	final String jdbcUrl = "jdbc:mysql://localhost:3306/nksnSoftSys?autoReconnect=true&useSSL=false";

	public List<PosiBean> posiFind() {
		Connection con = null;
		List<PosiBean> posiBeanList = new ArrayList<PosiBean>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);

			String sql = "SELECT * FROM POSI_TBL ORDER BY POSI_ID ASC";
			PreparedStatement ps= con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				String posiId = rs.getString("POSI_ID");
				String posiName = rs.getString("POSI_NAME");
				PosiBean posiBean = new PosiBean(posiId, posiName);
				posiBeanList.add(posiBean);
			}

		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}return posiBeanList;
	}
}
