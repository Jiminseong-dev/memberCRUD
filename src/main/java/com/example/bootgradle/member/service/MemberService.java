package com.example.bootgradle.member.service;

import com.example.bootgradle.member.controller.dto.MemberDto;
import com.example.bootgradle.member.repository.entity.MemberEntity;

import java.util.List;

public interface MemberService {
    int create(MemberDto memberDto);
    List<MemberDto> findAll();
    MemberDto getMemberInfo(Long seq);
    int modifyMemberInfo(MemberDto memberDto);
    int memberDelete(Long seq);
}
