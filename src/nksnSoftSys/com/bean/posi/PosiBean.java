package nksnSoftSys.com.bean.posi;

import java.io.Serializable;
public class PosiBean implements Serializable{

	private String posiId; // 守備位置id
	private String posiName; // 守備位置

	public PosiBean() {}
	public PosiBean(String posiId, String posiName) {
		this.posiId = posiId;
		this.posiName = posiName;
	}

	public String getPosiId() {
		return posiId;
	}
	public void setPosiId(String posiId) {
		this.posiId = posiId;
	}
	public String getPosiName() {
		return posiName;
	}
	public void setPosiName(String posiName) {
		this.posiName = posiName;
	}
}
