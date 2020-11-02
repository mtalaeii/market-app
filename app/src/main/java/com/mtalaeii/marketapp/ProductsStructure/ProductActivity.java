package com.mtalaeii.marketapp.ProductsStructure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.mtalaeii.marketapp.ApiStructure.ApiService;
import com.mtalaeii.marketapp.ProductsStructure.common.adapters.CommentAdapter;
import com.mtalaeii.marketapp.ProductsStructure.common.model.Comment;
import com.mtalaeii.marketapp.ProductsStructure.common.model.Product;
import com.mtalaeii.marketapp.R;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements Response.Listener<List<Comment>>,Response.ErrorListener {

    private static String EXTRA_KEY_PRODUCT = "product";
    private TextView productTitleTextView;
    private TextView productAddToCartTextView;
    private TextView productCurrentPriceTV;
    private TextView productPreviousPriceTv;
    private Button addToCartButton;
    private ImageView productImageView;
    private Product product = new Product();
    private RecyclerView commentsRecyclerView;
    private ProgressBar commentsProgressBar;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        product=(Product) getIntent().getSerializableExtra(EXTRA_KEY_PRODUCT);
        if(product == null){
            throw new IllegalStateException("Product is null");
        }
        setupViews();
        bindProducts();
        ApiService apiService = new ApiService<>(this);
        apiService.getComment("1",this,this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductActivity.this.finish();
            }
        });
    }
    private void setupViews() {
        productImageView=findViewById(R.id.product_detailImageIV);
        productTitleTextView = findViewById(R.id.product_detailTitleTV);
        addToCartButton = findViewById(R.id.product_detail_addToCartButton);
        productAddToCartTextView = findViewById(R.id.product_detail_addToCartTextView);
        commentsRecyclerView = findViewById(R.id.product_detail_commentsRV);
        productCurrentPriceTV = findViewById(R.id.product_detail_currentPriceTV);
        productPreviousPriceTv = findViewById(R.id.product_detail_previousPriceTV);
        commentsProgressBar=findViewById(R.id.product_detail_commentsProgressBar);
        back = findViewById(R.id.product_detail_backArrowButton);
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }
    private void bindProducts(){
        productTitleTextView.setText(product.getTitle());
        Glide.with(this).load(product.getImageUrl()).into(productImageView);
        if(product.getStatus() != product.STATUS_EXIST){
            addToCartButton.setVisibility(View.GONE);
            productAddToCartTextView.setVisibility(View.VISIBLE);
        }else{
            addToCartButton.setVisibility(View.VISIBLE);
            productAddToCartTextView.setVisibility(View.GONE);
            productPreviousPriceTv.setText(product.getPreviousPrice());
            productCurrentPriceTV.setText(product.getCurrentPrice().toString()+"$");
        }
    }
    public static void  start(Context context, Product product){
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra(EXTRA_KEY_PRODUCT,product);
        context.startActivity(intent);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(List<Comment> comments) {
        CommentAdapter commentAdapter = new CommentAdapter(comments);
        commentsRecyclerView.setAdapter(commentAdapter);
        commentsProgressBar.setVisibility(View.GONE);
    }

}