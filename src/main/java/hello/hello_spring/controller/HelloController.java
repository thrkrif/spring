package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "spring!!!!!!!!");
        return "hello";
    }
    // mvc 방식
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 방식
    // 위의 mvc 방식과 차이 없어보이지만 페이지 소스를 보면 웹 브라우저 화면에 작성한
    // 글만 보인다.
    @GetMapping("hello-string")
    @ResponseBody   // 넣어줘야함. http의 body 부분에 return 값을 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // "hello-string"
    }

}
