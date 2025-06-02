// 文件路径: src/main/java/com/example/designpattern/builderpattern/dto/UserProfileDto.java
package com.example.designpattern.builderpattern;

import lombok.Builder;
import lombok.ToString;
import lombok.Getter;
// 或者直接使用 @Data (包含了 @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor)
// 但对于DTO，特别是希望其不可变或部分不可变时，精确选择注解更好。
// 这里我们用 @Getter 和 @ToString, 并通过 @Builder 实现创建。

import java.util.List;
import java.util.Set;

@Getter // Lombok: 自动生成所有字段的getter方法
@ToString // Lombok: 自动生成 toString() 方法
@Builder // Lombok: 核心！自动生成建造者模式相关代码
public class UserProfileDto {

    // 假设这些是必需的 (Lombok @Builder 不直接强制必需，但可以在构造函数或build方法中校验)
    // 或者通过 @Builder(toBuilder = true) 和在构造函数中进行校验配合
    private final Long userId;
    private final String username;

    // 可选参数
    private String email;
    private String fullName;
    private Integer age;
    private String avatarUrl;

    // 可选参数，带默认值 (使用 @Builder.Default)
    @Builder.Default
    private boolean isActive = true; // 默认用户是激活状态
    @Builder.Default
    private String preferredLanguage = "zh-CN"; // 默认偏好语言

    // 集合类型的可选参数
    private Set<String> roles;
    private List<String> interests;

    // 注意：当使用 @Builder 时，Lombok 通常会生成一个全参数的构造函数（通常是包级私有或私有）
    // 以供Builder内部使用。如果我们想自定义构造函数或添加校验，需要注意Lombok的行为。
    // 如果我们希望某些字段是必需的，可以在 builder() 方法之后，调用build() 之前，
    // 或者在 build() 方法内部（如果自定义Builder）进行校验。
    // 对于 Lombok 生成的 Builder，如果想强制必需字段，通常是在类的构造器（Lombok 会生成一个全参构造器）
    // 中进行null检查并抛异常，但这需要配合 `@AllArgsConstructor(access = AccessLevel.PACKAGE)` 等。
    // 更简单的方式是依赖调用者在调用Builder方法时提供必需值。
}