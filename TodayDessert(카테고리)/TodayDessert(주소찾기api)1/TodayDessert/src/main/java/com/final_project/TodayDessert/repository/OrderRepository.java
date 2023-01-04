package com.final_project.TodayDessert.repository;

import com.final_project.TodayDessert.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o "+"where o.member.memberId = :memberId "+"order by o.orderDate desc")
    List<Order> findOrders(@Param("memberId") String memberId, Pageable pageable);

    @Query("select count(o) from Order o "+"where o.member.memberId = :memberId")
    Long countOrder(@Param("memberId") String memberId);

}
