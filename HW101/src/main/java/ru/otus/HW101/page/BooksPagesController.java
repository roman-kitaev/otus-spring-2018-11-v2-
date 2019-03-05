package ru.otus.HW101.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BooksPagesController {
    @GetMapping("/")
    public String listPage(Model model) {
        model.addAttribute("keywords", "list books in Omsk, omsk, list books, list books free");
        return "list";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam String id) {
        return "edit";
    }
}
