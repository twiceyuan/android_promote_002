package com.tcxy.dial;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity{
	
	
	private final static String TAG = "MainActivity";

	private EditText et_username;
	
	private EditText et_password;
	
	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login_combine);
		
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		
		//当用户名输入框失去焦点时，去填充密码
		et_username.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus){
					
					SharedPreferences sp = MainActivity.this.getSharedPreferences("user.txt", Context.MODE_PRIVATE);
					
					String p = sp.getString(et_username.getText().toString(), null);
					if(p!=null){
						et_password.setText(p);
					}
					
					/*Map<String,String> map = LoginService.getUser(MainActivity.this);
					if(map!=null){
						String p = map.get(et_username.getText().toString());
						if(p!=null){
							et_password.setText(p);
						}
					}*/
				}
			}
		});
		
	}
	
	public void login(View view){
		
		
		Log.i(TAG, et_username.toString());
		
		
		Log.i(TAG, et_username.getText().toString());
		
		String username = et_username.getText().toString().trim();
		
		String password = et_password.getText().toString().trim();
		
		if(username==null||username.equals("")||password==null||password.equals("")){
			
			Toast.makeText(this, "用户名或者密码为空", Toast.LENGTH_LONG).show();
			return;
		}
		
		LoginService.saveUser(this,username, password);
		
		if(username.equals("test")&&password.equals("123")){
			Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
		}
		Toast.makeText(this, "登录失败", Toast.LENGTH_LONG).show();
		
	}
	
}
