package org.fullstack4.springmvc.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Log4j2
@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void loginGET() {
        log.info("---------------------");
        log.info("LoginController => loginGET()");
        log.info("---------------------");
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void loginPOST() {
        log.info("---------------------");
        log.info("LoginController => loginPOST()");
        log.info("---------------------");
    }
    @RequestMapping(value = "/logout")
    public String logout() {
        log.info("---------------------");
        log.info("LoginController => logout()");
        log.info("---------------------");
        return "redirect:/bbs/list";
    }
}
