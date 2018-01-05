package com.hjx.handler;

import com.hjx.exception.SellException;
import com.hjx.exception.SellerAuthorizeException;
import com.hjx.exception.SpecialException;
import com.hjx.util.ResultVOUtil;
import com.hjx.vo.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    //拦截异常封装
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }

    //返回异常
    @ExceptionHandler(value = SpecialException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handlerSpecialException(SpecialException e){
    }

}
