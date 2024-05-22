package org.zerock.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.PageRequestDTO;
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

    @RequestMapping("/list")
    public void list(Model model, PageRequestDTO pageRequestDTO){
        log.info("todo list....");
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
        //model.addAttribute("dtoList", todoService.getAll());
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long tno, PageRequestDTO pageRequestDTO, Model model){
        TodoDTO dto = todoService.getOne(tno);
        log.info(dto);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(Long tno, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){
        log.info("Remove....................");
        log.info("tno : " + tno);

        todoService.remove(tno);

        redirectAttributes.addAttribute("page", 1);
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        return "redirect:/todo/list" + pageRequestDTO.getLink();
    }

    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO, @Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            log.info("has error");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/modify";
        }

        log.info(todoDTO);

        todoService.modify(todoDTO);

        redirectAttributes.addAttribute("tno", todoDTO.getTno());


        return "redirect:/todo/read";
    }



}
