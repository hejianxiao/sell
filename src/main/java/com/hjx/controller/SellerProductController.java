package com.hjx.controller;

import com.hjx.dataobject.ProductCategory;
import com.hjx.dataobject.ProductInfo;
import com.hjx.enums.ResultEnum;
import com.hjx.exception.SellException;
import com.hjx.form.ProductForm;
import com.hjx.service.CategoryService;
import com.hjx.service.ProductInfoService;
import com.hjx.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by hjx
 * 2018/1/4 0004.
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    private ProductInfoService productInfoService;
    private CategoryService categoryService;

    @Autowired
    public SellerProductController(ProductInfoService productInfoService, CategoryService categoryService) {
        this.productInfoService = productInfoService;
        this.categoryService = categoryService;
    }

    /**
     * 商品列表
     * @param page 第几页，从第一页开始
     * @param size 一页多少条数据
     */
    @GetMapping("/list")
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
    @PostMapping("/onSale")
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
    @PostMapping("/offSale")
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

    /**
     * 商品维护
     * @param productId 商品id
     */
    @GetMapping("/index")
    public String index(@RequestParam(value = "productId", required = false) String productId,
                        Model model) {

        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productInfoService.findOne(productId);
            model.addAttribute("productInfo",productInfo);
        }

        Sort sort = new Sort(Sort.Direction.ASC,"categoryType");
        List<ProductCategory> productCategoryList = categoryService.findAll(sort);
        model.addAttribute("productCategoryList",productCategoryList);

        return "product/index";
    }

    /**
     * 保存商品
     * @param productForm 商品表单
     */
    @PostMapping("/save")
    public String save(@Valid ProductForm productForm,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("msg",bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("url","index");
            return "common/error";
        }
        ProductInfo productInfo;
        try {
            if (StringUtils.isEmpty(productForm.getProductId())) {
                productInfo = new ProductInfo();
                productForm.setProductId(KeyUtil.genUniqueKey());
            } else {
                productInfo = productInfoService.findOne(productForm.getProductId());
            }

            BeanUtils.copyProperties(productForm,productInfo);
            productInfoService.save(productInfo);
        } catch (SellException e) {
            model.addAttribute("msg",e.getMessage());
            model.addAttribute("url","index");
            return "common/error";
        }
        model.addAttribute("url","list");
        return "common/success";
    }

}
