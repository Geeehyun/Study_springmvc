package org.fullstack4.springmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.C;
import org.fullstack4.springmvc.domain.MemberVO;
import org.fullstack4.springmvc.dto.MemberDTO;
import org.fullstack4.springmvc.service.LoginServiceIf;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
    public String loginPOST(@Valid MemberDTO memberDTO,
                            @RequestParam(name = "acc_url", defaultValue = "/bbs/list") String acc_url,
                            @RequestParam(name = "save_id", defaultValue = "") String save_id,
                            @RequestParam(name = "auto_login", defaultValue = "") String auto_login,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            BindingResult bindingResult
                            ) {
        log.info("---------------------");
        log.info("LoginController => loginPOST()");
        log.info("user_id : " + memberDTO.getUser_id());
        log.info("pwd : " + memberDTO.getPwd());
        log.info("save_id : " + save_id);
        log.info("auto_login : " + save_id);
        log.info("---------------------");
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("err", bindingResult.getAllErrors());
            return "redirect:/login/login";
        }
        MemberDTO loginDTO = serviceIf.login(memberDTO.getUser_id(), memberDTO.getPwd());
        if (loginDTO != null) {
            if (save_id.equals("on")) {
                Cookie cookie = new Cookie("save_id", loginDTO.getUser_id());
                cookie.setPath("/");
                cookie.setMaxAge(999999);
                response.addCookie(cookie);
            }
            if (auto_login.equals("on")) {
                Cookie cookie = new Cookie("auto_login", loginDTO.getUser_id());
                cookie.setPath("/");
                cookie.setMaxAge(999999);
                response.addCookie(cookie);
            }
            HttpSession session = request.getSession();
            session.setAttribute("user_id", loginDTO.getUser_id());
            return "redirect:"+acc_url;
        }
        redirectAttributes.addFlashAttribute("err", "로그인 실패");
        return "redirect:/login/login";
    }
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        log.info("---------------------");
        log.info("LoginController => logout()");
        log.info("---------------------");
        HttpSession session = request.getSession(false);
        //`request.getSession(false)` : 세션이 없으면 생성하지 말고, 있는것만 리턴해
        //`request.getSession(true)` : 세션이 없으면 생성해서 리턴, 있으면 있는거 리턴 (기본값)
        session.invalidate();
        return "redirect:/bbs/list";
    }
}
