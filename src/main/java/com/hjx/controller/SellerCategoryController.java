package com.hjx.controller;

import com.hjx.dataobject.ProductCategory;
import com.hjx.exception.SellException;
import com.hjx.form.CategoryForm;
import com.hjx.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by hjx
 * 2018/1/5 0005.
 */
@Controller
@RequestMapping("/seller/category")
@Slf4j
public class SellerCategoryController {

    private CategoryService categoryService;

    @Autowired
    public SellerCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        Sort sort = new Sort(Sort.Direction.ASC,"categoryType");
        List<ProductCategory> productCategoryList = categoryService.findAll(sort);
        model.addAttribute("productCategoryList", productCategoryList);
        return "category/list";
    }

    @GetMapping("/index")
    public String index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                        Model model) {
        if (categoryId != null) {
            ProductCategory productCategory = categoryService.findOne(categoryId);
            model.addAttribute("productCategory", productCategory);
        }
        return "category/index";
    }

    @PostMapping("/save")
    public String save(@Valid CategoryForm categoryForm,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("msg",bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("url","index");
            return "common/error";
        }
        ProductCategory productCategory;
        if (categoryForm.getCategoryId() != null) {
            productCategory = categoryService.findOne(categoryForm.getCategoryId());
        } else {
            productCategory = new ProductCategory();
        }
        try {
            BeanUtils.copyProperties(categoryForm,productCategory);
            categoryService.save(productCategory);
        } catch (SellException e) {
            model.addAttribute("msg",e.getMessage());
            model.addAttribute("url","index");
            return "common/error";
        }
        model.addAttribute("url","list");
        return "common/success";
    }

}
