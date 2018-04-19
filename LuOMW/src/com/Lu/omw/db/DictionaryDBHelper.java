package com.Lu.omw.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DictionaryDBHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "mydb";

	public DictionaryDBHelper(Context context) {
		super(context, DB_NAME, null, 6); // ��1��ʼ
	}

	//ֻ����һ��
	@Override
	public void onCreate(SQLiteDatabase db) { // manage database creation
		// ������
		db.execSQL("create table dic(_id integer primary key autoincrement,en_name text,cn_name text) ");
	}

	// manage version management
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// �������°汾:ɾ���ɱ������½�һ���ű�
		if (newVersion > oldVersion) {
			db.execSQL("drop table dic");
			onCreate(db);
		}
	}
}
