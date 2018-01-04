<html>
<#include "../common/header.ftl"/>
<body>
<div id="wrapper" class="toggled">

    <!-- sidebar -->
    <#include "../common/nav.ftl"/>

    <!-- content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productInfoPage.content as product>
                        <tr>
                            <td>${product.productId}</td>
                            <td>${product.productName}</td>
                            <td><img style="width:80px;height:80px;" src="${product.productIcon}"></td>
                            <td>${product.productPrice}</td>
                            <td>${product.productStock}</td>
                            <td>${product.productDescription}</td>
                            <td>${product.categoryType}</td>
                            <td>${product.createTime}</td>
                            <td>${product.updateTime}</td>
                            <td><a href="/seller/product/detail">修改</a></td>
                            <td>
                                <#if product.getProductStatusEnum().code == 0>
                                    <a href="/seller/product/offSale?productId=${product.productId}">下架</a>
                                <#else>
                                    <a href="/seller/product/onSale?productId=${product.productId}">上架</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled">
                            <a>上一页</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/seller/product/list?page=${currentPage - 1}&size=${size}">上一页</a>
                        </li>
                    </#if>

                    <#list 1..productInfoPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled">
                                <a>${index}</a>
                            </li>
                        <#else>
                            <#if productInfoPage.getTotalPages() gt 5>
                                <#if (currentPage-index)?abs lte 2 >
                                    <li>
                                        <a href="/seller/product/list?page=${index}&size=${size}">${index}</a>
                                    </li>
                                <#elseif (currentPage-index)?abs == 3>
                                    <li class="disabled">
                                        <a>..</a>
                                    </li>
                                </#if>
                            <#else>
                                <li>
                                    <a href="/seller/product/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            </#if>
                        </#if>
                    </#list>

                    <#if currentPage gte productInfoPage.getTotalPages()>
                        <li class="disabled">
                            <a>下一页</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/seller/product/list?page=${currentPage + 1}&size=${size}">下一页</a>
                        </li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>

