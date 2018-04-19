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

	//�����������ݲ���
	@Test
	public void insert(){
		DicDBUtil.insert(getContext());
	}
	
	//���Բ�ѯ����
	@Test
	public void textQuery(){
		List<GiftBean> gifts = DBUtils.query(getContext());
		if(gifts != null){
			for(int i = 0; i < gifts.size(); i++){
				Log.d("test", gifts.get(i).toString());
			}
		}
	}
	
	//����ͨ��id��ѯĳ����¼
	@Test
	public void textQueryById(){
		GiftBean giftBean = DBUtils.queryById(getContext(), 1);
		Log.d("test", giftBean.toString());
	}
	
	//���Ը���
	@Test
	public void testUpdate(){
		DBUtils.updateById(getContext(), 1, "�±�");
	}
	
	//����ͨ��idɾ��ĳ����¼
	@Test
	public void testDeleteById(){
		int row = DBUtils.deleteById(getContext(), 1);
		Log.d("test", "row = " + row);
	}
	
	//����ɾ����������
	@Test
	public void testDeleteAll(){
		int rows = DBUtils.deleteAll(getContext());
		Log.d("test", "rows = " + rows);
	}
	
	//ƫ������ѯ
	//select * from table LIMIT 5,10; #���ص�6-15������ 
	//select * from table LIMIT 5; #����ǰ5�� 
	//select * from table LIMIT 0,5; #����ǰ5��
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
