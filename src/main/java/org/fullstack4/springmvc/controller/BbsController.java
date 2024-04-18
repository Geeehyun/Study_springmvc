package org.fullstack4.springmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.dto.BbsDTO;
import org.fullstack4.springmvc.service.BbsServiceIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        log.info("bbsDTOList : " + bbsDTOList);
        model.addAttribute("bbsDTOList", bbsDTOList);
    }
    @GetMapping("/view")
    public void view(@RequestParam(name="idx", defaultValue = "0") int idx,
                     Model model) {
        log.info("---------------------");
        log.info("BbsController => view()");
        BbsDTO bbsDTO = bbsServiceIf.view(idx);
        log.info("bbsDTO : " + bbsDTO);
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
    public String registPost(BbsDTO dto,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        log.info("---------------------");
        log.info("BbsController => registPost()");
        log.info("BbsDTO : " + dto.toString());
        log.info("---------------------");
        int result = bbsServiceIf.regist(dto);
        if (result > 0) {
            return "redirect:/bbs/list";
        } else {
            return "redirect:/bbs/regist";
        }

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
