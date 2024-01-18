package com.example.bootgradle.member.controller;

import com.example.bootgradle.member.controller.dto.MemberDto;
import com.example.bootgradle.member.repository.entity.MemberEntity;
import com.example.bootgradle.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "MemberController", description = "회원 관련 API")
public class MemberController {

    private final MemberService memberService;
    @Operation(summary = "메인 페이지 이동", description = "사용자 목록이 조회된다.")
    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        List<MemberDto> memberDtoList = memberService.findAll();
        modelAndView.addObject("member",memberDtoList);
        return modelAndView;
    }
    @Operation(summary = "사용자 추가 페이지 이동", description = "사용자 추가 페이지 이동")
    @GetMapping("/member/createPage")
    public ModelAndView userInsertPage(){
        ModelAndView modelAndView = new ModelAndView("/member/memberCreate");
        return modelAndView;
    }

    @Operation(summary = "사용자 추가")
    @PostMapping("/member/create")
    public ModelAndView userInsert(MemberDto memberDto){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        log.info(memberDto.toString());
        int result = memberService.create(memberDto);
        log.info(result+"");
        return modelAndView;
    }
    @Operation(summary = "사용자 수정")
    @GetMapping("/member/modifyPage/{id}")
    public ModelAndView modify(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("/member/memberModify");
        log.info(id);
        MemberDto memberDto = MemberDto.builder().id(id).build();
        int result = memberService.modify(memberDto);
        return modelAndView;
    }
}
