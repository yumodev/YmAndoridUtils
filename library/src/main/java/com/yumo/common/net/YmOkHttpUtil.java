package com.yumo.common.net;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yumodev on 2/7/16.
 * 网络工具类，主要通过OkHttp实现
 */
public class YmOkHttpUtil {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    private static String getBodyString(Request request, OkHttpClient client) throws IOException{
        Response response = client.newCall(request).execute();
        if(response.isSuccessful()) {
            return response.body().string();
        }
        return null;
    }

    /**
     * 通过一个URL获取返回的字符串。
     * @param url
     * @return
     * @throws IOException
     */
    public static String getBodyString(String url) throws IOException{
        Request request = new Request.Builder().url(url).build();
        return getBodyString(request, getOkHttpClient());
    }



    /**
     * 获取返回的body输入流
     * @param url
     * @return
     */
    public static InputStream getBodyInputStream(String url) throws IOException{
        OkHttpClient client = getOkHttpClient();
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            return response.body().byteStream();
        }

        return null;
    }

    public static String getBodyString(String url, Map<String, String> heads) throws IOException{
        Request.Builder builder = new Request.Builder().url(url);
        appendHeaders(builder, heads);
        Request request = builder.build();

        OkHttpClient client = getOkHttpClient();
        return getBodyString(request, client);
    }

    public static void appendHeaders(Request.Builder builder, Map<String, String> heads){
        if (heads == null){
            return;
        }

        Iterator<String> iterator = heads.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            builder.addHeader(key, heads.get(key));
        }
    }

    private static OkHttpClient getOkHttpClient(){
        return new OkHttpClient();
    }

    /**
     * Post请求，不需要添加Heads
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static String postString(String url, String json) throws IOException {
        OkHttpClient client = getOkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * post请求 添加Headers
     * @param url
     * @param body
     * @param headers
     * @return
     * @throws IOException
     */
    public static String postString(String url, String body, Map<String, String> headers) throws IOException {
        OkHttpClient client = getOkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, body);

        Request.Builder builder = new Request.Builder().url(url);
        appendHeaders(builder, headers);

        Request request = builder.post(requestBody).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
