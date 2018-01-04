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
                                    <th>订单id</th>
                                    <th>姓名</th>
                                    <th>手机号</th>
                                    <th>地址</th>
                                    <th>金额</th>
                                    <th>订单状态</th>
                                    <th>支付方式</th>
                                    <th>支付状态</th>
                                    <th>创建时间</th>
                                    <th colspan="2">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list orderDTOPage.content as orderDTO>
                                <tr>
                                    <td>${orderDTO.orderId}</td>
                                    <td>${orderDTO.buyerName}</td>
                                    <td>${orderDTO.buyerPhone}</td>
                                    <td>${orderDTO.buyerAddress}</td>
                                    <td>${orderDTO.orderAmount}</td>
                                    <td>${orderDTO.getOrderStatusEnum().msg}</td>
                                    <td>微信H5</td>
                                    <td>${orderDTO.getPayStatusEnum().msg}</td>
                                    <td>${orderDTO.createTime}</td>
                                    <td><a href="/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                                    <td>
                                        <#if orderDTO.getOrderStatusEnum().code == 0>
                                            <a href="/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
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
                                    <a href="/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a>
                                </li>
                            </#if>

                            <#list 1..orderDTOPage.getTotalPages() as index>
                                <#if currentPage == index>
                                    <li class="disabled">
                                        <a>${index}</a>
                                    </li>
                                <#else>
                                    <#if orderDTOPage.getTotalPages() gt 5>
                                        <#if (currentPage-index)?abs lte 2 >
                                            <li>
                                                <a href="/seller/order/list?page=${index}&size=${size}">${index}</a>
                                            </li>
                                        <#elseif (currentPage-index)?abs == 3>
                                            <li class="disabled">
                                                <a>..</a>
                                            </li>
                                        </#if>
                                    <#else>
                                        <li>
                                            <a href="/seller/order/list?page=${index}&size=${size}">${index}</a>
                                        </li>
                                    </#if>
                                </#if>
                            </#list>

                            <#if currentPage gte orderDTOPage.getTotalPages()>
                                <li class="disabled">
                                    <a>下一页</a>
                                </li>
                            <#else>
                                <li>
                                    <a href="/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a>
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

