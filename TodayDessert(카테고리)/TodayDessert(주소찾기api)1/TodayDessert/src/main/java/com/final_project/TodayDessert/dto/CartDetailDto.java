package com.final_project.TodayDessert.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDetailDto {
    private Long cartItemId;
    private String storeNm;
    private String itemNm;
    private int price;
    private int count;
    private String imgUrl;

    public CartDetailDto(Long cartItemId, String storeNm, String itemNm, int price, int count, String imgUrl) {
        this.cartItemId = cartItemId;
        this.storeNm = storeNm;
        this.itemNm = itemNm;
        this.price = price;
        this.count = count;
        this.imgUrl = imgUrl;
    }
}
