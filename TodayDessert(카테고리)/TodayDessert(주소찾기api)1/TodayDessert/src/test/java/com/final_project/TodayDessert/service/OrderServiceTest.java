package com.final_project.TodayDessert.service;

import com.final_project.TodayDessert.constant.ItemSellStatus;
import com.final_project.TodayDessert.constant.OrderStatus;
import com.final_project.TodayDessert.dto.OrderDto;
import com.final_project.TodayDessert.entity.Item;
import com.final_project.TodayDessert.entity.Member;
import com.final_project.TodayDessert.entity.Order;
import com.final_project.TodayDessert.entity.OrderItem;
import com.final_project.TodayDessert.repository.ItemRepository;
import com.final_project.TodayDessert.repository.MemberRepository;
import com.final_project.TodayDessert.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    public Item saveItem(){
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setStoreNm("스토어이름");
        return itemRepository.save(item);
    }

    public Member saveMember(){
        Member member = new Member();
        member.setMemberId("good");
        return memberRepository.save(member);
    }

//    @Test
//    @DisplayName("주문 테스트")
//    public void order(){
//        Item item = saveItem();
//        Member member = saveMember();
//
//        OrderDto orderDto = new OrderDto();
//        orderDto.setCount(10);
//        orderDto.setItemId(item.getId());
//
//        Long orderId = orderService.order(orderDto, member.getMemberId());
//
//        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
//
//        List<OrderItem> orderItems = order.getOrderItems();
//
//        int totalPrice = orderDto.getCount()*item.getPrice();
//
//        assertEquals(totalPrice, order.getTotalPrice());
//    }

    @Test
    @DisplayName("주문 취소 테스트")
    public void cancelOrder(){
        Item item = saveItem();
        Member member = saveMember();

        OrderDto orderDto = new OrderDto();
        orderDto.setCount(10);
        orderDto.setItemId(item.getId());
        Long orderId = orderService.order(orderDto, member.getMemberId());

        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        orderService.cancelOrder(orderId);

        assertEquals(OrderStatus.CANCEL, order.getOrderStatus());
        assertEquals(100, item.getStockNumber());
    }


}
