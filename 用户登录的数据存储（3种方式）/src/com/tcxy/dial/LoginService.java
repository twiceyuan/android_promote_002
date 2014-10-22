package com.tcxy.dial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoginService {
	
	
	/**
	 * 保存用户信息，完成记住密码功能
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public static boolean saveUser(Context context,String username,String password){
		
		
		//第一种方式
		/*//File file = new File("/data/data/com.tcxy.dial/user.txt");
		
		File file = new File(context.getFilesDir(),"user.txt");
		
		String txt  = username+"%%"+password+"##";
		try {
			OutputStream out = new FileOutputStream(file);
			out.write(txt.getBytes());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;*/
		
		
		//第二种方式
		/*String txt  = username+"%%"+password+"##";
		try {
			OutputStream out = context.openFileOutput("user2.txt",Context.MODE_PRIVATE);
			out.write(txt.getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;*/
		
		//第三种方式
		
		SharedPreferences sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
		
		Editor editor = sp.edit();
		
		editor.putString(username, password);
		
		editor.commit();
		
		return true;
		
	}
	
	
	/**
	 * 取得记录的密码
	 * 
	 * @return
	 */
	public static Map<String,String> getUser(Context context){
		
		Map<String,String> map = new HashMap<String, String>();
		
		try {
			//File file = new File("/data/data/com.tcxy.dial/user.txt");
			
			File file = new File(context.getFilesDir(),"user.txt");
			
			if(!file.exists())
				return null;
			
			BufferedReader bf = new BufferedReader(new FileReader(file));
			
			String txt = bf.readLine();
			
			bf.close();
			
			if(txt==null||txt.equals("")){
				return null;
			}
			
			String[] user = txt.split("##");
			
			for (String u : user) {
				
				String username = u.split("%%")[0];
				String password = u.split("%%")[1];
				map.put(username, password);
			}
			return map;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
