package com.hjx.aspect;

import com.hjx.constant.CookieConstant;
import com.hjx.constant.RedisConstant;
import com.hjx.exception.SellerAuthorizeException;
import com.hjx.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by hjx
 * 2018/1/5 0005.
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    private StringRedisTemplate redisTemplate;

    @Autowired
    public SellerAuthorizeAspect(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Pointcut("execution(public * com.hjx.controller.Seller*.*(..))" +
        "&& !execution(public * com.hjx.controller.SellerUserController.*(..))")
    public void verify() {

    }
    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校验】Cookie查不到token");
            throw new SellerAuthorizeException();
        }
        //查询redis
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】Redis查不到token");
            throw new SellerAuthorizeException();
        }
    }
}
