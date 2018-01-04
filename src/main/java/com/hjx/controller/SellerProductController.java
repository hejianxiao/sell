package com.hjx.controller;

import com.hjx.dataobject.ProductInfo;
import com.hjx.dto.OrderDTO;
import com.hjx.enums.ResultEnum;
import com.hjx.exception.SellException;
import com.hjx.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hjx
 * 2018/1/4 0004.
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    private ProductInfoService productInfoService;

    @Autowired
    public SellerProductController(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
    }

    /**
     * 商品列表
     * @param page 第几页，从第一页开始
     * @param size 一页多少条数据
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       Model model) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = new PageRequest(page - 1,size,sort);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        model.addAttribute("productInfoPage",productInfoPage);
        model.addAttribute("currentPage",page);
        model.addAttribute("size",size);
        return "product/list";
    }

    /**
     * 上架商品
     * @param productId 商品id
     */
    @RequestMapping("/onSale")
    public String onSale(@RequestParam("productId") String productId,
                         Model model) {
        model.addAttribute("url", "list");
        try {
            productInfoService.onSale(productId);
            model.addAttribute("msg", ResultEnum.PRODUCT_ONSALE_SUCCESS.getMsg());
        } catch (SellException e) {
            log.error("【卖家端上架商品】发生异常={}",e.getMessage());
            model.addAttribute("msg", e.getMessage());
            return "common/error";
        }
        return "common/success";
    }

    /**
     * 下架商品
     * @param productId 商品id
     */
    @RequestMapping("/offSale")
    public String offSale(@RequestParam("productId") String productId,
                         Model model) {
        model.addAttribute("url", "list");
        try {
            productInfoService.offSale(productId);
            model.addAttribute("msg", ResultEnum.PRODUCT_OFFSALE_SUCCESS.getMsg());
        } catch (SellException e) {
            log.error("【卖家端下架商品】发生异常={}",e.getMessage());
            model.addAttribute("msg", e.getMessage());
            return "common/error";
        }
        return "common/success";
    }

}
