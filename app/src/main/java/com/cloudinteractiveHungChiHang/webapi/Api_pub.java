package com.cloudinteractiveHungChiHang.webapi;


import android.content.Context;


import com.cloudinteractiveHungChiHang.Interface.Callback_WebApi;

import java.util.Map;

public class Api_pub extends Common_WebApi {
    public Api_pub(Callback_WebApi event) {

        super(event);
    }
    public Api_pub(Callback_WebApi event, Context context) {

        super(event,context);
    }

    public void webapiCall(String url, Map<String, String> para) {
        String path = "photos";
        CallWebApi("webapiCall", url, path, para);
    }



}