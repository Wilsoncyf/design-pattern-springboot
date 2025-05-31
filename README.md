# Spring Boot 设计模式实践 🚀

## 项目简介

本项目旨在通过 Java 和 Spring Boot 框架演示常见设计模式的实现与应用。项目中包含了多个设计模式的实际代码示例，重点展示如何在现代企业级应用开发中有效地运用这些模式来构建灵活、可维护的系统。

当前的核心示例是一个基于**工厂模式**思想构建的支付服务系统。

## 主要特性 ✨

* **支付服务系统**: 提供一个简单的 RESTful API 用于处理不同类型的支付。
* **动态支付方式选择**: 利用工厂机制，在运行时根据传入的类型（如支付宝、微信支付、银行卡支付）选择相应的支付服务提供者。
* **工厂模式演示 (Spring Boot)**: 清晰地展示了如何在 Spring Boot 环境下，结合依赖注入和组件扫描，实现一种灵活的服务工厂。
* **经典工厂模式示例**: 在 `com.example.designpattern.factorypattern` 包中包含了简单工厂、工厂方法和抽象工厂模式的纯 Java 基础示例代码。

## 技术栈 🛠️

* Java 17
* Spring Boot 3.5.0
    * Spring Web
    * Spring Core (IoC, DI)
* Maven (项目构建和依赖管理)
* Lombok (简化样板代码)

## 项目结构概览 📁

```
design-pattern/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/designpattern/
│   │   │       ├── DesignPatternApplication.java  # Spring Boot 主启动类
│   │   │       ├── controller/
│   │   │       │   └── PaymentController.java     # 支付API控制器
│   │   │       ├── factorypattern/              # 经典工厂模式Java示例
│   │   │       │   ├── AbstractFactoryDemo.java
│   │   │       │   ├── Client.java
│   │   │       │   └── ClientV2.java
│   │   │       └── paymentsystem/               # Spring Boot支付系统核心实现
│   │   │           ├── AlipayServiceImpl.java
│   │   │           ├── CardPayServiceImpl.java
│   │   │           ├── PaymentService.java        # 支付服务接口
│   │   │           ├── PaymentServiceFactoryImpl.java # 支付服务工厂
│   │   │           ├── PaymentType.java         # 支付类型枚举
│   │   │           └── WeChatPayServiceImpl.java
│   │   └── resources/
│   │       ├── application.properties         # Spring Boot配置文件
│   │       ├── static/
│   │       └── templates/
│   └── test/
├── .gitattributes
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── *.puml                                     # PlantUML 设计图源文件
└── *.png                                      # PlantUML 生成的设计图图片
```

* `com.example.designpattern.DesignPatternApplication.java`: Spring Boot 应用主入口。
* `com.example.designpattern.paymentsystem/`: Spring Boot 支付服务系统示例的核心包。
    * `PaymentType.java`: 定义了不同的支付方式枚举。
    * `PaymentService.java`: 支付服务的统一接口。
    * `AlipayServiceImpl.java`, `WeChatPayServiceImpl.java`, `CardPayServiceImpl.java`: 各种具体支付方式的实现类。
    * `PaymentServiceFactoryImpl.java`: 核心工厂类，用于根据类型获取具体的支付服务实例。
* `com.example.designpattern.controller/`: 包含应用的 REST API 控制器。
    * `PaymentController.java`: 暴露 `/api/payments/pay` 端点用于发起支付。
* `com.example.designpattern.factorypattern/`: 包含了一些基础的、非 Spring 环境的工厂模式示例代码。
* 项目根目录还包含了一些 PlantUML 图表文件 (`.puml` 和 `.png`)，用于可视化展示设计模式的结构。

## 如何运行项目 🏃‍♂️

1.  **克隆仓库** (如果您是从 GitHub 克隆):
    ```bash
    git clone [https://github.com/Wilsoncyf/design-pattern-springboot.git](https://github.com/Wilsoncyf/design-pattern-springboot.git)
    cd design-pattern-springboot
    ```
    或者直接在您当前的本地项目目录 `/Users/chenyongfeng/Documents/project/design-pattern/` 操作。

2.  **构建项目**:
    在项目根目录下，使用 Maven Wrapper 执行构建：
    ```bash
    ./mvnw clean package
    ```
    (如果您系统中已安装 Maven，也可以直接使用 `mvn clean package`)

3.  **运行应用**:
    ```bash
    java -jar target/design-pattern-0.0.1-SNAPSHOT.jar
    ```

4.  应用启动后，默认将在 `http://localhost:8080` 监听。

## API 端点测试 (支付服务) 🧪

您可以使用 Postman、curl 或其他 API 测试工具与以下端点交互：

* **处理支付**: `POST /api/payments/pay`
* **请求头 (Headers)**: `Content-Type: application/json`
* **请求体 (Request Body - JSON 格式)**:
    ```json
    {
        "paymentType": "alipay", // 可选值: "alipay", "wechat_pay", "card_pay"
        "amount": 100.50
    }
    ```
* **使用 `curl` 测试示例**:
    ```bash
    curl -X POST \
      http://localhost:8080/api/payments/pay \
      -H 'Content-Type: application/json' \
      -d '{
        "paymentType": "alipay",
        "amount": 123.45
      }'
    ```
  您可以尝试不同的 `paymentType` 和 `amount` 值进行测试。

## 已实现的设计模式 🌟

* **工厂模式 (Spring Boot 风格)**: 在 `paymentsystem` 包中得到了重点演示。通过结合 Spring 的依赖注入和服务发现机制，实现了一个灵活的工厂，用于创建和提供不同类型的 `PaymentService` 实现。
* **简单工厂、工厂方法、抽象工厂**: 在 `com.example.designpattern.factorypattern` 包中提供了这些经典工厂模式的基础 Java 实现，作为学习和对比参考。

---