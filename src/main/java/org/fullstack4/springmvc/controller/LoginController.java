package org.fullstack4.springmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.C;
import org.fullstack4.springmvc.dto.MemberDTO;
import org.fullstack4.springmvc.service.LoginServiceIf;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j2
@Controller
@RequestMapping(value = "/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginServiceIf serviceIf;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void loginGET() {
        log.info("---------------------");
        log.info("LoginController => loginGET()");
        log.info("---------------------");
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(@RequestParam(name = "user_id", defaultValue = "") String user_id,
                            @RequestParam(name = "pwd", defaultValue = "") String pwd,
                            @RequestParam(name = "save_id", defaultValue = "") String save_id,
                            @RequestParam(name = "auto_login", defaultValue = "") String auto_login,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest request,
                            HttpServletResponse response
                            ) {
        log.info("---------------------");
        log.info("LoginController => loginPOST()");
        log.info("user_id : " + user_id);
        log.info("pwd : " + pwd);
        log.info("save_id : " + save_id);
        log.info("auto_login : " + save_id);
        log.info("---------------------");
        MemberDTO dto = serviceIf.login(user_id, pwd);
        if (dto != null) {
            if (save_id.equals("on")) {
                Cookie cookie = new Cookie("save_id", user_id);
                cookie.setPath("/");
                cookie.setMaxAge(999999);
                response.addCookie(cookie);
            }
            if (auto_login.equals("on")) {
                Cookie cookie = new Cookie("auto_login", user_id);
                cookie.setPath("/");
                cookie.setMaxAge(999999);
                response.addCookie(cookie);
            }
            if (user_id.equals(dto.getUser_id()) && pwd.equals(dto.getPwd())) {
                HttpSession session = request.getSession();
                session.setAttribute("user_id", user_id);
                return "redirect:/bbs/list";
            }
        }

        redirectAttributes.addFlashAttribute("err", "로그인 실패");
        return "redirect:/login/login";
    }
    @RequestMapping(value = "/logout")
    public String logout() {
        log.info("---------------------");
        log.info("LoginController => logout()");
        log.info("---------------------");
        return "redirect:/bbs/list";
    }
}
