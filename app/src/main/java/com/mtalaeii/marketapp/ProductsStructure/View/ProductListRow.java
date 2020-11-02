package com.mtalaeii.marketapp.ProductsStructure.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mtalaeii.marketapp.ProductsStructure.common.model.Product;
import com.mtalaeii.marketapp.ProductsStructure.common.adapters.ProductsAdapter;
import com.mtalaeii.marketapp.R;

import java.util.List;

public class ProductListRow extends RelativeLayout {
    private TextView btnShowAll;
    private RecyclerView productsRecyclerView;
    private TextView rowTitleTextView;
    private ProgressBar progressBar;
    public ProductListRow(Context context) {
        super(context);
        init();
    }

    public ProductListRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProductListRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ProductListRow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    public void attachProducts(List<Product> products, ProductsAdapter.ProductsViewHolder.OnProductItemClick onProductItemClick){
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        ProductsAdapter productsAdapter = new ProductsAdapter(products,onProductItemClick,R.layout.item_product_small);
        productsRecyclerView.setAdapter(productsAdapter);
        progressBar.setVisibility(GONE);

    }
    private void init(){
        View view =LayoutInflater.from(getContext()).inflate(R.layout.view_products_list_row,this,true);
        productsRecyclerView = view.findViewById(R.id.productListRow_rV);
        btnShowAll = view.findViewById(R.id.productListRow_showAllProductTV);
        rowTitleTextView = findViewById(R.id.productListRow_titleTV);
        progressBar=findViewById(R.id.productListRow_progressBar);

    }
    public void setOnShowAllButtonClickListener(OnClickListener onClickListener){
        btnShowAll.setOnClickListener(onClickListener);
    }
    public void setTextTitle(String text){
        rowTitleTextView.setText(text);
    }

}
