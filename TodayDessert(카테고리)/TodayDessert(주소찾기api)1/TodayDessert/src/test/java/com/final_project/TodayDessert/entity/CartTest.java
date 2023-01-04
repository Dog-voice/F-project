package com.final_project.TodayDessert.entity;

import com.final_project.TodayDessert.dto.MemberRegisterFormDto;
import com.final_project.TodayDessert.repository.CartRepository;
import com.final_project.TodayDessert.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class CartTest {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    public Member createMember() {
        MemberRegisterFormDto memberRegisterFormDto = new MemberRegisterFormDto();
        memberRegisterFormDto.setMemberId("member");
        memberRegisterFormDto.setMemberPwd("member1234");
        memberRegisterFormDto.setMemberName("멤버");
        memberRegisterFormDto.setMemberEmail("member@member.com");
        memberRegisterFormDto.setMemberZipCode("11111");
        memberRegisterFormDto.setMemberAddress("인천광역시");
        memberRegisterFormDto.setMemberPhone("010-0000-0000");
        return Member.createMember(memberRegisterFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("장바구니 회원 엔티티 매핑 테스트")
    public void findCartAndMemberTest() {
        Member member = createMember();
        memberRepository.save(member);

        Cart cart = new Cart();
        cart.setMember(member);
        cartRepository.save(cart);

        em.flush();
        em.clear();
        Cart savedCart = cartRepository.findById(cart.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(savedCart.getMember().getMemberNum(), member.getMemberNum());
    }
}
