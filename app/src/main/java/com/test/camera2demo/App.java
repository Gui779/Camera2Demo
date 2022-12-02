package com.test.camera2demo;

import android.app.Application;

public class App extends Application {

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    /**
     * 获取全局上下文实例
     */
    public static App getInstance() {
        return mInstance;
    }

}
