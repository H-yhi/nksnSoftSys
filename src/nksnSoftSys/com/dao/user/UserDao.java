package nksnSoftSys.com.dao.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			String regex_num = "^[0-9]+$" ; // 数値のみ
			Pattern p1 = Pattern.compile(regex_num);
			Matcher m1 = p1.matcher(userId);
			Matcher m2 = p1.matcher(pass);
			boolean result11 = m1.matches();
			boolean result12 = m2.matches();
			if(userId == "" || pass == "" || name == "") {
				return false;
			}else if(userId.length() != 4 || pass.length() != 4) {
				return false;
			}else if(result11 == false || result12 == false) {
				return false;
			}
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
		List<UserBean> userBeanList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);

			String sql1 = "select user_info.user_id,name, posi_name, game,";
			String sql2 = "substring(cast((hit / at_bat) as char),2,4) as ave,";
			String sql3 = "hit, home_run, rbi,";
			String sql4 = "substring(cast((hit + fo_ball + de_ball) / (at_bat + fo_ball + de_ball + sac_fly) as char) ,2,4)as on_base_ave";
			String sql5 = "from user_info";
			String sql6 = "inner join posi_tbl";
			String sql7 = "inner join kjn_gra_tbl";
			String sql8 = "where user_info.posi_id = posi_tbl.posi_id and user_info.user_id = kjn_gra_tbl.user_id";
			String sql9 = "order by user_info.posi_id asc, name asc";
			String sql10 = sql1 + " " + sql2 + " " + sql3 + " " + sql4 + " " + sql5 + " " + sql6 + " " + sql7 + " " + sql8 + " " + sql9;
			PreparedStatement ps= con.prepareStatement(sql10);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String userId = rs.getString("user_id");
				String name = rs.getString("name");
				String posiName = rs.getString("posi_name");
				int game = rs.getInt("game");
				String ave = rs.getString("ave");
				int hit = rs.getInt("hit");
				int homeRun = rs.getInt("home_run");
				int rbi = rs.getInt("rbi");
				String onBaseAve = rs.getString("on_base_ave");
				if (ave == null) {
					ave = "---";
				}
				if (onBaseAve == null) {
					onBaseAve = "---";
				}
				UserBean userBean = new UserBean(userId,name,posiName,game,ave, hit,homeRun,rbi,onBaseAve);
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
}
