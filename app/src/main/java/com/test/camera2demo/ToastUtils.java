package com.test.camera2demo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class ToastUtils {

    private static Toast mToast;

    /**
     * 子线程主线程都支持弹框提示
     * Context 为 application
     *
     * @param msg
     */
    public static void showToast(String msg) {
        //主线程
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            if (mToast == null) {
                mToast = Toast.makeText(App.getInstance().getApplicationContext(), "", Toast.LENGTH_SHORT);
            }
            mToast.setText(msg);
            mToast.show();
        } else {
            mainHandler.sendMessage(mainHandler.obtainMessage(999, msg));
        }

    }


    /**
     * 切换到主线程
     */
    private static Handler mainHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 999:
                    if (mToast == null) {
                        mToast = Toast.makeText(App.getInstance().getApplicationContext(), "", Toast.LENGTH_LONG);
                    }
                    mToast.setText((String) msg.obj);
                    mToast.show();
                    break;
            }
        }
    };
}
