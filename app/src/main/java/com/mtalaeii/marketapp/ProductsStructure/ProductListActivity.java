package com.mtalaeii.marketapp.ProductsStructure;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mtalaeii.marketapp.ApiStructure.ApiService2;
import com.mtalaeii.marketapp.ApiStructure.Details;
import com.mtalaeii.marketapp.ProductsStructure.common.adapters.ProductsAdapter;
import com.mtalaeii.marketapp.ProductsStructure.common.model.Product;
import com.mtalaeii.marketapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;

public class ProductListActivity extends AppCompatActivity implements ProductsAdapter.ProductsViewHolder.OnProductItemClick {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private List<Product> productList;
    private ProductsAdapter productsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        setupViews();
        getProductData();
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
        productList = new ArrayList<>();
    }

    @Override
    public void onProductClick(Product product) {
        ProductActivity.start(this,product);
    }

    public void getProductData(){
        try {
            ApiService2.getProductApi().getProducts().enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, retrofit2.Response<List<Product>> response) {
                    Details.L(response.body().size()+" Size ");
                    productList.clear();
                    productList.addAll(response.body());
                    productsAdapter = new ProductsAdapter(productList,ProductListActivity.this);
                    recyclerView.setAdapter(productsAdapter);
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Details.T(t.toString(), ProductListActivity.this);
                }
            });
        }catch (Exception e){
            Details.T(e.toString(), ProductListActivity.this);
        }

    }
}