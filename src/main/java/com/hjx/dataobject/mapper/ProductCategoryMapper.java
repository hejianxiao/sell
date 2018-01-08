package com.hjx.dataobject.mapper;

import org.apache.ibatis.annotations.Insert;

import java.util.Map;

/**
 * Created by hjx
 * 2018/1/8 0008.
 */
public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name,category_type) values (#{category_name, jdbcType=VARCHAR},#{category_type, jdbcType=INTEGER})")
    int inserByMap(Map<String,Object> map);
}
