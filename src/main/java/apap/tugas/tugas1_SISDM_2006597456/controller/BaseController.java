package apap.tugas.tugas1_SISDM_2006597456.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class BaseController {
    @GetMapping("/")
    private String Home(){
        return "home";
    }

}
