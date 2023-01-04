package com.final_project.TodayDessert.repository;

import com.final_project.TodayDessert.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberId(String memberId);

}
