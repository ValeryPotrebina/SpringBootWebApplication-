package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.Console;

@Controller
@RequestMapping("/first")
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(HttpServletRequest request, Model model){
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
//        System.out.println("Hello, " + name + " " + surname);
        model.addAttribute("message", "Hello, " + name + " " + surname);
        return "first/hello";
    }
    @GetMapping("/bye")
    public String goodByePage(){
        return "first/bye";
    }
    @GetMapping("/calculator")
    public String calc(HttpServletRequest request, Model model) {
        Integer num1 = Integer.parseInt(request.getParameter("num1"));
        Integer num2 = Integer.parseInt(request.getParameter("num2"));
        String action = request.getParameter("action");
        System.out.println(num1 + " " + num2 + " " + action);
        double result;
        switch (action){
            case "sum" : {
                result = num1 + num2;
                model.addAttribute("calcResult", num1 + " + " + num2 + " = " + (result));
                break;
            }
            case "mult" : {
                result = num1*num2;
                model.addAttribute("calcResult", num1 + " * " + num2 + " = " + (result));
                break;
            }
            case "sub" : {
                result = num1 - num2;
                model.addAttribute("calcResult", num1 + " - " + num2 + " = " + (result));
                break;
            }
            case "div" : {
                result = num1 / (double) num2;
                model.addAttribute("calcResult", num1 + " / " + num2 + " = " + (result));
                break;
            }
            default: {
                model.addAttribute("calcResult", "wrong input");
                break;
            }
        }
        return "first/calculator";
    }
}
