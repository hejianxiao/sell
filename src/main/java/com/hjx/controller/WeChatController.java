package com.hjx.controller;

import com.hjx.enums.ResultEnum;
import com.hjx.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by hjx
 * 2017/12/27 0027.
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {

    private WxMpService wxMpService;

    @Autowired
    public WeChatController(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }




    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException {
        String url = "http://hjx.natapp1.cc/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url,
                WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl,"UTF-8"));

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            String openid = wxMpOAuth2AccessToken.getOpenId();

            return "redirect:" + returnUrl + "?openid=" + openid;
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}",e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR,e.getError().getErrorMsg());
        }
    }






//    @GetMapping("/wx")
//    public void wx(HttpServletRequest request, HttpServletResponse response, String signature, String timestamp, String nonce, String echostr) throws IOException, NoSuchAlgorithmException, NoSuchAlgorithmException, NoSuchAlgorithmException, IOException {
//        System.out.println("signature:"+signature);
//        System.out.println("timestamp:"+timestamp);
//        System.out.println("nonce:"+nonce);
//        System.out.println("echostr:"+echostr);
//        System.out.println("TOKEN:"+"hjx");
//
//        String[] params = new String[] { "hjx", timestamp, nonce };
//        Arrays.sort(params);
//        // 将三个参数字符串拼接成一个字符串进行sha1加密
//        String clearText = params[0] + params[1] + params[2];
//        String algorithm = "SHA-1";
//        String sign = new String(
//                org.apache.commons.codec.binary.Hex.encodeHex(MessageDigest.getInstance(algorithm).digest((clearText).getBytes()), true));
//        // 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
//        if (signature.equals(sign)) {
//            System.out.println("通过");
//            response.getWriter().print(echostr);
//        }
//    }
}
