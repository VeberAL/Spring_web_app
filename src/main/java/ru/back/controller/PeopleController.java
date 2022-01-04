package ru.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.back.dao.PersonDAO;
import ru.back.models.Person;

import javax.validation.Valid;

//каждый метод контроллера соответствует своему url
@Controller
//Все методы маппинга будут иметь url с /people/..
@RequestMapping("/people")
public class PeopleController {

    //CRUD операции с заглушками
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        return null;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        return null;
    }

    @GetMapping("/new")
    //@ModelAttribute работает как @PathVariable, но записывает сразу все атрибуты модели и записывает их
    public String newPerson(@ModelAttribute("person") Person person) {
        return null;
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        return null;
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        return null;
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        return null;
    }
}