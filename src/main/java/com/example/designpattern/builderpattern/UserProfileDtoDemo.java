// 文件路径: src/main/java/com/example/designpattern/builderpattern/UserProfileDtoDemo.java
package com.example.designpattern.builderpattern;

import java.util.Arrays;
import java.util.HashSet;

public class UserProfileDtoDemo {
    public static void main(String[] args) {
        System.out.println("--- 使用 Lombok @Builder 创建 UserProfileDto 示例 ---");

        // 示例1: 创建一个包含大部分信息的用户配置
        UserProfileDto user1 = UserProfileDto.builder() // 获取Lombok生成的Builder实例
                .userId(1001L)
                .username("john_doe")
                .email("john.doe@example.com")
                .fullName("John Doe")
                .age(30)
                .avatarUrl("https://example.com/avatars/john.png")
                .isActive(true) // 可以覆盖默认值
                .preferredLanguage("en-US") // 覆盖默认值
                .roles(new HashSet<>(Arrays.asList("USER", "EDITOR")))
                .interests(Arrays.asList("Programming", "Photography", "Music"))
                .build(); // 调用build()方法生成最终的DTO对象

        System.out.println("\n用户1 配置详情:");
        System.out.println(user1); // @ToString 会输出易读的格式

        // 示例2: 创建一个只包含必需信息和部分可选信息的用户配置
        UserProfileDto user2 = UserProfileDto.builder()
                .userId(1002L)
                .username("jane_smith")
                .email("jane.smith@example.com")
                // fullName, age, avatarUrl 未设置 (对于对象类型字段，它们将是 null；对于基本类型，是其默认值，但我们这里没有基本类型可选字段)
                // isActive 和 preferredLanguage 将使用 @Builder.Default 设置的默认值
                .build();

        System.out.println("\n用户2 配置详情:");
        System.out.println(user2);
        System.out.println("用户2 是否激活: " + user2.isActive()); // true (来自 @Builder.Default)
        System.out.println("用户2 偏好语言: " + user2.getPreferredLanguage()); // "zh-CN" (来自 @Builder.Default)

        // 示例3: 演示必需参数 (虽然Lombok @Builder本身不强制，但我们设计上认为是必需的)
        // 如果我们想强制 userId 和 username，通常的做法是在接收这些值的服务层进行校验
        // 或者，如果自定义了构造函数并配合Lombok，可以在构造函数中进行校验。
        // Lombok @Builder 主要解决的是构造参数过多和可选参数的问题。
        System.out.println("\n假设 userId 和 username 是必需的。");
        UserProfileDto user3 = UserProfileDto.builder()
                .userId(1003L) // 提供了必需的 userId
                .username("minimal_user") // 提供了必需的 username
                .build();
        System.out.println("\n用户3 (最小配置):");
        System.out.println(user3);


        // 获取单个属性
        System.out.println("\n用户1的邮箱是: " + user1.getEmail());

    }
}