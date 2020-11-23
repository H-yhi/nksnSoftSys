package nksnSoftSys.com.bean.aut;

import java.io.Serializable;

public class AutBean implements Serializable{

	private String autId; // 権限ID
	private String aut; // 権限

	public AutBean() {}


	public AutBean(String autId, String aut) {
		this.autId = autId;
		this.aut = aut;
	}


	public String getAutId() {
		return autId;
	}

	public void setAutId(String autId) {
		this.autId = autId;
	}

	public String getAut() {
		return aut;
	}

	public void setAut(String aut) {
		this.aut = aut;
	}


}
