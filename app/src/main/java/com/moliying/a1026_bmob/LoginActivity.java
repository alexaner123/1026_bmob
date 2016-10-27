package com.moliying.a1026_bmob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_name = (EditText) findViewById(R.id.editText_name);
        et_password = (EditText) findViewById(R.id.editText_password);
    }

    public void loginClick(View view){
        String name = et_name.getText().toString();
        String password = et_password.getText().toString();

        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User bmobUser, BmobException e) {
                if(e==null){
                    toast("登录成功:");
                    //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                    Intent intent = new Intent(LoginActivity.this,NoteActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }else{
                    toast(e.getMessage());
                }
            }
        });
    }

    private void toast(String str){
        Toast.makeText(LoginActivity.this, str, Toast.LENGTH_SHORT).show();
    }
}
