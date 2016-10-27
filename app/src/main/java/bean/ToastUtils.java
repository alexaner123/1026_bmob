package bean;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 16-10-26.
 */
public class ToastUtils {
    public static void toast(Context context, String str){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
