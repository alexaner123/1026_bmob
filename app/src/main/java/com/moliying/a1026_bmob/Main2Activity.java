package com.moliying.a1026_bmob;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class Main2Activity extends AppCompatActivity {

    EditText et_name,et_password,et_icon,et_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //第一：默认初始化
        Bmob.initialize(this, "26811e6fcd098943524653a7a30ea702");
        et_name = (EditText) findViewById(R.id.editText_name);
        et_password = (EditText) findViewById(R.id.editText_password);
        et_icon = (EditText) findViewById(R.id.editText_icon);
        et_email = (EditText) findViewById(R.id.editText_email);
    }

    public void registerClick(View view){
        final String name = et_name.getText().toString();
        final String password = et_password.getText().toString();
        final String icon = et_icon.getText().toString();
        final String email = et_email.getText().toString();

        String picPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/"+icon;
        Log.i("", "registerClick: "+picPath);

        final BmobFile bmobFile = new BmobFile(new File(picPath));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    toast("上传文件成功:" + bmobFile.getFileUrl());
                    User user = new User();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.setEmail(email);
                    user.setIcon(bmobFile);
                    //注意：不能用save方法进行注册
                    user.signUp(new SaveListener<User>() {
                        @Override
                        public void done(User s, BmobException e) {
                            if(e==null){
                                toast("注册成功:" +s.toString());
                            }else{
                                toast(e.getMessage());
                            }
                        }
                    });
                }else{
                    toast("上传文件失败：" + e.getMessage());
                }
            }
            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }
        });
    }

    public void loginClick(View view){
       Intent intent = new Intent(this,LoginActivity.class);
       startActivity(intent);
        this.finish();
    }


    private void toast(String str){
        Toast.makeText(Main2Activity.this, str, Toast.LENGTH_SHORT).show();
    }

    public void onTestClick(View view){
        String picPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/1.jpg";

        final BmobFile bmobFile = new BmobFile(new File(picPath));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    toast("上传成功");
                }else{
                    toast("上传文件失败：" + e.getMessage());
                }
            }
            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }
        });
    }


}
