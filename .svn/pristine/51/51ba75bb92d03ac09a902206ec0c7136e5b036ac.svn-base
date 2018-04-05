package com.bqiong.usercenter.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hunsy on 2017/1/4.
 */
public class MyOkHttpClient {

    private static MyOkHttpClient mhc = null;
    private OkHttpClient client;

    private MyOkHttpClient() {
        client = new OkHttpClient.Builder()
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .connectTimeout(3, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(100, 30, TimeUnit.SECONDS))
                .build();
    }

    public static MyOkHttpClient getInstance() {
        if (mhc == null) {
            mhc = new MyOkHttpClient();
        }
        return mhc;
    }

    public void exec(Request req, Callback cb) {
        client.newCall(req).enqueue(cb);
    }

    public Response exec(Request req) throws IOException {
        return client.newCall(req).execute();
    }
}
