// 文件路径: src/main/java/com/example/designpattern/paymentsystem/CardPayServiceImpl.java
package com.example.designpattern.paymentsystem;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

/**
 * 银行卡支付服务实现
 */
@Service("cardPayService") // 将其声明为Spring Bean
public class CardPayServiceImpl implements PaymentService {

    @Override
    public void pay(BigDecimal amount) {
        // 实际项目中对接银行支付网关的逻辑
        System.out.println("正在通过银行卡支付 " + amount + " 元... 💳");
        System.out.println("银行卡支付成功！");
    }

    @Override
    public PaymentType getServiceType() {
        return PaymentType.CARD_PAY; // 返回当前服务的类型
    }
}