package com.springbase.member.command.application;

import com.springbase.member.command.domain.Member;

public class JoinMemberRequest {
    private String name;
    private String password;

    public Member getMember() {
        return new Member(name, password);
    }
}
