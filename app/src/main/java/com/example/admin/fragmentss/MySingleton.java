package com.example.admin.fragmentss;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static MySingleton instance;
    private static Context cntx;
    private RequestQueue requestQueue;

    private MySingleton(Context context){
        cntx=context;
        requestQueue=getRequestQueue();
    }

    public static synchronized MySingleton getInstance(Context context){
        if (instance==null){
            instance=new MySingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(cntx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

}
