// 文件路径: src/main/java/com/example/designpattern/paymentsystem/AlipayServiceImpl.java
package com.example.designpattern.paymentsystem;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

/**
 * 支付宝支付服务实现
 */
@Service("alipayService") // 将其声明为Spring Bean，并指定一个名称
public class AlipayServiceImpl implements PaymentService {

    @Override
    public void pay(BigDecimal amount) {
        // 在实际项目中，这里会是对接支付宝SDK的复杂逻辑
        System.out.println("正在通过支付宝支付 " + amount + " 元... 💸");
        System.out.println("支付宝支付成功！");
    }

    @Override
    public PaymentType getServiceType() {
        return PaymentType.ALIPAY; // 返回当前服务的类型
    }
}