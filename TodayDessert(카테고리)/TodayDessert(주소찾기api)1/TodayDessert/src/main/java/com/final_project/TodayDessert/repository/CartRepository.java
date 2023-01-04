package com.final_project.TodayDessert.repository;

import com.final_project.TodayDessert.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMember_MemberNum(Long memberNum);
}
