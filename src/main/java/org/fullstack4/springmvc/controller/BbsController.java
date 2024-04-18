package org.fullstack4.springmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
        log.info("bbsDTOList : " + bbsDTOList);
        model.addAttribute("bbsDTOList", bbsDTOList);
    }
    @GetMapping("/view")
    public void view(@RequestParam(name="idx", defaultValue = "0") int idx,
                     Model model) {
        log.info("---------------------");
        log.info("BbsController => view()");
        BbsDTO bbsDTO = bbsServiceIf.view(idx);
        bbsDTO.setContent(bbsDTO.getContent().replace("\r\n", "<br>"));
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
    public String registPost(@Valid BbsDTO dto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        log.info("---------------------");
        log.info("BbsController => registPost()");

        if(bindingResult.hasErrors()) {
            log.info("Errors");
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
                          Model model) {
        log.info("---------------------");
        log.info("BbsController => modifyGet()");
        log.info("---------------------");
        BbsDTO bbsDTO = bbsServiceIf.view(idx);
        log.info("bbsDTO : " + bbsDTO);
        log.info("---------------------");
        model.addAttribute("bbsDTO", bbsDTO);
    }
    @PostMapping("/modify")
    public String modifyPost(BbsDTO dto,
                           Model model) {
        log.info("---------------------");
        log.info("BbsController => modifyPost()");
        log.info("---------------------");
        int result = bbsServiceIf.modify(dto);
        if(result > 0 ){
            return "redirect:/bbs/view?idx="+dto.getIdx();
        } else {
            return "redirect:/bbs/modify?idx="+dto.getIdx();
        }
    }
    @PostMapping("/delete")
    public String deletePost(@RequestParam(name="idx", defaultValue = "0") int idx) {
        log.info("---------------------");
        log.info("BbsController => deletePost()");
        log.info("---------------------");
        int result = bbsServiceIf.delete(idx);
        if (result > 0) {
            return "redirect:/bbs/list";
        } else {
            return "redirect:/bbs/view?idx="+idx;
        }
    }
}
