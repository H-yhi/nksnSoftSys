package nksnSoftSys.com.dao.kjnGra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nksnSoftSys.com.bean.kjnGra.kjnGraBean;

public class KjnGraDao {
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

			String sql1 = "select k.user_id,name,game,at_bat,bat_con,hit,sec_hit,thr_hit,home_run,rbi,";
			String sql2 = "st_base,fo_ball,de_ball,sac_roll,sac_fly  from kjn_gra_tbl as k";
			String sql3 = "inner join user_info as u";
			String sql4 = "where u.user_id = k.user_id";
			String sql5 = sql1 + " " + sql2 + " " + sql3 + " " + sql4;
			PreparedStatement ps= con.prepareStatement(sql5);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String userId = rs.getString("k.user_id");
				String name = rs.getString("name");
				int game = rs.getInt("game");
				int atBat = rs.getInt("at_bat");
				int batCon = rs.getInt("bat_con");
				int hit = rs.getInt("hit");
				int secHit = rs.getInt("sec_hit");
				int thrHit = rs.getInt("thr_hit");
				int homeRun = rs.getInt("home_run");
				int rbi = rs.getInt("rbi");
				int stBase = rs.getInt("st_base");
				int foBall = rs.getInt("fo_ball");
				int deBall = rs.getInt("de_ball");
				int sacRoll = rs.getInt("sac_roll");
				int sacFly = rs.getInt("sac_fly");
				kjnGraBean kjnGraBean = new kjnGraBean(userId,name,game,
						atBat,batCon,hit,
						secHit,thrHit,homeRun,
						rbi,stBase,foBall,
						deBall,sacRoll,sacFly);
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

	public boolean kjnGraDell(String userId) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
			String sql = "DELETE FROM KJN_GRA_TBL WHERE user_id = ?";
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
}
