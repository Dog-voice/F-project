package com.final_project.TodayDessert.controller;

import com.final_project.TodayDessert.dto.MemberRegisterFormDto;
import com.final_project.TodayDessert.entity.Member;
import com.final_project.TodayDessert.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(value = "/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberRegisterForm(Model model) {

        model.addAttribute("memberRegisterForm", new MemberRegisterFormDto());

        return "member/memberRegisterForm";
    }

    @PostMapping(value = "/new")
    public String newMember(@Valid MemberRegisterFormDto memberRegisterFormDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            return "member/memberRegisterForm";

        }

        try {
            Member member = Member.createMember(memberRegisterFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberRegisterForm";
        }

        return "/member/memberLoginForm";

    }

    @GetMapping(value = "/login")
    public String loginMember() {

        return "/member/memberLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {

        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인하세요");

        return "/member/memberLoginForm";
    }
}
