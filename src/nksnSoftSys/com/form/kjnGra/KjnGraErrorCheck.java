package nksnSoftSys.com.form.kjnGra;

public class KjnGraErrorCheck {

	public KjnGraErrorCheck() {};

	// 入力値にマイナスがないか確認
	public boolean minCheck(int atBat, int batCon, int hit,
			int secHit, int thrHit, int homeRun,
			int rbi, int stBase, int foBall,
			int deBall, int sacRoll, int sacFly) {
		if(atBat < 0 || batCon < 0 || hit <0 ||
				secHit < 0 || thrHit < 0 || homeRun < 0 ||
				rbi < 0 || stBase < 0 || foBall < 0 ||
				deBall < 0 || sacRoll < 0 || sacFly < 0) {
			return true;
		}
		return false;
	}

	// 打席 < 打数のチェック
	public boolean batConCheck(int atBat, int batCon) {
		if(atBat < batCon) {
			return true;
		}
		return false;
	}

	// 打席 < ヒット数のチェック
	public boolean hitCheck(int atBat, int hit, int secHit, int thrHit, int homeRun) {
		int totalHit = hit + secHit + thrHit + homeRun;
		if(atBat < totalHit) {
			return true;
		}
		return false;
	}

	// ホームラン > 打点のチェック
	public boolean rbiCheck(int homeRun, int rbi) {
		if(homeRun > rbi) {
			return true;
		}
		return false;
	}

	// 打席 - 打数 == 四死球 + バント + 犠牲フライであるか
	public boolean atBatCheck(int atBat, int batCon, int foBall,
			int deBall, int secRoll, int secFly) {
		int total1 = atBat - batCon;
		int total2 = foBall + deBall + secRoll + secFly;
		if(total1 != total2) {
			return true;
		}
		return false;
	}

	public boolean gameCheck(int game, int atBat, int batCon, int hit,
			int secHit, int thrHit, int homeRun,
			int rbi, int stBase, int foBall,
			int deBall, int sacRoll, int sacFly) {
		int total = atBat + batCon + hit + secHit + thrHit + homeRun + rbi + stBase + foBall + deBall + sacRoll + sacFly;
		if(game == 0 && total != 0) {
			return true;
		}
		return false;
	}

}
