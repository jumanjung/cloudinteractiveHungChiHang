package com.cloudinteractiveHungChiHang.webapi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RedirectError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cloudinteractiveHungChiHang.Interface.Callback_WebApi;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Common_WebApi {
    private static final String TAG = "Common_WebApi";
    public JSONObject dara;
    private String access_token;
    Context mContext;
    private Callback_WebApi myCallback;
    private String URL;
    protected String ApiName;
    private boolean authLimit;
    private RequestQueue mRequestQueue;
    private JsonObjectRequest mJsonObjectRequest;
    private StringRequest mStringRequest;
    private AlertDialog alertDialog;


    public Common_WebApi(Callback_WebApi event) {
        this.myCallback = event;
        this.mContext = (Context) event;
        //HttpsTrustManager.allowAllSSL();
    }
    public Common_WebApi(Callback_WebApi event,Context context) {
        this.myCallback = event;
        this.mContext = context;
        //HttpsTrustManager.allowAllSSL();
    }





    protected void CallWebApi(String Api, String url, String path, Map<String, String> para) {
        URL = url + path;
        this.ApiName = Api;
        System.out.println("para = " + para);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        alertDialog = builder.setMessage("載入中")
                .setCancelable(false)
                .create();
        alertDialog.show();
        volley_api(Api, para);
    }


    private void volley_api(final String ApiName, final Map<String, String> para) {
        Log.d(TAG, URL);

        mRequestQueue = Volley.newRequestQueue(mContext);
        mStringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("INFO", ApiName + " 成功取得:" + response);
                        try {
                            JSONArray retobj = new JSONArray(response);
                            //JSONArray jsonArray = new JSONArray();
                            //jsonArray.put(retobj);
                            alertDialog.dismiss();
                            myCallback.success(retobj, ApiName);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            JSONObject object = new JSONObject();
                            alertDialog.dismiss();
                            myCallback.fail(object, ApiName);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("INFO", ApiName + " 取得失敗:" + error.toString());

                        JSONObject object = new JSONObject();
                        try {
                            if (error instanceof TimeoutError) {
                                object.put("message", "連線時間逾時");
                            } else if (error instanceof NoConnectionError) {
                                object.put("message", "無法建立連線");
                            } else if (error instanceof AuthFailureError) {
                                object.put("message", "認證錯誤");
                            } else if (error instanceof ServerError) {
                                object.put("message", "伺服器錯誤");
                            } else if (error instanceof NetworkError) {
                                object.put("message", "網路錯誤");
                            } else if (error instanceof ParseError) {
                                object.put("message", "網頁解析錯誤");
                            } else if (error instanceof RedirectError) {
                                object.put("message", "轉址錯誤");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        myCallback.fail(object, ApiName);
                    }
                }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return para;
            }
        };
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mStringRequest);
    }



    public void restart() {
//        Intent intent = new Intent(activity, MainActivity.class);
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("report", "");

        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        //Following code will restart your application after 2 seconds
        AlarmManager mgr = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
//        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
//                pendingIntent);
        mgr.set(AlarmManager.RTC, 0, pendingIntent);

        //This will finish your activity manually
//        mContext.finish();

        //This will stop your application and take out from it.
//        System.exit(2);
        //BaseApplication.getInstance().exit();
    }
}


