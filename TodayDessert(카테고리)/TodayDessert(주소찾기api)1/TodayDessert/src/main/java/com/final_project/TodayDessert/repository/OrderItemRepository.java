package com.final_project.TodayDessert.repository;

import com.final_project.TodayDessert.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
