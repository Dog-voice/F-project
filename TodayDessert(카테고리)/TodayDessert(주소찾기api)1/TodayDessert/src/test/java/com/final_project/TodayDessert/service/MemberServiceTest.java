package com.final_project.TodayDessert.service;

import com.final_project.TodayDessert.dto.MemberRegisterFormDto;
import com.final_project.TodayDessert.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember() {

        MemberRegisterFormDto memberRegisterFormDto = new MemberRegisterFormDto();

        memberRegisterFormDto.setMemberId("member");
        memberRegisterFormDto.setMemberPwd("member123");
        memberRegisterFormDto.setMemberName("멤버");
        memberRegisterFormDto.setMemberEmail("member@member.com");
        memberRegisterFormDto.setMemberZipCode("11111");
        memberRegisterFormDto.setMemberAddress("인천광역시");
        memberRegisterFormDto.setMemberPhone("010-0000-0000");

        return Member.createMember(memberRegisterFormDto, passwordEncoder);

    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest() {

        Member member = createMember();
        Member savedMember = memberService.saveMember(member);

        assertEquals(member.getMemberId(), savedMember.getMemberId());
        assertEquals(member.getMemberPwd(), savedMember.getMemberPwd());
        assertEquals(member.getMemberName(), savedMember.getMemberName());
        assertEquals(member.getMemberEmail(), savedMember.getMemberEmail());
        assertEquals(member.getMemberZipCode(), savedMember.getMemberZipCode());
        assertEquals(member.getMemberAddress(), savedMember.getMemberAddress());
        assertEquals(member.getMemberPhone(), savedMember.getMemberPhone());
        assertEquals(member.getRole(), savedMember.getRole());

    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateMemberTest() {

        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);

        Throwable e = assertThrows(IllegalStateException.class, () -> {

            memberService.saveMember(member2);

        });

        assertEquals("이미 가입한 회원입니다", e.getMessage());

    }

}
