package nksnSoftSys.com.dao.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nksnSoftSys.com.bean.userInfo.UserBean;

public class UserDao {
	final String jdbcId = "root";
	final String jdbcPass = "root";
	final String jdbcUrl = "jdbc:mysql://localhost:3306/nksnSoftSys?autoReconnect=true&useSSL=false";

	public UserBean checkUser(String userId, String pass){
		Connection con = null;
		try{

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);

			String sql = "SELECT user_id, pass, name, aut_flg FROM USER_INFO WHERE user_id = ? AND pass = ?";
			PreparedStatement ps= con.prepareStatement(sql);

			ps.setString(1, userId);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				String userId2 = rs.getString("user_id");
				String pass2 = rs.getString("pass");
				String name2 = rs.getString("name");
				String autFlg = rs.getString("aut_flg");
				UserBean userBean = new UserBean(userId2,pass2,name2,autFlg);
				return userBean;
			}else {
				return null;
			}
		}catch(SQLException e){
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
		}
	}

	// 20201122 start
	//public Boolean regUser(String userId, String pass, String name, String posiId) {
	public Boolean regUser(String userId, String pass, String name, String posiId, String handId, String autFlg) {
	// 20201122 end
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);

			// 20201122 start
			//String sql = "INSERT INTO USER_INFO(user_id,pass,name,posi_id) VALUES(?,?,?,?)";
			String sql = "INSERT INTO USER_INFO(user_id,pass,name,posi_id,hand_id,aut_Flg) VALUES(?,?,?,?,?,?)";
			// 20201122 end
			PreparedStatement ps= con.prepareStatement(sql);

			ps.setString(1, userId);
			ps.setString(2, pass);
			ps.setString(3, name);
			ps.setString(4, posiId);
			// 20201122 start
			ps.setString(5, handId);
			ps.setString(6, autFlg);
			// 20201122 end
			ps.executeUpdate();
			return true;

		}catch(SQLException e){
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
					return null;
				}
			}
		}
	}

	public List<UserBean> findAll(){
		Connection con = null;
		List<UserBean> userBeanList = new ArrayList<UserBean>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);

			String sql1 = "select user_info.user_id,name, posi_name, game,cast(((hit) + (sec_hit * 2) + (thr_hit * 3) + (home_run * 4)) / bat_con as char) as long_hit_ave,";
			String sql2 = "cast(( (hit + sec_hit + thr_hit + home_run) / bat_con) as char) as ave,fo_ball,de_ball,st_base,sac_roll,sac_fly,";
			String sql3 = "home_run, rbi, hit + sec_hit + thr_hit + home_run as all_hit,cast((((hit) + (sec_hit *2) + (thr_hit  * 3) + (home_run * 4)) / bat_con) + ((hit + sec_hit + thr_hit + home_run + fo_ball + de_ball) / (bat_con + fo_ball + de_ball + sac_fly)) as char) as ops,";
			String sql4 = "cast((hit + sec_hit + thr_hit + home_run + fo_ball + de_ball) / (bat_con + fo_ball + de_ball + sac_fly) as char) as on_base_ave";
			String sql5 = "from user_info";
			String sql6 = "inner join posi_tbl";
			String sql7 = "inner join kjn_gra_tbl";
			String sql8 = "where user_info.posi_id = posi_tbl.posi_id and user_info.user_id = kjn_gra_tbl.user_id";
			String sql9 = "order by user_info.posi_id asc, name asc";
			String sql10 = sql1 + " " + sql2 + " " + sql3 + " " + sql4 + " " + sql5 + " " + sql6 + " " + sql7 + " " + sql8 + " " + sql9;
			PreparedStatement ps= con.prepareStatement(sql10);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				//String sLongHitAve = null;
				//String sOps = null;
				String userId = rs.getString("user_id");
				String name = rs.getString("name");
				String posiName = rs.getString("posi_name");
				int game = rs.getInt("game");
				String ave = rs.getString("ave");
				int hit = rs.getInt("all_hit");
				int homeRun = rs.getInt("home_run");
				int rbi = rs.getInt("rbi");
				String longHitAve = rs.getString("long_hit_ave");
				String onBaseAve = rs.getString("on_base_ave");
				int foBall = rs.getInt("fo_ball");
				int deBall = rs.getInt("de_ball");
				int stBase = rs.getInt("st_base");
				String ops = rs.getString("ops");
				int sacRoll = rs.getInt("sac_roll");
				int sacFly = rs.getInt("sac_fly");
				if (ave == null) {
					ave = "---";
				}else if(ave.equals("1.0000")) {
					ave = ave.substring(0,5);
				}else {
					ave = ave.substring(1,5);
				}
				if (onBaseAve == null) {
					onBaseAve = "---";
				}else if (onBaseAve.equals("1.0000")) {
					onBaseAve = onBaseAve.substring(0,5);
				}else {
					onBaseAve = onBaseAve.substring(1,5);
				}

				//double dLongHitAve = Double.parseDouble(longHitAve);
				if (longHitAve == null) {
					longHitAve = "---";
					//sLongHitAve = String.valueOf(longHitAve);
					//sLongHitAve = sLongHitAve.substring(0,3);
				}else if(longHitAve.startsWith("0")) {
					//sLongHitAve = String.valueOf(longHitAve);
					//sLongHitAve = sLongHitAve.substring(1,3);
					longHitAve = longHitAve.substring(1,5);
				}else {
					longHitAve = longHitAve.substring(0,5);;
				}
				if (ops == null) {
					//sOps = String.valueOf(ops);
					//sOps = sOps.substring(0,3);
					ops = "---";
				}else if(ops.startsWith("0")) {
					//sOps = String.valueOf(ops);
					//sOps = sOps.substring(1,3);
					ops = ops.substring(1,5);
				}else {
					ops = ops.substring(0,5);;
				}
				UserBean userBean = new UserBean(userId,name,posiName,
						game,ave, hit,
						homeRun,rbi,onBaseAve,
						longHitAve,foBall,deBall,
						stBase,ops,sacRoll,sacFly);
				userBeanList.add(userBean);
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
		}
		return userBeanList;
	}

	public boolean userDell(String userId) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
			String sql = "DELETE FROM USER_INFO WHERE USER_ID = ?";
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} catch(Exception e) {
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

	public UserBean userFind(String userId) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
			String sql1 = "select pass,name,s.posi_id,s.hand_id,s.aut_flg,user_id from user_info as s";
			String sql2 = "inner join posi_tbl as p";
			String sql3 = "inner join hand_tbl as h";
			String sql4 = "inner join aut_tbl as a";
			String sql5 = "where s.posi_id = p.posi_id and s.hand_id = h.hand_id";
			String sql6 = "and s.aut_flg = a.aut_id";
			String sql7 = "and s.user_id = ?";
			String sql8 = sql1 + " " + sql2 + " " + sql3 + " " + sql4 + " " + sql5 + " " + sql6 + " " + sql7;


			PreparedStatement ps= con.prepareStatement(sql8);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				String posiId = rs.getString("s.posi_id");
				String handId = rs.getString("s.hand_id");
				String autId = rs.getString("s.aut_flg");
				String userId1 = rs.getString("user_id");
				UserBean userBean = new UserBean(pass,name,posiId,handId,autId,userId1);
				return userBean;
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
		}return null;
	}

	public boolean userUp(String pass, String name, String posiId, String handId, String autFlg, String userId) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
			String sql1 = "update user_info set pass = ?, name = ?, posi_id = ?, hand_id = ?, aut_flg = ?";
			String sql2 = "where user_id = ?";
			String sql3 = sql1 + " " + sql2;
			PreparedStatement ps= con.prepareStatement(sql3);
			ps.setString(1,pass);
			ps.setString(2,name);
			ps.setString(3,posiId);
			ps.setString(4,handId);
			ps.setString(5,autFlg);
			ps.setString(6,userId);
			ps.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} catch(Exception e) {
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
