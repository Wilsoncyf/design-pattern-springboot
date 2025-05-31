// 文件路径: src/main/java/com/example/designpattern/paymentsystem/PaymentService.java
package com.example.designpattern.paymentsystem;

import java.math.BigDecimal;

/**
 * 支付服务接口
 */
public interface PaymentService {

    /**
     * 执行支付操作
     * @param amount 支付金额
     */
    void pay(BigDecimal amount);

    /**
     * 获取当前支付服务的类型
     * @return PaymentType 支付类型枚举
     */
    PaymentType getServiceType();
}