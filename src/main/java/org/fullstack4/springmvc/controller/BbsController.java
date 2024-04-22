package org.fullstack4.springmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.common.CommonUtil;
import org.fullstack4.springmvc.dto.BbsDTO;
import org.fullstack4.springmvc.service.BbsServiceIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
@RequestMapping(value = "/bbs")
@RequiredArgsConstructor
public class BbsController {
    private final BbsServiceIf bbsServiceIf;
    @GetMapping("/list")
    public void list(Model model) {
        log.info("---------------------");
        log.info("BbsController => list()");
        log.info("---------------------");
        List<BbsDTO> bbsDTOList = bbsServiceIf.listAll();
//        log.info("bbsDTOList : " + bbsDTOList);
        model.addAttribute("bbsDTOList", bbsDTOList);
    }
    @GetMapping("/view")
    public void view(@RequestParam(name="idx", defaultValue = "0") int idx,
                     Model model) throws Exception {
        log.info("---------------------");
        log.info("BbsController => view()");
        BbsDTO bbsDTO = bbsServiceIf.view(idx);
        if(bbsDTO == null) {
            throw new Exception("없는 게시글 입니다.");
        }
        bbsDTO.setContent(bbsDTO.getContent().replace("\r\n", "<br>"));
//        log.info("bbsDTO : " + bbsDTO);
        log.info("---------------------");
        model.addAttribute("bbsDTO", bbsDTO);
    }
    @GetMapping("/regist")
    public void registGet() {
        log.info("---------------------");
        log.info("BbsController => registGet()");
        log.info("---------------------");
    }

    @PostMapping("/regist")
    public String registPost(@Valid BbsDTO dto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        log.info("---------------------");
        log.info("BbsController => registPost()");

        if(bindingResult.hasErrors()) {
            log.info("Errors : " + bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("dto", dto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            // 이런식으로 에러 있는지 bindingResult 체크해서 addFlashAttribute 보내면 처음 리다이렉트 한 페이지에서 한번만 조회하고 끝낼 수 있음.
            // getAllErrors는 Array 형태로 보내짐.
            return "redirect:/bbs/regist";
        }

        int result = bbsServiceIf.regist(dto);
        log.info("BbsDTO : " + dto.toString());
        log.info("result : " + result);
        log.info("---------------------");
        if (result > 0) {
            return "redirect:/bbs/list";
        } else {
            return "redirect:/bbs/regist";
        }
    }
    @GetMapping("/modify")
    public void modifyGet(@RequestParam(name="idx", defaultValue = "0") int idx,
                          HttpServletRequest request,
                          Model model) throws Exception {
        HttpSession session = request.getSession();
        log.info("---------------------");
        log.info("BbsController => modifyGet()");
        log.info("---------------------");
        BbsDTO bbsDTO = bbsServiceIf.view(idx);
        if(bbsDTO == null) {
            throw new Exception("업는 게시글 입니다.");
        }
        if(!CommonUtil.idCheck(CommonUtil.parseString(session.getAttribute("user_id")),CommonUtil.parseString(bbsDTO.getUser_id()))) {
            throw new Exception("본인 게시글만 수정 할 수 있습니다.");
        }
        log.info("bbsDTO : " + bbsDTO);
        log.info("---------------------");
        model.addAttribute("bbsDTO", bbsDTO);
    }
    @PostMapping("/modify")
    public String modifyPost(@Valid BbsDTO dto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("---------------------");
        log.info("BbsController => modifyPost()");
        log.info("---------------------");
        if (CommonUtil.idCheck(CommonUtil.parseString(session.getAttribute("user_id")),CommonUtil.parseString(dto.getUser_id()))) {
            if(bindingResult.hasErrors()) {
                log.info("Errors : " + bindingResult.getAllErrors());
                redirectAttributes.addFlashAttribute("dto", dto);
                redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
                return "redirect:/bbs/modify?idx="+dto.getIdx();
            }
            int result = bbsServiceIf.modify(dto);
            if(result > 0 ){
                return "redirect:/bbs/view?idx="+dto.getIdx();
            } else {
                return "redirect:/bbs/modify?idx="+dto.getIdx();
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "잘못된 접근입니다.");
            return "redirect:/bbs/list";
        }
    }
    @PostMapping("/delete")
    public String deletePost(@RequestParam(name="idx", defaultValue = "0") int idx,
                             @RequestParam(name="user_id", defaultValue = "") String user_id,
                             RedirectAttributes redirectAttributes,
                             HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("---------------------");
        log.info("BbsController => deletePost()");
        log.info("---------------------");
        if (CommonUtil.idCheck(CommonUtil.parseString(session.getAttribute("user_id")),CommonUtil.parseString(user_id))) {
            int result = bbsServiceIf.delete(idx);
            if (result > 0) {
                return "redirect:/bbs/list";
            } else {
                return "redirect:/bbs/view?idx="+idx;
            }
        } else {
            log.info("아이디 불일치");
            redirectAttributes.addFlashAttribute("error", "잘못된 접근입니다.");
            return "redirect:/bbs/list";
        }

    }
}
