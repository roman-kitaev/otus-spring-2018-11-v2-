package ru.otus.HW181.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BooksPagesController {
    @GetMapping("/")
    public String publicPage(Model model) {
        model.addAttribute("keywords", "list books in Omsk, omsk, list books, list books free");
        return "public";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam String id) {
        return "edit";
    }

    @GetMapping("/admin")
    public String admin() {
        return "adminpage";
    }
}
