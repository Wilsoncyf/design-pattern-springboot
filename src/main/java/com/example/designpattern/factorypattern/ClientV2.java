package com.example.designpattern.factorypattern;// 抽象产品 (PaymentService) 和具体产品 (AlipayService, WeChatPayService) 同上，这里省略

// 1. 抽象工厂：支付服务工厂接口
interface IPaymentServiceFactory {
    PaymentService createPaymentService();
}

// 2. 具体工厂：支付宝支付工厂
class AlipayServiceFactory implements IPaymentServiceFactory {
    @Override
    public PaymentService createPaymentService() {
        return new AlipayService(); // 负责创建支付宝支付实例
    }
}

// 3. 具体工厂：微信支付工厂
class WeChatPayServiceFactory implements IPaymentServiceFactory {
    @Override
    public PaymentService createPaymentService() {
        return new WeChatPayService(); // 负责创建微信支付实例
    }
}

// 客户端使用
public class ClientV2 {
    public static void main(String[] args) {
        IPaymentServiceFactory alipayFactory = new AlipayServiceFactory();
        PaymentService alipay = alipayFactory.createPaymentService();
        alipay.pay(100.0);

        IPaymentServiceFactory wechatFactory = new WeChatPayServiceFactory();
        PaymentService wechat = wechatFactory.createPaymentService();
        wechat.pay(200.0);

        // 如果要增加银行卡支付 (CardPayService)
        // 1. 创建 CardPayService (具体产品)
        // 2. 创建 CardPayServiceFactory (具体工厂)
        // IPaymentServiceFactory cardPayFactory = new CardPayServiceFactory();
        // PaymentService cardPay = cardPayFactory.createPaymentService();
        // cardPay.pay(300.0);
        // 无需修改原有工厂代码！
    }
}