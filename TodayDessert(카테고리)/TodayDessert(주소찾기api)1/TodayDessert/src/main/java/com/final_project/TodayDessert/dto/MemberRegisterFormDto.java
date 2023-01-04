package com.final_project.TodayDessert.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberRegisterFormDto {
    
    @NotBlank(message = "아이디는 필수 입력 값입니다")
    private String memberId;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다")
    private String memberPwd;

    @NotEmpty(message = "이름은 필수 입력 값입니다")
    private String memberName;

    @NotEmpty(message = "이메일은 필수 입력 값입니다")
    @Email(message = "이메일 형식으로 입력하세요")
    private String memberEmail;

    @NotEmpty(message = "우편번호는 필수 입력 값입니다")
    private String memberZipCode;

    @NotEmpty(message = "상세주소는 필수 입력 값입니다")
    private String memberAddress;

    @NotEmpty(message = "전화번호는 필수 입력 값입니다")
    private String memberPhone;

    private String role;
}
