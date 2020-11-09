package com.mtalaeii.marketapp.ApiStructure;

import com.mtalaeii.marketapp.ProductsStructure.common.model.Comment;
import com.mtalaeii.marketapp.ProductsStructure.common.model.Product;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("product.json?key=78822a80")
    Call<List<Product>> getProducts();
    @GET("comment.json?key=78822a80")
    Call<List<Comment>> getComment();
}
