package com.final_project.TodayDessert.service;

import com.final_project.TodayDessert.entity.Member;
import com.final_project.TodayDessert.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {

        validateDuplicateMember(member);

        return memberRepository.save(member);

    }

    private void validateDuplicateMember(Member member) {

        Member findMember = memberRepository.findByMemberId(member.getMemberId());

        if (findMember != null) {

            throw new IllegalStateException("이미 가입한 회원입니다");

        }

    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        Member member = memberRepository.findByMemberId(memberId);

        if (member == null) {

            throw new UsernameNotFoundException(memberId);

        }

        return User.builder()
                .username(member.getMemberId())
                .password(member.getMemberPwd())
                .authorities(member.getAuthorities())
                .build();
    }
}
