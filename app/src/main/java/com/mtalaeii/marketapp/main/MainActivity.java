package com.mtalaeii.marketapp.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mtalaeii.marketapp.ApiStructure.ApiService;
import com.mtalaeii.marketapp.ProductsStructure.ProductActivity;
import com.mtalaeii.marketapp.ProductsStructure.common.adapters.ProductsAdapter;
import com.mtalaeii.marketapp.ProductsStructure.common.model.Product;
import com.mtalaeii.marketapp.ProductsStructure.ProductListActivity;
import com.mtalaeii.marketapp.ProductsStructure.View.ProductListRow;
import com.mtalaeii.marketapp.R;

import java.util.ArrayList;
import java.util.List;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

public class MainActivity extends AppCompatActivity {
    private BannerSlider bannerSlider;
    private ProductListRow latestProductListRow;
    private ProductListRow popularProductListRow;
    private ImageView tShirt;
    private ImageView shoes;
    private ImageView watch;
    private ImageView glass;
    private ProductsAdapter.ProductsViewHolder.OnProductItemClick onProductItemClick =new ProductsAdapter.ProductsViewHolder.OnProductItemClick() {
        @Override
        public void onProductClick(Product product) {
            ProductActivity.start(MainActivity.this,product);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        ApiService apiService = new ApiService(this);
        apiService.getProduct(new Response.Listener<List<Product>>() {
            @Override
            public void onResponse(List<Product> response) {
                latestProductListRow.attachProducts(response,onProductItemClick);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        },Product.LATEST_PRODUCTS);
        apiService.getProduct(new Response.Listener<List<Product>>() {
            @Override
            public void onResponse(List<Product> response) {
                popularProductListRow.attachProducts(response,onProductItemClick);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                
            }
        },Product.POPULAR_PRODUCTS);
    }

    private void setupViews() {
        bannerSlider = findViewById(R.id.main_bannerSlider_slider);
        List<Banner> bannerList = new ArrayList<>();
        RemoteBanner banner1 = new RemoteBanner("https://assets.materialup.com/uploads/dcc07ea4-845a-463b-b5f0-4696574da5ed/preview.jpg");
        RemoteBanner banner2 = new RemoteBanner("https://assets.materialup.com/uploads/20ded50d-cc85-4e72-9ce3-452671cf7a6d/preview.jpg");
        RemoteBanner banner3 = new RemoteBanner("https://assets.materialup.com/uploads/76d63bbc-54a1-450a-a462-d90056be881b/preview.png");
        bannerList.add(banner1);
        bannerList.add(banner2);
        bannerList.add(banner3);
        bannerSlider.setBanners(bannerList);
        latestProductListRow = findViewById(R.id.productListRow_main_latestProducts);
        latestProductListRow.setTextTitle("Newest Products");
        latestProductListRow.setOnShowAllButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, ProductListActivity.class);
                startActivity(intent);
            }
        });
        tShirt = findViewById(R.id.main_tShirtIV);
        onFakeClick(tShirt);
        shoes = findViewById(R.id.main_shoesIV);
        onFakeClick(shoes);
        watch = findViewById(R.id.main_watchIV);
        onFakeClick(watch);
        glass = findViewById(R.id.main_glassIV);
        onFakeClick(glass);
        popularProductListRow = findViewById(R.id.productListRow_main_popularProducts);
        popularProductListRow.setTextTitle("Popular Products");
        popularProductListRow.setOnShowAllButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductListActivity.class);
                startActivity(intent);
            }
        });

    }
    public void onFakeClick(ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"No category found !!!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}