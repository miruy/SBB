package babo.yurim.sbb.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 컨트롤러의 기능을 수행한다는 의미
public class HelloController {

    @GetMapping("/hello") // /hello로 get요청 되면 해당 함수 살행
    @ResponseBody // 해당 함수의 출력값이 return값 그 자체임을 의미(요청의 대한 응답이 이 함수자체)
    public String hello(){
        return "Hello Stringboot!";
    }
}
