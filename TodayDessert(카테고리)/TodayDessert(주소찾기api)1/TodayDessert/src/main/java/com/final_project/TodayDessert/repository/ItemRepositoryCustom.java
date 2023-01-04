package com.final_project.TodayDessert.repository;

import com.final_project.TodayDessert.dto.ItemSearchDto;
import com.final_project.TodayDessert.dto.MainItemDto;
import com.final_project.TodayDessert.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    Page<Item> getStoreItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
    /*
    * 2022-11-27
    * 카테고리 별로 페이지 만듦
    * */
    Page<MainItemDto> getButterbarPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getCannelePage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getCookiesPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getFinancierPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMacaronPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getSconePage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getVeganPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
