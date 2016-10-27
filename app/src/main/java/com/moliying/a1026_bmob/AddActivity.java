package com.moliying.a1026_bmob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

import bean.Note;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddActivity extends AppCompatActivity {

    private EditText et_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        et_info = (EditText) findViewById(R.id.editText_info);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK ){
            Note note = new Note();
            note.setValue("text",et_info.getText().toString());
            note.save(new SaveListener<String>() {
                @Override
                public void done(String objectId,BmobException e) {
                    if(e==null){
                        toast("添加数据成功，返回objectId为："+objectId);
                    }else{
                        toast("创建数据失败：" + e.getMessage());
                    }
                }
            });
        }
        return super.onKeyDown(keyCode, event);
    }

    public void toast(String str){
        Toast.makeText(AddActivity.this, str, Toast.LENGTH_SHORT).show();
    }
}
