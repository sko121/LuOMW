package com.Lu.omw.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;  
import java.util.Map;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * @author Administrator
 * 操作联系人的工具类
 */
public class ContactHelperUtil {

	private static final String BASE_URI = "content://" + ContactsContract.AUTHORITY;
	
	private static final String RAW_CONTACT_URI = BASE_URI + "/raw_contacts";
	
	private static final String PHOME_URI = BASE_URI + "/data/phones";
	
	/**
	 * 查询联系人
	 * @param contentResolver
	 * @return
	 */
	public static List<Map<String, Object>> queryContact(ContentResolver contentResolver){
		List<Map<String, Object>> contacts = new ArrayList<Map<String,Object>>();
		Cursor cursor = contentResolver.query(Uri.parse(RAW_CONTACT_URI),
				new String[]{"_id", "display_name"}, null, null, null);
		Map<String, Object> map = null;
		if(cursor != null){
			while(cursor.moveToNext()){
				int contactId = cursor.getInt(cursor.getColumnIndex("_id"));
				String contactName = cursor.getString(cursor.getColumnIndex("display_name"));
				map = new HashMap<String, Object>();
				map.put("contactid", contactId);
				map.put("contactName", contactName);
				Cursor c = contentResolver.query(Uri.parse(PHOME_URI),
						new String[]{"_id", "data1"}, 
						"raw_contact_id=? and mimetype=?", 
						new String[]{String.valueOf(contactId), String.valueOf(5)},
						null);
				if(c != null){
					while(c.moveToNext()){
						String phone = c.getString(c.getColumnIndex("data1"));
						map.put("phone", phone);
					}
				}
				contacts.add(map);
			}
		}
		return contacts;
	}
	
	/**
	 * 删除联系人
	 * @param resolver
	 * @param displayName
	 * @return
	 */
	public static boolean deleteContact(ContentResolver resolver, String displayName){
		int data = resolver.delete(Uri.parse(RAW_CONTACT_URI),
				"display_name=?", new String[]{displayName});
		if(data > 0){ //删除成功
			return true;
		}
		return false;
	}
}
