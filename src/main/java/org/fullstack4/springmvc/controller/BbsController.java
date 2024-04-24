package org.fullstack4.springmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.common.CommonUtil;
import org.fullstack4.springmvc.dto.BbsDTO;
import org.fullstack4.springmvc.dto.BbsReplyDTO;
import org.fullstack4.springmvc.dto.PageRequestDTO;
import org.fullstack4.springmvc.dto.PageResponseDTO;
import org.fullstack4.springmvc.service.BbsReplyServiceIf;
import org.fullstack4.springmvc.service.BbsServiceIf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Log4j2
@Controller
@RequestMapping(value = "/bbs")
@RequiredArgsConstructor
public class BbsController {
    private final BbsServiceIf bbsServiceIf;
    private final BbsReplyServiceIf bbsReplyServiceIf;
//    @GetMapping("/list")
//    public void list(Model model) { // 초기 버전
//        log.info("---------------------");
//        log.info("BbsController => list()");
//        log.info("---------------------");
//        List<BbsDTO> bbsDTOList = bbsServiceIf.listAll();
//        model.addAttribute("bbsDTOList", bbsDTOList);
//    }

    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO,
                     BindingResult bindingResult,
                     RedirectAttributes redirectAttributes,
                     Model model) {
        log.info("---------------------");
        log.info("BbsController => list() Start");
//        log.info("BbsController >> pageRequestDTO" + pageRequestDTO);
        if(bindingResult.hasErrors()) {
            log.info("BbsController >> list Error");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        }
        PageResponseDTO<BbsDTO> responseDTO = bbsServiceIf.bbsListByPage(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
//        log.info("BbsController >> responseDTO" + responseDTO);
        log.info("BbsController => list() End");
        log.info("---------------------");
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
        List<BbsReplyDTO> replyDTOList = bbsReplyServiceIf.list(idx);
        bbsDTO.setContent(bbsDTO.getContent().replace("\r\n", "<br>"));
        log.info("replyDTOList : " + replyDTOList);
        log.info("---------------------");
        model.addAttribute("bbsDTO", bbsDTO);
        model.addAttribute("replyDTOList", replyDTOList);
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

    @RequestMapping(value="/fileUpload", method= RequestMethod.GET)
    public String fileUploadGET() {
        return "/bbs/fileUpload";
    }

    @RequestMapping(value="/fileUpload", method= RequestMethod.POST)
    public String fileUploadPOST(@RequestParam("file") MultipartFile file) {
        String uploadFolder = "D:\\java4\\uploads";
        String fileRealName = file.getOriginalFilename(); //원래 파일의 이름
        long size = file.getSize();
        String fileExt = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length()); // 확장자명
        //엑셀.파.일xxx.xls --> 제일 마지막 인덱스의 . 에서부터 파일이름 끝에를 파싱

        log.info("============================");
        log.info("uploadFolder : " + uploadFolder);
        log.info("fileRealName : " + fileRealName);
        log.info("size : " + size);
        log.info("fileExt : " + fileExt);


        //새로운 파일명 생성
        UUID uuid = UUID.randomUUID();
        String[] uuids = uuid.toString().split("-");
        String newName = uuids[0];

        log.info("uuid : " + uuid);
        log.info("uuids : " + uuids);
        log.info("newName : " + newName);


        File saveFile = new File(uploadFolder + "\\" + newName + fileExt);

        try {
            file.transferTo(saveFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        log.info("============================");

        return "/bbs/fileUpload";
    }

    @RequestMapping(value="/fileUpload2", method= RequestMethod.GET)
    public String fileUpload2GET() {
        return "/bbs/fileUpload2";
    }
    @RequestMapping(value="/fileUpload2", method= RequestMethod.POST)
    public String fileUpload2POST(MultipartHttpServletRequest files) {
        String uploadFolder = "D:\\java4\\uploads";

        List<MultipartFile> list = files.getFiles("files");
        for(MultipartFile file : list) {
            String fileRealName = file.getOriginalFilename(); //원래 파일의 이름
            long size = file.getSize();
            String fileExt = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length()); // 확장자명
            //엑셀.파.일xxx.xls --> 제일 마지막 인덱스의 . 에서부터 파일이름 끝에를 파싱

            log.info("============================");
            log.info("uploadFolder : " + uploadFolder);
            log.info("fileRealName : " + fileRealName);
            log.info("size : " + size);
            log.info("fileExt : " + fileExt);

            //새로운 파일명 생성
            UUID uuid = UUID.randomUUID();
            String[] uuids = uuid.toString().split("-");
            String newName = uuids[0];

            log.info("uuid : " + uuid);
            log.info("uuids : " + uuids);
            log.info("newName : " + newName);

            File saveFile = new File(uploadFolder + "\\" + newName + fileExt);

            try {
                file.transferTo(saveFile);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            catch(Exception e) {
                e.printStackTrace();
            }

            log.info("============================");
        }
        return "/bbs/fileUpload2";
    }
}
