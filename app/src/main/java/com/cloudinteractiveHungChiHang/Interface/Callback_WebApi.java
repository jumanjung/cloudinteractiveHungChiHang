package com.cloudinteractiveHungChiHang.Interface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface Callback_WebApi {
    void success(JSONArray para, String ApiName) throws JSONException;

    void fail(JSONObject para, String ApiName);
}
