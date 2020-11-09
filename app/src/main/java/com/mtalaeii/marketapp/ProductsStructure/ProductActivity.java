package com.mtalaeii.marketapp.ProductsStructure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.mtalaeii.marketapp.ApiStructure.ApiService2;
import com.mtalaeii.marketapp.ApiStructure.Details;
import com.mtalaeii.marketapp.ProductsStructure.common.adapters.CommentAdapter;
import com.mtalaeii.marketapp.ProductsStructure.common.model.Comment;
import com.mtalaeii.marketapp.ProductsStructure.common.model.Product;
import com.mtalaeii.marketapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ProductActivity extends AppCompatActivity {

    public static String EXTRA_KEY_PRODUCT = "product";
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
    private List<Comment> commentList;
    private CommentAdapter commentAdapter;
    public static int addCounter = 0;
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
        getCommentsData();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductActivity.this.finish();
            }
        });
        addToCartButton.setOnClickListener(v -> {
            if(addCounter >= 0){
                addCounter++;
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
        commentList = new ArrayList<>();
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

    public void getCommentsData(){
        try {
            ApiService2.getCommentApi().getComment().enqueue(new Callback<List<Comment>>() {
                @Override
                public void onResponse(Call<List<Comment>> call, retrofit2.Response<List<Comment>> response) {
                    Details.L(response.body().size()+" Size ");
                    commentList.clear();
                    commentList.addAll(response.body());
                    commentAdapter = new CommentAdapter(commentList);
                    commentsRecyclerView.setAdapter(commentAdapter);
                    commentsProgressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<List<Comment>> call, Throwable t) {
                    Details.T(t.toString(),ProductActivity.this);
                }
            });
        }catch (Exception e){
                    Details.T(e.toString(),ProductActivity.this);
        }

    }
}