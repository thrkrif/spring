package hello.hello_spring.controller;



import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {


    // @Autowired private MemberService memberService; 필드 주입

//    @Autowired  setter 주입, 누군가 바꿀 수 있다.
//    public void setMemberService(MemberService memberService){
//        this.memberService = memberService;
//    }


    private final MemberService memberService;

    // 생성하는 시점에 의존관계 주입하고 이후에 바뀌지 않는다.
    // 생성자 주입이 요새 트렌드이다.
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String CreateForm(MemberForm memberForm){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
