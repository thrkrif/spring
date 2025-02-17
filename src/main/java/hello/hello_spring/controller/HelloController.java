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
    // 컨트롤러를 찾아서 viewResolver에게 던진다
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 방식 : resources에 html 파일을 생성하지 않아도 된다.
    // 위의 mvc 방식과 차이 없어보이지만 페이지 소스를 보면 웹 브라우저 화면에 작성한
    // 글만 보인다.
    @GetMapping("hello-string")
    @ResponseBody   // 넣어줘야함. http의 body 부분에 return 값을 직접 넣어주겠다.
    // ResponseBody 어노테이션이 있으면 지금 return 처럼 string만 전달하는 경우
    // viewResolver에게 던지지 않고 http 응답에 바로 넣어 전달한다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // "hello-string" , 실제로 이렇게 쓰는 일은 없다.
    }

    // 진짜 API를 보여주마.
    @GetMapping("hello-api")
    @ResponseBody // 이 어노테이션이 있으면 viewResolver에게 던지지 않는다.
    // 위와 다른 점은 return 값이 객체라는 점
    // 객체인 경우 default : json 방식으로 데이터를 만들어서 http 응답에 반환한다.
    // 더 디테일 한 건 뒤에 설명하겠다.
    // 객체를 json으로 바꿔주는 대표적 라이브러리 : Jackson
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
