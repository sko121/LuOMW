package com.example.omw.provider;

import com.Lu.omw.db.MyDBHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * @author robin ������ṩ��
 */
public class GiftContentProvider extends ContentProvider{

	// gift ���ݿ������
	private MyDBHelper mDBHelper;
	// ���ݿ�
	private SQLiteDatabase db;
	
	// ְȨ������
	private static final String AUTHORITY = "cn.com.omw.gift";
	
	// ����һ��uri������(helpful)
	private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	private static final String GIFT_PATH = "gift";

	private static final String GIFT_ID = "gift/#";// # ����ƥ���κ�����

	private static final String GIFT_NAME = "gift/*";// * ����ƥ���κ��ַ�

	private static final int GIFT_PATH_CODE = 1;

	private static final int GIFT_ID_CODE = 2;

	private static final int GIFT_NAME_CODE = 3;
	
	// ����
	private static final String TABLE_NAME="gift";
	
	static {
		uriMatcher.addURI(AUTHORITY, GIFT_PATH, GIFT_PATH_CODE);
		uriMatcher.addURI(AUTHORITY, GIFT_ID, GIFT_ID_CODE);
		uriMatcher.addURI(AUTHORITY, GIFT_NAME, GIFT_NAME_CODE);
	}
	@Override
	public boolean onCreate() {
		//��ʼ�����ݿ�
		mDBHelper = new MyDBHelper(getContext());
		db = mDBHelper.getWritableDatabase();
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		int code = uriMatcher.match(uri);
		Cursor cursor = null;
		String data;
		switch (code) {
		// ��ѯ���е�
		case GIFT_PATH_CODE:
			cursor = db.query(TABLE_NAME, projection, selection, selectionArgs,
					null, null, null, sortOrder);
			break;
		case GIFT_ID_CODE:
			data = uri.getLastPathSegment();
			cursor=db.query(TABLE_NAME,projection, "_id=?", new String[]{data},
					null, null, null, sortOrder);
			break;
		// ͨ������ ��ѯ
		case GIFT_NAME_CODE:
			data = uri.getLastPathSegment();// ��ȡ·�������һ����
			cursor=db.query(TABLE_NAME,projection, "name=?", new String[]{data}, 
					null, null, null, sortOrder);
			break;
		default:
			break;
		}
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long id = db.insert(TABLE_NAME, null, values);
		return ContentUris.withAppendedId(uri, id);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return db.delete(TABLE_NAME, selection, selectionArgs);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return db.update(TABLE_NAME, values, selection, selectionArgs);
	}

}
