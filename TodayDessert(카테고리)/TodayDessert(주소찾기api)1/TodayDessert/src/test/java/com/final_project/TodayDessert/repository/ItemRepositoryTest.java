package com.final_project.TodayDessert.repository;

import com.final_project.TodayDessert.constant.ItemCategory;
import com.final_project.TodayDessert.constant.ItemSellStatus;
import com.final_project.TodayDessert.entity.Item;
import com.final_project.TodayDessert.entity.QItem;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        Item item = new Item();
        item.setStoreNm("테스트스토어");
        item.setItemNm("테스트상품");
        item.setPrice(15000);
        item.setStockNumber(10);
        item.setItemDetail("테스트상품설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setItemCategory(ItemCategory.COOKIES);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }

    public void createItemList() {
        Item item = new Item();
    }

    @Test
    @DisplayName("카테고리 조회 테스트")
    public void queryDslTest() {
        this.createItemList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item;
        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemCategory.eq(ItemCategory.COOKIES));

        List<Item> itemList = query.fetch();

        System.out.println(itemList.toString());
    }
}
