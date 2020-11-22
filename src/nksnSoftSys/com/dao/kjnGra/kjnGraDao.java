package nksnSoftSys.com.dao.kjnGra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nksnSoftSys.com.bean.kjnGra.kjnGraBean;

public class kjnGraDao {
	final String jdbcId = "root";
	final String jdbcPass = "root";
	final String jdbcUrl = "jdbc:mysql://localhost:3306/nksnSoftSys?autoReconnect=true&useSSL=false";

	public boolean regKjnGra(String userId) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
			String sql = "INSERT INTO KJN_GRA_TBL(USER_ID) VALUES(?)";

			PreparedStatement ps= con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			if(con != null) {
				try {
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}return true;
	}

	public List<kjnGraBean> kjnGraFindAll() {
		Connection con = null;
		List<kjnGraBean> kjnGraList = new ArrayList<kjnGraBean>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);

			String sql1 = "select game,substring(cast((hit / at_bat) as char),2,4) as ave,";
			String sql2 = "hit, home_run, rbi,";
			String sql3 = "substring(cast((hit + fo_ball + de_ball) / (at_bat + fo_ball + de_ball + sac_fly) as char) ,2,4)as on_base_ave";
			String sql4 = "from kjn_gra_tbl";
			String sql5 = sql1 + " " + sql2 + " " + sql3 + " " + sql4;
			PreparedStatement ps= con.prepareStatement(sql5);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int game = rs.getInt("game");
				String ave = rs.getString("ave");
				int hit = rs.getInt("hit");
				int homeRun = rs.getInt("home_run");
				int rbi = rs.getInt("rbi");
				String onBaseAve = rs.getString("on_base_ave");
				kjnGraBean kjnGraBean = new kjnGraBean(game, ave, hit, homeRun, rbi, onBaseAve);
				kjnGraList.add(kjnGraBean);
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
		}
		return kjnGraList;
	}
}
