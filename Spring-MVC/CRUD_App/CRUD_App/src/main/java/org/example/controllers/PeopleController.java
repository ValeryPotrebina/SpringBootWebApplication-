package org.example.controllers;

import org.example.dao.PersonDAO;
import org.example.models.Person;
import org.example.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    private final PersonValidator personValidator;
    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model){
        // Получим всх людей из DAO и передадим на отображение в представление
        model.addAttribute("people", personDAO.index());
        System.out.println("[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "] " + "show People");
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        //Получим одного человека по id из DAO и передадим на отображение в представление
        model.addAttribute("person", personDAO.show(id));
        System.out.println("[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "] " + "show newPerson");
        return "people/show";
    }

    /*@GetMapping("/new")
    public String newPerson(Model model) {
        //html форма
        System.out.println("[" + LocalDateTime.now() + "] " + "creating newPerson");
        model.addAttribute("person", new Person());
        return "people/new";
    }*/
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        //html форма
        System.out.println("[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "] " + "creating newPerson");
        return "people/new";
    }

    @PostMapping
    public  String create(@ModelAttribute("person") @Valid Person person,
                          BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        System.out.println("[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "] " + "saving newPerson");

        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return "people/new";
        }
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return "people/edit";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

}
