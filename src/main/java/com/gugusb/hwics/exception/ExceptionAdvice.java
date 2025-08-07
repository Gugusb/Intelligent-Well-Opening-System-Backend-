package com.gugusb.hwics.exception;

import com.gugusb.hwics.utils.MessageRespnser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler({Exception.class})
    public MessageRespnser handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.error("统一异常处理" + e.getMessage(), e);
        return new MessageRespnser(500, "error", null);
    }
}
