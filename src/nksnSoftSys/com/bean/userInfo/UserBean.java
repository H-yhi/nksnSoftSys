package nksnSoftSys.com.bean.userInfo;

import java.io.Serializable;

public class UserBean implements Serializable {

	private String name; // 名前
	private String userId; // ユーザーid
	private String pass; // パスワード
	private String posiId; // 守備位置id
	private String posiName; // 守備位置名前
	private int game; // 試合数
	private String ave; // 打率
	private int hit; // 安打
	private int homeRun; // 本塁打
	private int rbi; //打点
	private String onBaseAve; //  出塁率
	private String handId; // 効き投げ効き打ちID
	private String autFlg; // 権限フラグ

	public UserBean() {};

	public UserBean(String userId, String pass, String name, String autFlg) {
		this.userId = userId;
		this.pass = pass;
		this.name = name;
		this.autFlg = autFlg;
	}

	public UserBean(String name, String posiName) {
		this.name = name;
		this.posiName = posiName;
	}

	public UserBean(String pass, String name, String posiId, String handId, String autFlg, String userId) {
		this.pass = pass;
		this.name = name;
		this.posiId = posiId;
		this.handId = handId;
		this.autFlg = autFlg;
		this.userId = userId;
	}

	public UserBean(String userId, String name, String posiName, int game, String ave, int hit, int homeRun, int rbi, String onBaseAve) {
		this.userId = userId;
		this.name = name;
		this.posiName = posiName;
		this.game = game;
		this.ave = ave;
		this.hit = hit;
		this.homeRun = homeRun;
		this.rbi = rbi;
		this.onBaseAve = onBaseAve;
	}
	public String getPosiId() {
		return posiId;
	}
	public void setPosiId(String posiId) {
		this.posiId = posiId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPosiName() {
		return posiName;
	}

	public void setPosiName(String posiName) {
		this.posiName = posiName;
	}

	public int getGame() {
		return game;
	}

	public void setGame(int game) {
		this.game = game;
	}

	public String getAve() {
		return ave;
	}

	public void setAve(String ave) {
		this.ave = ave;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getHomeRun() {
		return homeRun;
	}

	public void setHomeRun(int homeRun) {
		this.homeRun = homeRun;
	}

	public int getRbi() {
		return rbi;
	}

	public void setRbi(int rbi) {
		this.rbi = rbi;
	}

	public String getOnBaseAve() {
		return onBaseAve;
	}

	public void setOnBaseAve(String onBaseAve) {
		this.onBaseAve = onBaseAve;
	}

	public String getHandId() {
		return handId;
	}

	public void setHandId(String handId) {
		this.handId = handId;
	}

	public String getAutFlg() {
		return autFlg;
	}

	public void setAutFlg(String autFlg) {
		this.autFlg = autFlg;
	}


}
