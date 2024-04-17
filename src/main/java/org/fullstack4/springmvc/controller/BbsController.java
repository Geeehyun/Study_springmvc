package org.fullstack4.springmvc.controller;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.dto.BbsDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public void view(@RequestParam(name="idx", defaultValue = "0") int idx,
                     @RequestParam(name="user_id", defaultValue = "")String user_id,
                     @RequestParam(name="title", defaultValue = "")String title,
                     @RequestParam(name="display_date", defaultValue = "")String display_date,
                     Model model) {
        log.info("---------------------");
        log.info("BbsController => view()");
        log.info("idx : " + idx);
        log.info("user_id : " + user_id);
        log.info("title : " + title);
        log.info("display_date : " + display_date);
        log.info("---------------------");
        model.addAttribute("idx", idx);
        model.addAttribute("user_id", user_id);
        model.addAttribute("title", title);
        model.addAttribute("display_date", display_date);
    }
    @GetMapping("/regist")
    public void registGet() {
        log.info("---------------------");
        log.info("BbsController => registGet()");
        log.info("---------------------");
    }
    @PostMapping("/regist")
    public String registPost(BbsDTO dto,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        log.info("---------------------");
        log.info("BbsController => registPost()");
        log.info("user_id : " + dto.getUser_id());
        log.info("title : " + dto.getTitle());
        log.info("content : " + dto.getContent());
        log.info("display_date : " + dto.getDisplay_date());
        log.info("dto.toString : " + dto.toString());
        log.info("---------------------");
        redirectAttributes.addAttribute("title", dto.getTitle()); //redirect 주소에 쿼리스트링으로 값을 넣어주는 방식
        redirectAttributes.addFlashAttribute("title2", dto.getTitle()); //안보이게 값 보내주는 방식
        return "redirect:/bbs/list";
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

    @GetMapping("/list2")
    public void list2(String s1, int i2) {
        log.info("--------------------");
        log.info("BbsController => list2()");
        log.info("s1 : " + s1);
        log.info("i2 : " + i2);
        log.info("--------------------");
    }
}
