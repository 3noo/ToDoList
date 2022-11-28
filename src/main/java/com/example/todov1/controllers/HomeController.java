package com.example.todov1.controllers;
import com.example.todov1.dao.TasksRepo;
import com.example.todov1.entities.Tasks;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    TasksRepo tp;

    @GetMapping
    public String displayTasks(Model model) {

        List<Tasks> tasks = tp.findAll();
        model.addAttribute("tasks", tasks);
        return "main/res";

    }

    @RequestMapping("/new")
    public String displayHome(Model model) {

        Tasks aTask = new Tasks();
        model.addAttribute("tasks", aTask);
        return "main/hom";

    }

    @PostMapping(value = "/save")
    public String processForm(Tasks tasks, Model model) {

        tp.save(tasks);
        return "redirect:/";


    }

    @GetMapping("/edit/{id}")
    public String editTutorial(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Tasks aTask =  tp.findById(id).get();
            model.addAttribute("tasks", aTask);
            tp.deleteById(id);

            return "main/hom";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteTutorial(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
        try {
           tp.deleteById(id);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/";
    }

}

