package com.example.sukalyan.demoapp;


import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import org.json.JSONObject;

public class NetworkHelper {
    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    Call post(String url, JSONObject json, Callback callback) {

        //RequestBody body = RequestBody.create(JSON,json);
        RequestBody body=RequestBody.create(JSON, json.toString());
        //Log.e("MyTagGoesHere", String.valueOf(body));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public static class Message {

        private String nickname;
        private String message ;

        public  Message(){

        }
        public Message(String nickname, String message) {
            this.nickname = nickname;
            this.message = message;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
