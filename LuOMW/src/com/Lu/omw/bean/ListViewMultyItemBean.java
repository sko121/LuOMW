package com.Lu.omw.bean;

import java.util.Arrays;

public class ListViewMultyItemBean {
	private String title;
	private String content;
	private int[] imgs;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + Arrays.hashCode(imgs);
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
		ListViewMultyItemBean other = (ListViewMultyItemBean) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (!Arrays.equals(imgs, other.imgs))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int[] getImgs() {
		return imgs;
	}
	public void setImgs(int[] imgs) {
		this.imgs = imgs;
	}
	public ListViewMultyItemBean() {
	}
	public ListViewMultyItemBean(String title, String content, int[] imgs) {
		super();
		this.title = title;
		this.content = content;
		this.imgs = imgs;
	}
}
