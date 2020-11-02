package com.mtalaeii.marketapp.ApiStructure;

import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GsonRequest<T> extends Request<T> {
    private static final String TAG = "GsonRequest";
    private Response.Listener<T> responseListener;
    private Gson gson = new Gson();
    private Type type;
    public GsonRequest(int method, String url,Response.Listener<T> responseListener, @Nullable Response.ErrorListener listener,Type type) {
        super(method ,url, listener);
        this.responseListener = responseListener;
        this.type = type;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String responseString = new String(response.data,"UTF-8");
            T result = gson.fromJson(responseString,type);
            return Response.success(result,null);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "parseNetworkResponse: "+e.toString());
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        responseListener.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type","/product.json");
        return headers;
    }

}
