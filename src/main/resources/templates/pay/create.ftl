<script>
    function onBridgeReady(){
        WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                    "appId":"${payResponse.appId}",     //公众号名称，由商户传入
                    "timeStamp":"${payResponse.timeStamp}",         //时间戳，自1970年以来的秒数
                    "nonceStr":"${payResponse.nonceStr}", //随机串
                    "package":"${payResponse.packAge}",
                    "signType":"MD5",         //微信签名方式：
                    "paySign":"${payResponse.paySign}" //微信签名
                },
                function(res){
                    if(res.err_msg == "get_brand_wcpay_request:ok" ) {}     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                }
        );
    }
    if (typeof WeixinJSBridge == "undefined"){
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    }else{
        onBridgeReady();
    }
</script>


<!--2018-01-02 09:04:57,230 - 【微信支付】payResponse={-->
<!--"appId": "wxd898fcb01713c658",-->
<!--"timeStamp": "1514855097",-->
<!--"nonceStr": "3jpwOIC12lA3dIcu",-->
<!--"packAge": "prepay_id\u003dwx201801020905086a6dee94270449746469",-->
<!--"signType": "MD5",-->
<!--"paySign": "D5FA2DDB26156D8F631C9B7871391E9C"-->
<!--}-->