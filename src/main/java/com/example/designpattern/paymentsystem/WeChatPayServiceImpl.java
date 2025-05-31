// 文件路径: src/main/java/com/example/designpattern/paymentsystem/WeChatPayServiceImpl.java
package com.example.designpattern.paymentsystem;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

/**
 * 微信支付服务实现
 */
@Service("weChatPayService") // 将其声明为Spring Bean
public class WeChatPayServiceImpl implements PaymentService {

    @Override
    public void pay(BigDecimal amount) {
        // 实际项目中对接微信支付SDK的逻辑
        System.out.println("正在通过微信支付 " + amount + " 元... 🧧");
        System.out.println("微信支付成功！");
    }

    @Override
    public PaymentType getServiceType() {
        return PaymentType.WECHAT_PAY; // 返回当前服务的类型
    }
}