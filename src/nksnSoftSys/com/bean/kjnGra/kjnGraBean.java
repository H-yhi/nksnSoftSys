package nksnSoftSys.com.bean.kjnGra;

import java.io.Serializable;

public class KjnGraBean implements Serializable {

	private String userId; // ユーザーID
	private int game; // 試合数
	private int atBat; // 打数
	private int batCon; // 打席
	private int hit; // 安打
	private int secHit; // 二塁打
	private int thrHit; // 三塁打
	private int homeRun; // 本塁打
	private int rbi; // 打点
	private int stBase; // 盗塁
	private int foBall; // 四球
	private int deBall; // 死球
	private int sacRoll; // 犠打
	private int sacFly; // 犠飛
	private String ave; // 打率
	private String onBaseAve; // 出塁率
	private String name; // 名前

	public KjnGraBean() {}
	public KjnGraBean(String userId) {
		this.userId = userId;
	}
	public KjnGraBean(String userId, String name, int game,
			int atBat, int batCon, int hit,
			int secHit, int thrHit, int homeRun,
			int rbi, int stBase, int foBall,
			int deBall, int sacRoll, int sacFly) {
		this.userId = userId;
		this.name = name;
		this.game = game;
		this.atBat = atBat;
		this.batCon = batCon;
		this.hit = hit;
		this.secHit = secHit;
		this.thrHit = thrHit;
		this.homeRun = homeRun;
		this.rbi = rbi;
		this.stBase = stBase;
		this.foBall = foBall;
		this.deBall = deBall;
		this.sacRoll = sacRoll;
		this.sacFly = sacFly;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getGame() {
		return game;
	}
	public void setGame(int game) {
		this.game = game;
	}
	public int getAtBat() {
		return atBat;
	}
	public void setAtBat(int atBat) {
		this.atBat = atBat;
	}
	public int getBatCon() {
		return batCon;
	}
	public void setBatCon(int batCon) {
		this.batCon = batCon;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getSecHit() {
		return secHit;
	}
	public void setSecHit(int secHit) {
		this.secHit = secHit;
	}
	public int getThrHit() {
		return thrHit;
	}
	public void setThrHit(int thrHit) {
		this.thrHit = thrHit;
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
	public int getStBase() {
		return stBase;
	}
	public void setStBase(int stBase) {
		this.stBase = stBase;
	}
	public int getFoBall() {
		return foBall;
	}
	public void setFoBall(int foBall) {
		this.foBall = foBall;
	}
	public int getDeBall() {
		return deBall;
	}
	public void setDeBall(int deBall) {
		this.deBall = deBall;
	}
	public int getSacRoll() {
		return sacRoll;
	}
	public void setSacRoll(int sacRoll) {
		this.sacRoll = sacRoll;
	}
	public int getSacFly() {
		return sacFly;
	}
	public void setSacFly(int sacFly) {
		this.sacFly = sacFly;
	}
	public String getAve() {
		return ave;
	}
	public void setAve(String ave) {
		this.ave = ave;
	}
	public String getOnBaseAve() {
		return onBaseAve;
	}
	public void setOnBaseAve(String onBaseAve) {
		this.onBaseAve = onBaseAve;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
