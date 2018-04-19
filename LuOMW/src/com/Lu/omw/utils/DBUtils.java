package com.Lu.omw.utils;

import java.util.ArrayList;
import java.util.List;

import com.Lu.omw.bean.GiftBean;
import com.Lu.omw.db.MyDBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.GetChars;
import android.util.Log;

public class DBUtils {
	
	public static final String TABLE_NAME = "gift";

	/**
	 * ����
	 * @param context
	 */
	public static void insert(Context context){
		//װ������
		List<GiftBean> gifts = FileUtils.readDataFromAssets(context, 
				GiftBean.class, "student.txt");
		
		Log.d("lulu", gifts.toString());
		//A helper class to manage database creation and version management.
		MyDBHelper myDBHelper = new MyDBHelper(context);
		//Create and/or open a database that will be used for reading and writing.
		SQLiteDatabase db = myDBHelper.getWritableDatabase();
		for(int i = 0; i < gifts.size(); i++){
			GiftBean giftBean = gifts.get(i);
			ContentValues contentValues = new ContentValues();  //HashMap
			contentValues.put("name", giftBean.getGiftName());
			db.insert(TABLE_NAME, null, contentValues);
		}
		db.close();
	}
	
	public static void insertBySql(Context context){
		//װ�����ݵ�����
		List<GiftBean> gifts = FileUtils.readDataFromAssets(context, 
				GiftBean.class, "student.txt");
		MyDBHelper myDBHelper = new MyDBHelper(context);
		SQLiteDatabase db = myDBHelper.getWritableDatabase();
		long start = SystemClock.currentThreadTimeMillis();
		for(int i = 0; i < gifts.size(); i++){
			GiftBean gift = gifts.get(i);
			db.execSQL("insert into gift(name) values(?)", 
					new String[]{gift.getGiftName()});
		}
		long end = SystemClock.currentThreadTimeMillis();
		Log.d("time : ", String.valueOf((end - start)/1000));
		db.close();
	}
	
	/**
	 * ��ѯ����
	 * @param context
	 * @return
	 */
	public static List<GiftBean> query(Context context) {
		List<GiftBean> gifts = new ArrayList<GiftBean>();
		//����sqli������
		MyDBHelper myDBHelper = new MyDBHelper(context);
		//��ȡ�ɶ����ݿ�
		SQLiteDatabase db = myDBHelper.getReadableDatabase();
		//��ȡ���ݿ���α�
		//This interface provides random read-write access to the result set returned
		// by a database query.
		Cursor cursor = db.query(TABLE_NAME, new String[]{"_id, name"}, null, 
				null, null,	null, null);
		//�����α�
		if(cursor != null){
			boolean firstCursor = cursor.moveToFirst();
			if(!firstCursor){
				return  null;
			}
			for(int i = 0; i < cursor.getCount(); i++){
				GiftBean giftBean = new GiftBean();
				String giftName = cursor.getString(cursor.getColumnIndex("name"));
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				giftBean.setGiftName(giftName);
				giftBean.setId(id);
				gifts.add(giftBean);
				cursor.moveToNext();
			}
			cursor.close();
		}
		db.close();
		return gifts;
	}
	
	/**
	 * @param context
	 * @return ��ѯ ���е�
	 */
	public static List<GiftBean> queryBySql(Context context) {
		// ����GiftBean �ļ���
		List<GiftBean> gifts = new ArrayList<GiftBean>();
		MyDBHelper mDBHelper = new MyDBHelper(context);
		// ��ȡ�ɶ����ݿ�
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		// ��ȡ���ݿ���α�
		Cursor cursor = db.rawQuery("select _id,name from gift", null);
		// �����α�
		if (cursor != null) {
			while (cursor.moveToNext()) {
				GiftBean giftBean = new GiftBean();
				String giftName = cursor.getString(cursor
						.getColumnIndex("name"));
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				giftBean.setGiftName(giftName);
				giftBean.setId(id);
				gifts.add(giftBean);
			}
		}
		db.close();
		return gifts;
	}

	/**
	 * @param context
	 * @return Curosr
	 */
	public static Cursor queryCursorBySql(Context context) {
		MyDBHelper mDBHelper = new MyDBHelper(context);
		// ��ȡ�ɶ����ݿ�
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		// ��ȡ���ݿ���α�
		Cursor cursor = db.rawQuery("select _id,name from gift", null);
		return cursor;
	}
	
	/**
	 * @param context
	 * @return Curosr.getCount()
	 */
	public static int getCurcorCount(Context context) {
		MyDBHelper mDBHelper = new MyDBHelper(context);
		// ��ȡ�ɶ����ݿ�
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		// ��ȡ���ݿ���α�
		Cursor cursor = db.rawQuery("select _id,name from gift", null);
		return cursor.getCount();
	}
	
	/**
	 * @param context
	 * @param start ��ʼλ��
	 * @param offset ƫ������
	 * @return ���ش�start��ʼ�����offset�����ݵ�List
	 */
	public static List<GiftBean> queryBySqlFromOffset(Context context,
			int start, int offset) {
		List<GiftBean> gifts = new ArrayList<GiftBean>();
		MyDBHelper myDBHelper = new MyDBHelper(context);
		SQLiteDatabase db = myDBHelper.getReadableDatabase();
		//��ȡ���ݿ���α�
		Cursor cursor = db.rawQuery("select _id,name from gift limit ?,?",
				new String[]{String.valueOf(start), String.valueOf(offset)});
		if(cursor != null && cursor.moveToFirst()){
			while(cursor.moveToNext()){
				GiftBean gift = new GiftBean();
				gift.setGiftName(cursor.getString(cursor.getColumnIndex("name")));
				gift.setId(cursor.getInt(cursor.getColumnIndex("_id")));
				gifts.add(gift);
			}
		}
		db.close();
		return gifts;
	}
	
	/**
	 * ��ѯid
	 * @param context
	 * @param id
	 * @return
	 */
	public static GiftBean queryById(Context context, int id){
		MyDBHelper myDBHelper = new MyDBHelper(context);
		SQLiteDatabase db = myDBHelper.getReadableDatabase();
		// select _id,name from gift where _id=?;
		Cursor cursor = db.query(TABLE_NAME, new String[]{"_id", "name"}, "_id=?",
				new String[]{String.valueOf(id)}, null, null, null);
		GiftBean giftBean = null;
		// cursor ��Ϊ�ղ��� ���ڵ�һ��
		if(cursor != null && cursor.moveToFirst()){
			giftBean = new GiftBean();
			giftBean.setGiftName(cursor.getString(cursor.getColumnIndex("name")));
			giftBean.setId(id);
		}
		db.close();
		return giftBean;
	}
	
	/**
	 * @param context
	 * @return ��ѯ ͨ��Id ����
	 */
	public static GiftBean queryByIdBySql(Context context, int id) {
		// �������ݿ�İ�����
		MyDBHelper mDBHelper = new MyDBHelper(context);
		// ���һ���ɶ����ݿ�
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select _id,name from gift where _id=?",
				new String[] { String.valueOf(id) });
		// select _id,name from gift where _id=?;
		GiftBean giftBean = null;
		// cursor ��Ϊ�ղ��� ���ڵ�һ��
		if (cursor != null && cursor.moveToFirst()) {
			giftBean = new GiftBean();
			String name = cursor.getString(cursor.getColumnIndex("name"));
			giftBean.setGiftName(name);
			giftBean.setId(id);
		}
		db.close();
		return giftBean;
	}
	
	/**
	 * @param context
	 * @return the number of rows affected if a whereClause is passed in,
	 * 		   0 otherwise. To remove all rows and get a count pass "1" 
	 * 		   as the whereClause.
	 */
	public static int deleteAll(Context context){
		MyDBHelper myDBHelper = new MyDBHelper(context);
		SQLiteDatabase db = myDBHelper.getWritableDatabase();
		int row = db.delete(TABLE_NAME, null, null);
		db.close();
		return row;
	}
	
	/**
	 * @param context
	 * ɾ�����е�����
	 */
	public static void deleteAllBySql(Context context) {
		MyDBHelper mDBHelper = new MyDBHelper(context);
		// ���һ���ɶ����ݿ�
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		// db.delete(TABLE_NAME, null, null);
		db.execSQL("delete from gift");
		db.close();
	}
	
	/**
	 * ͨ��idɾ��ĳ����¼
	 * @param context
	 * @param id
	 * @return
	 */
	public static int deleteById(Context context, int id){
		MyDBHelper myDBHelper = new MyDBHelper(context);
		SQLiteDatabase db = myDBHelper.getWritableDatabase();
		int row = db.delete(TABLE_NAME, "_id=?", new String[]{String.valueOf(id)});
		db.close();
		return row;
	}
	
	/**
	 * @param context
	 * @param id
	 *            ͨ��Id ɾ��ĳ����¼
	 */
	public static void deleteByIdBySql(Context context, int id) {
		MyDBHelper mDBHelper = new MyDBHelper(context);
		// ���һ���ɶ����ݿ�
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		// db.delete(TABLE_NAME, "_id=?", new String[] { String.valueOf(id) });
		db.execSQL("delete from gift where _id=?", new Object[] { id });
		// �ر����ݿ�
		db.close();
	}
	
	/**
	 * ͨ��id��������
	 * @param context
	 * @param id
	 * @param name
	 * @return
	 */
	public static int updateById(Context context, int id, String name){
		MyDBHelper myDBHelper = new MyDBHelper(context);
		SQLiteDatabase db = myDBHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", name);
		// �ڶ�������whereClause the optional WHERE clause to apply when updating.
		// Passing null will update all rows.
		// �������� ��whereArgs You may include ?s in the where clause, which
		// will be replaced by the values from whereArgs. The values
		// will be bound as Strings.
		int row = db.update(TABLE_NAME, contentValues, "_id=?", new String[]{String.valueOf(id)});
		db.close();
		return row;
	}
	
	/**
	 * @param context
	 * @param id
	 * @param name
	 * ͨ��id ��������
	 */
	public static void updateByIdBySql(Context context, int id, String name) {
		MyDBHelper mDBHelper = new MyDBHelper(context);
		// ���һ���ɶ����ݿ�
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		db.execSQL("update gift set name=? where _id=?", new Object[] { name,
				id });
		// update gift set name=? where _id=?
		db.close();
	}
}
