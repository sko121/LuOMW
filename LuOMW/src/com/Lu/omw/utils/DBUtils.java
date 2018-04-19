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
	 * 插入
	 * @param context
	 */
	public static void insert(Context context){
		//装载数据
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
		//装载数据到集合
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
	 * 查询所有
	 * @param context
	 * @return
	 */
	public static List<GiftBean> query(Context context) {
		List<GiftBean> gifts = new ArrayList<GiftBean>();
		//创建sqli帮助类
		MyDBHelper myDBHelper = new MyDBHelper(context);
		//获取可读数据库
		SQLiteDatabase db = myDBHelper.getReadableDatabase();
		//获取数据库的游标
		//This interface provides random read-write access to the result set returned
		// by a database query.
		Cursor cursor = db.query(TABLE_NAME, new String[]{"_id, name"}, null, 
				null, null,	null, null);
		//迭代游标
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
	 * @return 查询 所有的
	 */
	public static List<GiftBean> queryBySql(Context context) {
		// 声明GiftBean 的集合
		List<GiftBean> gifts = new ArrayList<GiftBean>();
		MyDBHelper mDBHelper = new MyDBHelper(context);
		// 获取可读数据库
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		// 获取数据库的游标
		Cursor cursor = db.rawQuery("select _id,name from gift", null);
		// 迭代游标
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
		// 获取可读数据库
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		// 获取数据库的游标
		Cursor cursor = db.rawQuery("select _id,name from gift", null);
		return cursor;
	}
	
	/**
	 * @param context
	 * @return Curosr.getCount()
	 */
	public static int getCurcorCount(Context context) {
		MyDBHelper mDBHelper = new MyDBHelper(context);
		// 获取可读数据库
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		// 获取数据库的游标
		Cursor cursor = db.rawQuery("select _id,name from gift", null);
		return cursor.getCount();
	}
	
	/**
	 * @param context
	 * @param start 开始位置
	 * @param offset 偏移数量
	 * @return 返回从start开始往后的offset条数据的List
	 */
	public static List<GiftBean> queryBySqlFromOffset(Context context,
			int start, int offset) {
		List<GiftBean> gifts = new ArrayList<GiftBean>();
		MyDBHelper myDBHelper = new MyDBHelper(context);
		SQLiteDatabase db = myDBHelper.getReadableDatabase();
		//获取数据库的游标
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
	 * 查询id
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
		// cursor 不为空并且 存在第一条
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
	 * @return 查询 通过Id 出现
	 */
	public static GiftBean queryByIdBySql(Context context, int id) {
		// 创建数据库的帮助类
		MyDBHelper mDBHelper = new MyDBHelper(context);
		// 获得一个可读数据库
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select _id,name from gift where _id=?",
				new String[] { String.valueOf(id) });
		// select _id,name from gift where _id=?;
		GiftBean giftBean = null;
		// cursor 不为空并且 存在第一条
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
	 * 删除所有的数据
	 */
	public static void deleteAllBySql(Context context) {
		MyDBHelper mDBHelper = new MyDBHelper(context);
		// 获得一个可读数据库
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		// db.delete(TABLE_NAME, null, null);
		db.execSQL("delete from gift");
		db.close();
	}
	
	/**
	 * 通过id删除某条记录
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
	 *            通过Id 删除某条记录
	 */
	public static void deleteByIdBySql(Context context, int id) {
		MyDBHelper mDBHelper = new MyDBHelper(context);
		// 获得一个可读数据库
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		// db.delete(TABLE_NAME, "_id=?", new String[] { String.valueOf(id) });
		db.execSQL("delete from gift where _id=?", new Object[] { id });
		// 关闭数据库
		db.close();
	}
	
	/**
	 * 通过id更新名字
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
		// 第二参数：whereClause the optional WHERE clause to apply when updating.
		// Passing null will update all rows.
		// 第三参数 ：whereArgs You may include ?s in the where clause, which
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
	 * 通过id 更新名字
	 */
	public static void updateByIdBySql(Context context, int id, String name) {
		MyDBHelper mDBHelper = new MyDBHelper(context);
		// 获得一个可读数据库
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		db.execSQL("update gift set name=? where _id=?", new Object[] { name,
				id });
		// update gift set name=? where _id=?
		db.close();
	}
}
