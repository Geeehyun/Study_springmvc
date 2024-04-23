package org.fullstack4.springmvc.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Controller
public class SampleController {
    @GetMapping("/hello")
    public void hello(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        log.info("---------------------");
        log.info("hello");
        log.info("---------------------");

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
    @GetMapping("favicon.ico")
    @ResponseBody
    public void returnNoFavicon() {
        // Spring mvc favicon 에러 처리용
    }
}
