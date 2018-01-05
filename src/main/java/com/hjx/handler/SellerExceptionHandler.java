package com.hjx.handler;

import com.hjx.exception.SellerAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by hjx
 * 2018/1/5 0005.
 */
@ControllerAdvice
public class SellerExceptionHandler {

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public String handlerAuthorizeException(){
        //去登陆，这里应该返回的是微信网页授权登录的地址
        return null;
    }

}
