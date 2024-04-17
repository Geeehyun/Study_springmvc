package org.fullstack4.springmvc.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping(value = "/bbs")
public class BbsController {
    @GetMapping("/list")
    public void list() {
        log.info("---------------------");
        log.info("BbsController => list()");
        log.info("---------------------");
    }
    @GetMapping("/view")
    public void view(int idx) {
        log.info("---------------------");
        log.info("BbsController => view()");
        log.info("---------------------");
    }
    @GetMapping("/regist")
    public void registGet() {
        log.info("---------------------");
        log.info("BbsController => registGet()");
        log.info("---------------------");
    }
    @PostMapping("/regist")
    public void registPost() {
        log.info("---------------------");
        log.info("BbsController => registPost()");
        log.info("---------------------");
    }
    @GetMapping("/modify")
    public void modifyGet() {
        log.info("---------------------");
        log.info("BbsController => modifyGet()");
        log.info("---------------------");
    }
    @PostMapping("/modify")
    public void modifyPost() {
        log.info("---------------------");
        log.info("BbsController => modifyPost()");
        log.info("---------------------");
    }
    @PostMapping("/delete")
    public void deletePost() {
        log.info("---------------------");
        log.info("BbsController => deletePost()");
        log.info("---------------------");
    }
}
