package com.example.omw.test;

import java.util.List;

import org.junit.Test;

import android.database.Cursor;
import android.test.AndroidTestCase;
import android.util.Log;

import com.Lu.omw.bean.GiftBean;
import com.Lu.omw.utils.DBUtils;
import com.Lu.omw.utils.DicDBUtil;

public class DicDBUtilsTest extends AndroidTestCase{

	//插入所有数据测试
	@Test
	public void insert(){
		DicDBUtil.insert(getContext());
	}
	
	//测试查询所有
	@Test
	public void textQuery(){
		List<GiftBean> gifts = DBUtils.query(getContext());
		if(gifts != null){
			for(int i = 0; i < gifts.size(); i++){
				Log.d("test", gifts.get(i).toString());
			}
		}
	}
	
	//测试通过id查询某条记录
	@Test
	public void textQueryById(){
		GiftBean giftBean = DBUtils.queryById(getContext(), 1);
		Log.d("test", giftBean.toString());
	}
	
	//测试更新
	@Test
	public void testUpdate(){
		DBUtils.updateById(getContext(), 1, "月饼");
	}
	
	//测试通过id删除某条记录
	@Test
	public void testDeleteById(){
		int row = DBUtils.deleteById(getContext(), 1);
		Log.d("test", "row = " + row);
	}
	
	//测试删除所有数据
	@Test
	public void testDeleteAll(){
		int rows = DBUtils.deleteAll(getContext());
		Log.d("test", "rows = " + rows);
	}
	
	//偏移量查询
	//select * from table LIMIT 5,10; #返回第6-15行数据 
	//select * from table LIMIT 5; #返回前5行 
	//select * from table LIMIT 0,5; #返回前5行
	@Test
	public void testQueryByLimit(){
		List<GiftBean> gifts = DBUtils.queryBySqlFromOffset(getContext(), 0, 10);
		if(gifts != null){
			for(int i = 0; i < gifts.size(); i++){
				Log.d("test", gifts.get(i).toString());
			}
		}
	}
	
	@Test
	public void testQueryCurcorBySql(){
		Cursor cursor = DBUtils.queryCursorBySql(getContext());
		Log.d("test", "cursor.count:" + DBUtils.getCurcorCount(getContext()));
		if(cursor != null){
			while(cursor.moveToNext()){
				GiftBean g = new GiftBean();
				g.setGiftName(cursor.getString(cursor.getColumnIndex("name")));
				g.setId(cursor.getInt(cursor.getColumnIndex("_id")));
				Log.d("test", g.toString());
			}
		}
	}
}
