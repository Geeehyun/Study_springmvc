package org.fullstack4.springmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.common.CommonUtil;
import org.fullstack4.springmvc.dto.MemberDTO;
import org.fullstack4.springmvc.service.MemberServiceIf;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Log4j2
@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberServiceIf memberServiceIf;
    @GetMapping("/view")
    public void view(HttpServletRequest request,
                     Model model){
        log.info("---------------------");
        log.info("MemberController => view()");
        HttpSession session = request.getSession();
        String user_id = CommonUtil.parseString(session.getAttribute("user_id"));
        MemberDTO memberDTO = memberServiceIf.select(user_id);
        log.info("memberDTO : " + memberDTO);
        model.addAttribute("memberDTO", memberDTO);
        log.info("---------------------");
    }

    @GetMapping("/join")
    public void joinGet() throws Exception {
        log.info("---------------------");
        log.info("MemberController => joinGet()");
        log.info("---------------------");
    }
    @PostMapping("/join")
    public String joinPost(@Valid MemberDTO memberDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes)
                            {
        log.info("---------------------");
        log.info("MemberController => joinPost()");
        if(bindingResult.hasErrors()) {
            log.info("Errors : " + bindingResult.getAllErrors());
            log.info("memberDTO : " + memberDTO);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("memberDTO", memberDTO);
            redirectAttributes.addFlashAttribute("test", "test");
            redirectAttributes.addAttribute("test2", "test2");
            return "redirect:/member/join";
        }
        log.info("memberDTO : " + memberDTO);
        int result = memberServiceIf.join(memberDTO);
        if (result > 0) {
            log.info("회원가입 성공");
            redirectAttributes.addFlashAttribute("memberDTO", memberDTO);
            return "redirect:/member/complete";
        }
        log.info("회원가입 실패");
        log.info("---------------------");
        redirectAttributes.addFlashAttribute("errors", "회원가입 실패");
        redirectAttributes.addFlashAttribute("memberDTO", memberDTO);
        return "redirect:/member/join";
    }
    @GetMapping("/complete")
    public void completeGet() {
        log.info("---------------------");
        log.info("MemberController => completeGet()");
        log.info("---------------------");
    }

    @PostMapping("/modify")
    public String modifyPost(@Valid MemberDTO memberDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             HttpServletRequest request) {
        log.info("---------------------");
        log.info("MemberController => modifyPost()");
        if(bindingResult.hasErrors()) {
            log.info("bindingResult.getAllErrors" + bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/member/view";
        }
        HttpSession session = request.getSession();
        memberDTO.setUser_id(CommonUtil.parseString(session.getAttribute("user_id")));
        int result = memberServiceIf.update(memberDTO);
        if (result > 0) {
            log.info("회원정보 정상 변경");
            redirectAttributes.addFlashAttribute("result", "회원정보 정상 변경");
        } else {
            log.info("회원정보 변경 오류");
            redirectAttributes.addFlashAttribute("fail", "회원정보 변경 오류");
        }
        log.info("---------------------");
        return "redirect:/member/view";
    }

    @PostMapping("/leave")
    public String leavePost(RedirectAttributes redirectAttributes,
                            HttpServletRequest request) {
        log.info("---------------------");
        log.info("MemberController => leavePost()");
        
        HttpSession session = request.getSession();
        String user_id = CommonUtil.parseString(session.getAttribute("user_id"));
        int result = memberServiceIf.leave(user_id);
        if (result <= 0) {
            log.info("회원탈퇴 실패");
            redirectAttributes.addFlashAttribute("fail", "회원탈퇴 실패");
        } else {
            log.info("회원탈퇴 성공");
            session.invalidate();
        }
        log.info("---------------------");
        return "redirect:/member/view";
    }
    @PostMapping("/idCheck")
    @ResponseBody
    public void leavePost(@RequestParam(name = "user_id", defaultValue = "") String user_id,
                          HttpServletResponse response) throws IOException {
        log.info("---------------------");
        log.info("MemberController => idCheck()");

        int result = (int) memberServiceIf.idCheck(user_id);
        log.info("result : " + result);
        response.setContentType("application/text; charset=utf-8");
        response.getWriter().print(result);
        log.info("---------------------");
    }
}
