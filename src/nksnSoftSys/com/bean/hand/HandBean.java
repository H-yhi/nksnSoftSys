package nksnSoftSys.com.bean.hand;

import java.io.Serializable;

public class HandBean implements Serializable {

	private String handId; // 効き投げ効き打ちID
	private String hand; // 効き投げ効き打ち

	public HandBean() {}
	public HandBean(String handId, String hand) {
		this.handId = handId;
		this.hand = hand;
	}

	public String getHandId() {
		return handId;
	}

	public void setHandId(String handid) {
		this.handId = handid;
	}

	public String getHand() {
		return hand;
	}

	public void setHand(String hand) {
		this.hand = hand;
	}


}
