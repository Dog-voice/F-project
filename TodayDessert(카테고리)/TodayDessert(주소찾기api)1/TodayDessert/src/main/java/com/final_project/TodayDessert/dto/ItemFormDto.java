package com.final_project.TodayDessert.dto;

import com.final_project.TodayDessert.constant.ItemCategory;
import com.final_project.TodayDessert.constant.ItemSellStatus;
import com.final_project.TodayDessert.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemFormDto {
    private Long id;

    @NotBlank(message = "상호명은 필수 입력 값입니다")
    private String storeNm;

    @NotBlank(message = "상품명은 필수 입력 값입니다")
    private String itemNm;

    @NotNull(message = "가격은 필수 입력 값입니다")
    private Integer price;

    @NotBlank(message = "상세 설명은 필수 입력 값입니다")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;
    private ItemCategory itemCategory;
    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();
    private List<Long> itemImgIds = new ArrayList<>();
    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem() {
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item) {
        return modelMapper.map(item, ItemFormDto.class);
    }
}
