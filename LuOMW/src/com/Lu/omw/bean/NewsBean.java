package com.Lu.omw.bean;

public class NewsBean {

	private String name;
	private String picSmall;
	private String description;
	@Override
	public String toString() {
		return "NewsBean [name=" + name + ", picSmall=" + picSmall
				+ ", description=" + description + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicSmall() {
		return picSmall;
	}
	public void setPicSmall(String picSmall) {
		this.picSmall = picSmall;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public NewsBean(String name, String picSmall, String description) {
		super();
		this.name = name;
		this.picSmall = picSmall;
		this.description = description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((picSmall == null) ? 0 : picSmall.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewsBean other = (NewsBean) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (picSmall == null) {
			if (other.picSmall != null)
				return false;
		} else if (!picSmall.equals(other.picSmall))
			return false;
		return true;
	}
}
