package de.duper.user7372914.so.answer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping(value = "/login")
    String login(Model model) {
        return "login";
    }

    @GetMapping(value = "/dashboard")
    String dashboard(Model model) {
        return "view-names";
    }

}