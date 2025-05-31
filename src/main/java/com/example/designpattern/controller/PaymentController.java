// 文件路径: src/main/java/com/example/designpattern/controller/PaymentController.java
package com.example.designpattern.controller;

import com.example.designpattern.paymentsystem.PaymentService;
import com.example.designpattern.paymentsystem.PaymentServiceFactoryImpl; // 确保导入我们创建的工厂实现
import com.example.designpattern.paymentsystem.PaymentType; // 确保导入支付类型枚举
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 支付控制器，用于演示支付服务工厂的使用
 */
@RestController
@RequestMapping("/api/payments") // 所有此控制器下的API都以 /api/payments 开头
public class PaymentController {

    private final PaymentServiceFactoryImpl paymentServiceFactory;

    /**
     * 通过构造函数注入 PaymentServiceFactoryImpl
     * @param paymentServiceFactory 支付服务工厂实例
     */
    @Autowired
    public PaymentController(PaymentServiceFactoryImpl paymentServiceFactory) {
        this.paymentServiceFactory = paymentServiceFactory;
    }

    /**
     * 执行支付的API端点
     *
     * 示例URL:
     * - POST /api/payments/pay
     * 请求体 (JSON):
     * {
     * "paymentType": "alipay", // 或者 "wechat_pay", "card_pay"
     * "amount": 100.50
     * }
     *
     * @param request 包含支付类型和金额的请求体
     * @return 支付结果信息
     */
    @PostMapping("/pay")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest request) {
        try {
            // 1. 从请求中获取支付类型的字符串代码
            String paymentTypeCode = request.getPaymentType();
            if (paymentTypeCode == null || paymentTypeCode.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("支付类型 (paymentType) 不能为空");
            }

            // 2. (可选，但推荐) 将字符串代码转换为 PaymentType 枚举，这样更安全
            //    如果 paymentServiceFactory.getPaymentService(String) 内部已经做了转换，则这步可以省略，
            //    但在这里显式转换有助于早期验证。
            // PaymentType type = PaymentType.fromCode(paymentTypeCode);


            // 3. 使用工厂获取对应的支付服务实例
            //    我们直接使用接收字符串类型的工厂方法，它内部会处理枚举转换和错误校验
            PaymentService service = paymentServiceFactory.getPaymentService(paymentTypeCode);

            // 4. 执行支付
            service.pay(request.getAmount());

            // 5. 返回成功响应
            String successMessage = service.getServiceType().getDescription() + " 支付 " + request.getAmount() + " 元成功！";
            return ResponseEntity.ok(successMessage);

        } catch (IllegalArgumentException e) {
            // 如果支付类型无效或找不到对应的服务，工厂会抛出 IllegalArgumentException
            return ResponseEntity.badRequest().body("支付失败: " + e.getMessage());
        } catch (Exception e) {
            // 其他通用异常处理
            e.printStackTrace(); // 在生产环境中应使用更完善的日志记录
            return ResponseEntity.internalServerError().body("支付失败: 系统内部错误，请稍后再试。");
        }
    }

    /**
     * 内部类，用于封装支付请求的数据
     */
    static class PaymentRequest {
        private String paymentType; // 支付类型的字符串代码，例如 "alipay", "wechat_pay"
        private BigDecimal amount;

        // Getters and Setters (Lombok @Data 也可以)
        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
    }
}