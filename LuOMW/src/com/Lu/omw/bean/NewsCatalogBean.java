package com.Lu.omw.bean;

import java.util.List;

/**
 * @author robin
 * 新闻实体目录
 */
public class NewsCatalogBean {
	public String msg;
	public String status;
	public List<NewsBean> data;

	public static class NewsBean {
		public int id;
		public String description;
		public String learner;
		public String name;
		public String picBig;
		public String picSmall;
	}
}
