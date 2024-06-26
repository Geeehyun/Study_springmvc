package org.fullstack4.springmvc.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@Log4j2
@ControllerAdvice
public class CommonException {
//    @ResponseBody
//    @ExceptionHandler(NumberFormatException.class)
//    public String exceptionNumber(NumberFormatException numberFormatException) {
//        log.info("-----------------------------");
//        log.info("CommonException >> exceptionNumber");
//        log.info(numberFormatException.getMessage());
//        log.info("-----------------------------");
//        return "Number format exception";
//    }

//    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    public String exception(Exception exception) {
//        log.info("-----------------------------");
//        log.info(exception.getMessage());
//        log.info("-----------------------------");
//
//        StringBuilder sb = new StringBuilder("<ul>");
//        sb.append("<li>" + exception.getMessage() + "</li>");
//
//        Arrays.stream(exception.getStackTrace()).forEach((el)->{
//            sb.append("<li>"+el+"</li>");
//        });
//        sb.append("</ul>");
//        return sb.toString();
//    }
    @ExceptionHandler(Exception.class)
    public String myError(Exception exception){
        log.info("-----------------------------");
        log.info(exception.getMessage());
        for(StackTraceElement el : exception.getStackTrace()) {
            log.info(el);
        }
        return "/common/error";
    }
}
