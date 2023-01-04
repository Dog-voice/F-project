package com.final_project.TodayDessert.entity;

import com.final_project.TodayDessert.dto.MemberRegisterFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity implements UserDetails {

    @Id
    @Column(name = "memberNum")
    @SequenceGenerator(name = "MEMBER_SEQUENCE_GEN", sequenceName = "member_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQUENCE_GEN")
    private Long memberNum;

    @Column(unique = true)
    private String memberId;

    private String memberPwd;

    private String memberName;

    private String memberEmail;

    private String memberZipCode;

    private String memberAddress;

    private String memberPhone;

    @CreationTimestamp
    private LocalDateTime memberRegDate;

    private String role;


    public static Member createMember(MemberRegisterFormDto memberRegisterFormDto, PasswordEncoder passwordEncoder) {

        Member member = new Member();

        member.setMemberId(memberRegisterFormDto.getMemberId());
        String memberPwd = passwordEncoder.encode(memberRegisterFormDto.getMemberPwd());
        member.setMemberPwd(memberPwd);
        member.setMemberName(memberRegisterFormDto.getMemberName());
        member.setMemberEmail(memberRegisterFormDto.getMemberEmail());
        member.setMemberZipCode(memberRegisterFormDto.getMemberZipCode());
        member.setMemberAddress(memberRegisterFormDto.getMemberAddress());
        member.setMemberPhone(memberRegisterFormDto.getMemberPhone());
        member.setRole(memberRegisterFormDto.getRole());

        return member;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : role.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
