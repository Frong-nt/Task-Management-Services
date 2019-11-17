package email.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestTemplate {

    @GetMapping("/testTemplate")
    public String test() {
        return "mailTemplate";
    }
}
