package com.Lu.omw.bean;

public class DicBean {

	private int _id;
	private String en_name;
	private String cn_name;

	public DicBean() {
	}

	public DicBean(int _id, String en_name, String cn_name) {
		this._id = _id;
		this.en_name = en_name;
		this.cn_name = cn_name;
	}
	
	public void setParams(String params){
		String[] pas = params.split("/");
		setEn_name(pas[0]);
		setCn_name(pas[1]);
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getEn_name() {
		return en_name;
	}

	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}

	public String getCn_name() {
		return cn_name;
	}

	public void setCn_name(String cn_name) {
		this.cn_name = cn_name;
	}

	@Override
	public String toString() {
		return "DicBean [_id=" + _id + ", en_name=" + en_name + ", cn_name="
				+ cn_name + "]";
	}
}
