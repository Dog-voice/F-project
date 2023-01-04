package com.final_project.TodayDessert.controller;

import com.final_project.TodayDessert.dto.MemberRegisterFormDto;
import com.final_project.TodayDessert.entity.Member;
import com.final_project.TodayDessert.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class MemberControllerTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(String memberId, String memberPwd) {

        MemberRegisterFormDto memberRegisterFormDto = new MemberRegisterFormDto();

        memberRegisterFormDto.setMemberId(memberId);
        memberRegisterFormDto.setMemberPwd(memberPwd);
        memberRegisterFormDto.setMemberName("멤버");
        memberRegisterFormDto.setMemberEmail("member@member.com");
        memberRegisterFormDto.setMemberZipCode("11111");
        memberRegisterFormDto.setMemberAddress("인천광역시");
        memberRegisterFormDto.setMemberPhone("010-0000-0000");

        Member member = Member.createMember(memberRegisterFormDto, passwordEncoder);

        return memberService.saveMember(member);

    }

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccessTest() throws Exception {

        String memberId = "member";
        String memberPwd = "member123";
        this.createMember(memberId, memberPwd);

        mockMvc.perform(formLogin().userParameter("memberId")
                .passwordParam("memberPwd")
                .loginProcessingUrl("/members/login")
                .user(memberId).password(memberPwd))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());

    }

}
