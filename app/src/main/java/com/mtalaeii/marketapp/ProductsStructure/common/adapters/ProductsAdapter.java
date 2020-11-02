package com.mtalaeii.marketapp.ProductsStructure.common.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mtalaeii.marketapp.ProductsStructure.common.model.Product;
import com.mtalaeii.marketapp.R;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {
    private List<Product> products;
    private ProductsViewHolder.OnProductItemClick onProductItemClick;
    private int resourceId = -1;
    public ProductsAdapter(List<Product> products, ProductsViewHolder.OnProductItemClick onProductItemClick){
        this.products = products;
        this.onProductItemClick = onProductItemClick;
    }
    public ProductsAdapter(List<Product> products,ProductsViewHolder.OnProductItemClick onProductItemClick,@LayoutRes int resourceId){
        this.onProductItemClick = onProductItemClick;
        this.resourceId= resourceId;
        this.products = products;
    }
    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(resourceId == -1 ? R.layout.item_product:resourceId,parent,false);
        return new ProductsViewHolder(view,onProductItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        holder.bindProduct(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductsViewHolder extends RecyclerView.ViewHolder{
        private ImageView productImageView;
        private TextView productTitleTextView;
        private TextView productPreviousPriceTV;
        private TextView productCurrentPriceTV;
        private OnProductItemClick onProductItemClick;
        public ProductsViewHolder(@NonNull View itemView,OnProductItemClick onProductItemClick) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.product_imageView);
            productTitleTextView = itemView.findViewById(R.id.product_titleTextView);
            productPreviousPriceTV = itemView.findViewById(R.id.product_previousPriceTextView);
            productCurrentPriceTV = itemView.findViewById(R.id.product_currentPriceTextView);
            this.onProductItemClick = onProductItemClick;
        }
        public void bindProduct(Product product){
            productTitleTextView.setText(product.getTitle());
            if (product.getStatus() == Product.STATUS_EXIST){
                productCurrentPriceTV.setText(product.getCurrentPrice().toString()+"$");
            }
            productPreviousPriceTV.setText(product.getPreviousPrice());
            Glide.with(itemView.getContext()).load(product.getImageUrl()).into(productImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onProductItemClick.onProductClick(product);
                }
            });
        }
        public interface OnProductItemClick{
            void onProductClick(Product product);
        }
    }
}




