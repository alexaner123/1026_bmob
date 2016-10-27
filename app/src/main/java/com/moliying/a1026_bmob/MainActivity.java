package com.moliying.a1026_bmob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //第一：默认初始化
        Bmob.initialize(this, "26811e6fcd098943524653a7a30ea702");
    }

    public void addClick(View view){
        Person p2 = new Person();
        p2.setName("lucky");
        p2.setAddress("北京海淀");
        p2.save(new SaveListener<String>() {
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

    public void deleteClick(View view){
        final Person p2 = new Person();
        p2.setObjectId("2ba1a81eaa");
        p2.delete(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    toast("删除成功:"+p2.getUpdatedAt());
                }else{
                    toast("删除失败：" + e.getMessage());
                }
            }
        });
    }

    public void updateClick(View view){
    //更新Person表里面id为6b6c11c537的数据，address内容更新为“北京朝阳”
        final Person p2 = new Person();
        p2.setAddress("北京朝阳");
        p2.setName("晓博");
        p2.update("2b2c70a7d0", new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    toast("更新成功:"+ p2.getUpdatedAt());
                }else{
                    toast("更新失败：" + e.getMessage());
                }
            }
        });
    }

    public void searchClick(View view){
    //查找Person表里面id为6b6c11c537的数据
        final BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.getObject("2b2c70a7d0", new QueryListener<Person>() {
            @Override
            public void done(Person object,BmobException e) {
                if(e==null){
                    toast("查询成功"+object.getAddress()+object.getName());
                }else{
                    toast("查询失败：" + e.getMessage());
                }
            }
        });
    }

    private void toast(String str){
        Toast.makeText(MainActivity.this,str , Toast.LENGTH_SHORT).show();
    }

}
