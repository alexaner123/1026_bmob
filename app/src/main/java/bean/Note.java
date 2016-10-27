package bean;

import android.content.Context;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 16-10-26.
 */
public class Note extends BmobObject{
    private Context context;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


}
