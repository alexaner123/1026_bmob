package com.moliying.a1026_bmob;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bean.Note;
import bean.ToastUtils;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class NoteActivity extends AppCompatActivity  {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        listView = (ListView) findViewById(R.id.listView);
        //短按更新
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //更新Person表里面id为6b6c11c537的数据，address内容更新为“北京朝阳”
                final Note note = new Note();
                note.setText("高警官捉鸡");
                note.update("6b6c11c537", new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            ToastUtils.toast(NoteActivity.this,"更新成功:"+note.getUpdatedAt());
                        }else{
                            ToastUtils.toast(NoteActivity.this,"更新失败：" + e.getMessage());
                        }
                    }
                });
            }
        });

        //长按删除
        listView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return true;
            }
        });
    }

    public void addClick(View view){
        Intent intent = new Intent(this,AddActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        BmobQuery<Note> query = new BmobQuery<Note>();
    //返回20条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(20);
//执行查询方法
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> object, BmobException e) {
                if(e==null){
                    ToastUtils.toast(NoteActivity.this,"查询成功：共"+object.size()+"条数据。");
                    listView.setAdapter(new NoteAdapter(NoteActivity.this,object));

                }else{
                    ToastUtils.toast(NoteActivity.this,e.getMessage());
                }
            }
        });

    }

     class NoteAdapter extends BaseAdapter{

        Context context;
        List<Note> notes;

        public NoteAdapter(Context context, List<Note> notes) {
            this.context = context;
            this.notes = notes;
        }

        @Override
        public int getCount() {
            return notes.size();
        }

        @Override
        public Object getItem(int position) {
            return notes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if(convertView == null){
                vh = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.listview_item,null);
                vh.tv_item = (TextView) convertView.findViewById(R.id.textView_item);
                convertView.setTag(vh);
            }else{
                vh = (ViewHolder) convertView.getTag();
            }
            Note note = notes.get(position);
            vh.tv_item.setText(note.getText());
            return convertView;
        }

        class ViewHolder{
            TextView tv_item;
        }
    }



}
