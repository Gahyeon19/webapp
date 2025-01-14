package com.example.template_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/text")
    public String text(Model model) {
        model.addAttribute("data", "Hello Spring!");
        return "text";      // templates/text.html
    }

    @GetMapping("/object")
    public String object(Model model) {
        Student student = new Student();
        student.setName("John");
        student.setScore(90);
        model.addAttribute("student", student);
        return "object";      // templates/object.html
    }
}
