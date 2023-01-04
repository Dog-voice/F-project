package com.final_project.TodayDessert.dto;

import com.final_project.TodayDessert.constant.ItemCategory;
import com.final_project.TodayDessert.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {
    private String searchDateType;
    private ItemSellStatus searchSellStatus;
    private ItemCategory searchCategory;
    private String searchBy;
    private String searchQuery = "";
}
