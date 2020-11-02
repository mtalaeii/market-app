package com.mtalaeii.marketapp.ApiStructure;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mtalaeii.marketapp.ProductsStructure.common.model.Comment;
import com.mtalaeii.marketapp.ProductsStructure.common.model.Product;

import java.lang.reflect.Type;
import java.util.List;

public class ApiService<T> {
    private Context context;
    //private T result;
    private Gson gson = new Gson();
    private static final String TAG = "ApiService";
    private static final String BASE_URL = "https://my.api.mockaroo.com/";
    private static final String ENDPOINT_PRODUCTS = "product.json?key=78822a80";
    private static final String ENDPOINT_COMMENT = "comment.json?key=78822a80";

    public ApiService(Context context) {
        this.context = context;
    }

    public void getProduct(int page,int order,Response.Listener<List<Product>> listener,Response.ErrorListener errorListener){
        GsonRequest<List<Product>> gsonRequest = new GsonRequest<>(Request.Method.GET,BASE_URL+ENDPOINT_PRODUCTS,listener,errorListener,new TypeToken<List<Product>>(){}.getType());
        gsonRequest.setRetryPolicy(new DefaultRetryPolicy(2000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(gsonRequest);
    }

    public void getProduct(int page,Response.Listener<List<Product>> listener,Response.ErrorListener errorListener) {
        getProduct(page,Product.DEFAULT_SORT,listener,errorListener);
    }
    public void getProduct(Response.Listener<List<Product>> listener,Response.ErrorListener errorListener){
        getProduct(1,Product.DEFAULT_SORT,listener,errorListener);
    }
    public void getProduct(Response.Listener<List<Product>> listener,Response.ErrorListener errorListener,int order) {
        getProduct(1,order,listener,errorListener);
    }
    public void getComment(String productId,Response.Listener<List<Comment>> commentsListener,Response.ErrorListener commentErrorListener){
        GsonRequest<List<Comment>> gsonRequest = new GsonRequest<>(Request.Method.GET, BASE_URL + ENDPOINT_COMMENT, commentsListener, commentErrorListener, new TypeToken<List<Comment>>(){}.getType());
        gsonRequest.setRetryPolicy(new DefaultRetryPolicy(2000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(gsonRequest);
    }
}
