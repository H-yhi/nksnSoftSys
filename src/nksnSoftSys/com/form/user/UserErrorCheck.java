package nksnSoftSys.com.form.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserErrorCheck {

	public UserErrorCheck() {}

	public boolean userIdPassNameBlankCheck(String userId, String pass, String name) {
		if(userId.equals("") || pass.equals("") || name.equals("")) {
			return true;
		}
		return false;
	}

	public boolean userIdPassLengthCheck(String userId, String pass) {
		if(userId.length() != 4 || pass.length() != 4) {
			return true;
		}
		return false;
	}

	public boolean userIdMojiCheck(String userId) {
		String regex_num = "^[0-9]+$" ; // 数値のみ
		Pattern p = Pattern.compile(regex_num);
		Matcher m = p.matcher(userId);
		boolean result = m.matches();
		if(result == false) {
			return true;
		}
		return false;
	}

	public boolean passMojiCheck(String pass) {
		String regex_num = "^[0-9]+$" ; // 数値のみ
		Pattern p = Pattern.compile(regex_num);
		Matcher m = p.matcher(pass);
		boolean result = m.matches();
		if(result == false) {
			return true;
		}
		return false;
	}
}

