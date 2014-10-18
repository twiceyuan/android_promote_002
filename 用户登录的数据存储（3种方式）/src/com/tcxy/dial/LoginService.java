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
	 * �����û���Ϣ����ɼ�ס���빦��
	 * @param username �û���
	 * @param password ����
	 * @return
	 */
	public static boolean saveUser(Context context,String username,String password){
		
		
		//��һ�ַ�ʽ
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
		
		
		//�ڶ��ַ�ʽ
		/*String txt  = username+"%%"+password+"##";
		try {
			OutputStream out = context.openFileOutput("user2.txt",Context.MODE_PRIVATE);
			out.write(txt.getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;*/
		
		//�����ַ�ʽ
		
		SharedPreferences sp = context.getSharedPreferences("user.txt", Context.MODE_PRIVATE);
		
		Editor editor = sp.edit();
		
		editor.putString(username, password);
		
		editor.commit();
		
		return true;
		
	}
	
	
	/**
	 * ȡ�ü�¼������
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
