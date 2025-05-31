package com.example.designpattern.factorypattern;

// 1. 抽象产品：支付服务接口
interface PaymentService {
    void pay(double amount);
}

// 2. 具体产品：支付宝支付
class AlipayService implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("通过支付宝支付了：" + amount + "元 💸");
    }
}

// 3. 具体产品：微信支付
class WeChatPayService implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("通过微信支付支付了：" + amount + "元 🧧");
    }
}

// 4. 简单工厂类
class PaymentFactory {
    public static PaymentService createPaymentService(String type) {
        if ("alipay".equalsIgnoreCase(type)) {
            return new AlipayService();
        } else if ("wechat".equalsIgnoreCase(type)) {
            return new WeChatPayService();
        } else {
            throw new IllegalArgumentException("不支持的支付类型: " + type);
        }
    }
}

// 客户端使用
public class Client {
    public static void main(String[] args) {
        PaymentService alipay = PaymentFactory.createPaymentService("alipay");
        alipay.pay(100.0);

        PaymentService wechat = PaymentFactory.createPaymentService("wechat");
        wechat.pay(200.0);
    }
}