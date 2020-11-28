package nksnSoftSys.com.bean.con;

import java.io.Serializable;

public class ConBean implements Serializable {

	private String conId; // カウントID
	private int con; // カウント

	public ConBean() {}
	public ConBean(String conId, int con) {
		this.conId = conId;
		this.con = con;
	}

	public String getConId() {
		return conId;
	}

	public void setConId(String conId) {
		this.conId = conId;
	}

	public int getCon() {
		return con;
	}

	public void setCon(int con) {
		this.con = con;
	}


}
