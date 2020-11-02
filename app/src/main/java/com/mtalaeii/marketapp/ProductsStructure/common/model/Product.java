
package com.mtalaeii.marketapp.ProductsStructure.common.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Product implements Serializable {

    @SerializedName("currentPrice")
    private int CurrentPrice;
    @SerializedName("id")
    private Long Id;
    @SerializedName("imageUrl")
    private String ImageUrl;
    @SerializedName("previousPrice")
    private int PreviousPrice;
    @SerializedName("status")
    private Long Status;
    @SerializedName("title")
    private String Title;
    public static final int STATUS_EXIST = 1;
    public static final int LATEST_PRODUCTS = 1;
    public static final int POPULAR_PRODUCTS = 2;
    public static final int DEFAULT_SORT = LATEST_PRODUCTS;



    public Integer getCurrentPrice() {
        return CurrentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        CurrentPrice = currentPrice;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getImageUrl() {
        return ImageUrl;

    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public SpannableString getPreviousPrice() {
        if(Status == STATUS_EXIST){
            String SPANNED_PRICE = String.valueOf(PreviousPrice+"") + "$";
            SpannableString spannableString = new SpannableString(SPANNED_PRICE);
            spannableString.setSpan(new StrikethroughSpan(),0,SPANNED_PRICE.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        }
        return new SpannableString("Not exist");
    }

    public void setPreviousPrice(int previousPrice) {
        PreviousPrice = previousPrice;
    }

    public Long getStatus() {
        return Status;
    }

    public void setStatus(Long status) {
        Status = status;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

}
