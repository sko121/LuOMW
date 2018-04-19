package com.example.omw;

import com.Lu.omw.bean.User;

import android.app.Application;

public class MyApplication extends Application{

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
