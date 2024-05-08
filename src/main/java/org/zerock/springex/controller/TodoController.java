package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Log4j2

@Controller
public class TodoController {

    @RequestMapping("list")
    public void list(){
        log.info("todo....list");
    }

    //@RequestMapping(value="/register", method = RequestMethod.GET)
    @GetMapping("/register")
    public void register(){
        log.info("todo register.......");
    }

    @PostMapping("/register")
    public void registerPost(){
        log.info("POST todo register.....");
    }



}
