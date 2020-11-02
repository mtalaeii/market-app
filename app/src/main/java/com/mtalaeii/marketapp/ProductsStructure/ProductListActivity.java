package com.mtalaeii.marketapp.ProductsStructure;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mtalaeii.marketapp.ApiStructure.ApiService;
import com.mtalaeii.marketapp.ProductsStructure.common.adapters.ProductsAdapter;
import com.mtalaeii.marketapp.ProductsStructure.common.model.Product;
import com.mtalaeii.marketapp.R;

import java.util.List;

public class ProductListActivity extends AppCompatActivity implements ProductsAdapter.ProductsViewHolder.OnProductItemClick, Response.Listener<List<Product>>,Response.ErrorListener {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        setupViews();
        ApiService apiService = new ApiService<>(this);
        apiService.getProduct(ProductListActivity.this,ProductListActivity.this );
    }

    private void setupViews() {
        
        View backButton = findViewById(R.id.product_listIm_backArrow);
        progressBar = findViewById(R.id.product_list_progressBar);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView= findViewById(R.id.product_listRV);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2,RecyclerView.VERTICAL,false));

    }

    @Override
    public void onProductClick(Product product) {
        ProductActivity.start(this,product);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(ProductListActivity.this,"Error !!!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(List<Product> products) {
        ProductsAdapter productsAdapter = new ProductsAdapter(products, ProductListActivity.this);
        recyclerView.setAdapter(productsAdapter);
        progressBar.setVisibility(View.GONE);
    }
}