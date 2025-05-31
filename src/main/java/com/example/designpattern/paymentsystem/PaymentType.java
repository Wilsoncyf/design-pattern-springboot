// 文件路径: src/main/java/com/example/designpattern/paymentsystem/PaymentType.java
package com.example.designpattern.paymentsystem;

/**
 * 支付类型枚举
 */
public enum PaymentType {
    ALIPAY("alipay", "支付宝支付"),
    WECHAT_PAY("wechat_pay", "微信支付"),
    CARD_PAY("card_pay", "银行卡支付");

    private final String code; // 类型的唯一编码
    private final String description; // 类型的描述

    PaymentType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // 可选: 根据code查找枚举的方法
    public static PaymentType fromCode(String code) {
        for (PaymentType type : PaymentType.values()) {
            if (type.getCode().equalsIgnoreCase(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未知的支付类型编码: " + code);
    }
}