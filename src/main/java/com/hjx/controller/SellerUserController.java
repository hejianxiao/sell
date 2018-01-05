package com.hjx.controller;

import com.hjx.constant.CookieConstant;
import com.hjx.constant.RedisConstant;
import com.hjx.dataobject.SellerInfo;
import com.hjx.enums.ResultEnum;
import com.hjx.service.SellerService;
import com.hjx.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 微信网页授权登陆后回调login并返回openid
 * Created by hjx
 * 2018/1/5 0005.
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    private SellerService sellerService;
    private StringRedisTemplate redisTemplate;

    @Autowired
    public SellerUserController(SellerService sellerService,
                                StringRedisTemplate redisTemplate) {
        this.sellerService = sellerService;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/login")
    public String login(HttpServletResponse response,
                        @RequestParam("openid") String openid,
                        Model model) {
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null) {
            model.addAttribute("msg", ResultEnum.USER_LOGIN_ERROR.getMsg());
            model.addAttribute("url", "");
            return "common/error";
        }

        //设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),
                openid,expire, TimeUnit.SECONDS);

        //设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN,token,expire);

        return "redirect:"+"/seller/order/list";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         Model model) {
        //从cookie查询
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if (cookie != null) {
            //清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            //清除cookie
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);

            model.addAttribute("msg",ResultEnum.USER_LOGOUT_SUCCESS.getMsg());
            model.addAttribute("url","/seller/order/list");
            return "common/success";
        }
        return "redirect:"+"/seller/order/list";
    }
}
