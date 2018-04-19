package com.Lu.omw.bean;

public class MyNews {
	private int id;
	private String title;
	private int timeLength;
	
	public MyNews() {
	}
	public MyNews(int id, String title, int timeLength) {
		super();
		this.id = id;
		this.title = title;
		this.timeLength = timeLength;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getTimeLength() {
		return timeLength;
	}
	public void setTimeLength(int timeLength) {
		this.timeLength = timeLength;
	}
	@Override
	public String toString() {
		return "MNews [id=" + id + ", title=" + title + ", timeLength="
				+ timeLength + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + timeLength;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		MyNews other = (MyNews) obj;
		if (id != other.id)
			return false;
		if (timeLength != other.timeLength)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
