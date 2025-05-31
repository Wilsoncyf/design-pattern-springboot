// 文件路径: src/main/java/com/example/designpattern/paymentsystem/PaymentServiceFactoryImpl.java
package com.example.designpattern.paymentsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct; // 注意：对于 Spring Boot 3.x, PostConstruct 在 jakarta.annotation 包下

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 支付服务工厂实现
 * <p>
 * 该工厂负责根据支付类型查找并提供相应的支付服务实例。
 * 它会自动注入所有实现了 PaymentService 接口的 Spring Bean。
 * </p>
 */
@Component // 将其声明为Spring组件，使其能够被依赖注入和扫描
public class PaymentServiceFactoryImpl {

    private final List<PaymentService> paymentServices; // Spring会自动注入所有PaymentService的实现类

    // 使用EnumMap可以获得更好的性能和类型安全
    private final Map<PaymentType, PaymentService> serviceCache = new EnumMap<>(PaymentType.class);

    /**
     * 通过构造函数注入所有 PaymentService 的实现列表。
     * 当有多个 PaymentService 类型的 bean 时，Spring 会将它们全部收集到一个 List 中。
     *
     * @param paymentServices 所有实现了 PaymentService 接口的 Spring Bean 列表
     */
    @Autowired
    public PaymentServiceFactoryImpl(List<PaymentService> paymentServices) {
        this.paymentServices = paymentServices;
    }

    /**
     * 在依赖注入完成后，此方法会被自动调用（由于 @PostConstruct 注解）。
     * 我们在这里初始化 serviceCache，将支付服务按其类型存入Map中，方便快速查找。
     */
    @PostConstruct
    public void initServiceCache() {
        if (paymentServices == null || paymentServices.isEmpty()) {
            // 在实际应用中，这里可能需要更健壮的错误处理或日志记录
            System.err.println("警告: 没有找到任何支付服务实现!");
            return;
        }
        for (PaymentService service : paymentServices) {
            if (service.getServiceType() == null) {
                System.err.println("警告: 支付服务 " + service.getClass().getSimpleName() + " 的 getServiceType() 返回 null。");
                continue;
            }
            // 如果有多个相同类型的服务，这里会以后者覆盖前者，可以根据业务需求调整
            if (serviceCache.containsKey(service.getServiceType())) {
                 System.err.println("警告: 发现重复的支付服务类型 " + service.getServiceType() +
                                   " 由 " + service.getClass().getSimpleName() +
                                   " 和 " + serviceCache.get(service.getServiceType()).getClass().getSimpleName() +
                                   " 同时实现。后者将覆盖前者。");
            }
            serviceCache.put(service.getServiceType(), service);
            System.out.println("支付服务工厂: 注册服务 " + service.getClass().getSimpleName() + " 类型为 " + service.getServiceType());
        }
    }

    /**
     * 根据支付类型获取对应的支付服务实例。
     *
     * @param paymentType 支付类型枚举
     * @return 对应支付类型的 PaymentService 实例
     * @throws IllegalArgumentException 如果找不到对应类型的支付服务
     */
    public PaymentService getPaymentService(PaymentType paymentType) {
        PaymentService service = serviceCache.get(paymentType);
        if (service == null) {
            throw new IllegalArgumentException("不支持的支付类型或未找到对应的支付服务: " + paymentType);
        }
        return service;
    }

    /**
     * (可选) 根据支付类型的字符串代码获取支付服务。
     *
     * @param paymentTypeCode 支付类型的字符串代码 (例如: "alipay", "wechat_pay")
     * @return 对应支付类型的 PaymentService 实例
     * @throws IllegalArgumentException 如果找不到对应类型的支付服务或类型代码无效
     */
    public PaymentService getPaymentService(String paymentTypeCode) {
        PaymentType paymentType = PaymentType.fromCode(paymentTypeCode); // 使用枚举中的fromCode方法
        return getPaymentService(paymentType);
    }
}