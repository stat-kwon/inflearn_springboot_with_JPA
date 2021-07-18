package com.inflearn.springbootwithjpa.controller;

import com.inflearn.springbootwithjpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/new")
    public String createFrom(Model model) {
        model.addAttribute("memberFrom", new MemberForm());
        return "members/createMemberForm";
    }

//    @PostMapping("/member/new")
//    public String create(MemberForm form) {
//
//        Address address = new Address(form.getCity(), form.getName(), form.getZipcode());
//
//        Member member = new Member();
//        member.setName(form.getName());
//    }
}
