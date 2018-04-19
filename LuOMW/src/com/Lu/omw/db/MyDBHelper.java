package com.Lu.omw.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "mydb";

	public MyDBHelper(Context context) {
		super(context, DB_NAME, null, 6); // ��1��ʼ
	}

	//ֻ����һ��
	@Override
	public void onCreate(SQLiteDatabase db) { // manage database creation
		// ������
		db.execSQL("create table gift(_id integer primary key autoincrement,name text) ");
	}

	// manage version management
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// �������°汾:ɾ���ɱ������½�һ���ű�
		if (newVersion > oldVersion) {
			db.execSQL("drop table gift");
			onCreate(db);
		}
	}
}
