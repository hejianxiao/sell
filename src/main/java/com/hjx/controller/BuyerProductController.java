package com.hjx.controller;

import com.hjx.vo.ProductInfoVO;
import com.hjx.vo.ProductVO;
import com.hjx.vo.ResultVO;
import com.hjx.dataobject.ProductCategory;
import com.hjx.dataobject.ProductInfo;
import com.hjx.service.CategoryService;
import com.hjx.service.ProductInfoService;
import com.hjx.util.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 * Created by hjx
 * 2017/12/25 0025.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    private ProductInfoService productInfoService;

    private CategoryService categoryService;

    @Autowired
    public BuyerProductController(ProductInfoService productInfoService,CategoryService categoryService) {
        this.productInfoService = productInfoService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ResultVO list() {

        //查询在架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        List<Integer> categoryTypeList =productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        //查询对应商品类目
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVoList = new ArrayList<>();
            productInfoList.stream()
                    .filter(productInfo -> productInfo.getCategoryType().equals(productVO.getCategoryType()))
                    .forEach(productInfo -> {
                ProductInfoVO productInfoVO = new ProductInfoVO();
                BeanUtils.copyProperties(productInfo, productInfoVO);
                productInfoVoList.add(productInfoVO);
            });
            productVO.setProductInfoVOList(productInfoVoList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }


}
