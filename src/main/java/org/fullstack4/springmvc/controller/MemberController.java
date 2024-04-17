package org.fullstack4.springmvc.controller;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.dto.BbsDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequestMapping(value = "/member")
public class MemberController {
    @GetMapping("/view")
    public void view(@RequestParam(name="user_id", defaultValue = "") String user_id,
                     Model model) {
        log.info("---------------------");
        log.info("MemberController => view()");
        log.info("user_id : " + user_id);
        log.info("---------------------");
    }

    @GetMapping("/join")
    public void joinGet() {
        log.info("---------------------");
        log.info("MemberController => joinGet()");
        log.info("---------------------");
    }
    @PostMapping("/join")
    public String joinPost() {
        log.info("---------------------");
        log.info("MemberController => joinPost()");
        log.info("---------------------");
        return "redirect:/login/login";
    }

    @GetMapping("/modify")
    public void modifyGet() {
        log.info("---------------------");
        log.info("MemberController => joinGet()");
        log.info("---------------------");
    }
    @PostMapping("/modify")
    public void modifyPost() {
        log.info("---------------------");
        log.info("MemberController => joinPost()");
        log.info("---------------------");
    }

    @PostMapping("/leave")
    public void leavePost() {
        log.info("---------------------");
        log.info("MemberController => leavePost()");
        log.info("---------------------");
    }

}
