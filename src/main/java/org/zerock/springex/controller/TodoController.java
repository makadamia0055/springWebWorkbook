package org.zerock.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

import javax.validation.Valid;
import java.time.LocalDate;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/todo")
@Controller
public class TodoController {

    private final TodoService todoService;

    @RequestMapping("list")
    public void list(){
        log.info("todo....list");
    }

    //@RequestMapping(value="/register", method = RequestMethod.GET)
    @GetMapping("/register")
    public void registerGET(){
        log.info("todo register.......");

    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("POST todo register.....");
        if(bindingResult.hasErrors()){
            log.info("has errors........");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

        log.info(todoDTO);

        todoService.register(todoDTO);

        return "redirect:/todo/list";

    }



}
