// 文件路径: src/main/java/com/example/designpattern/singletonappconfig/ManualAppConfigManager.java
package com.example.designpattern.singletonappconfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 手动实现的单例应用配置管理器 (使用静态内部类方式)
 * 注意：这主要用于演示目的，在Spring项目中通常不推荐这样做。
 */
public class ManualAppConfigManager {

    private final Map<String, String> configurations = new HashMap<>();

    // 1. 私有化构造函数
    private ManualAppConfigManager() {
        // 模拟加载配置的过程
        System.out.println("手动单例 ManualAppConfigManager: 正在加载配置...");
        configurations.put("apiKey", "your_manual_api_key_12345");
        configurations.put("theme", "dark");
        configurations.put("feature.x.enabled", "true");
        System.out.println("手动单例 ManualAppConfigManager: 配置加载完毕。");
    }

    // 2. 定义一个私有的静态内部类来持有单例实例
    private static class SingletonHolder {
        private static final ManualAppConfigManager INSTANCE = new ManualAppConfigManager();
    }

    // 3. 提供全局静态访问点
    public static ManualAppConfigManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // 提供获取配置的方法
    public Optional<String> getConfiguration(String key) {
        return Optional.ofNullable(configurations.get(key));
    }

    public boolean isFeatureXEnabled() {
        return Boolean.parseBoolean(getConfiguration("feature.x.enabled").orElse("false"));
    }

    // 方便演示的打印所有配置方法
    public void printAllConfigurations() {
        System.out.println("--- 手动单例配置项 ---");
        configurations.forEach((key, value) -> System.out.println(key + " = " + value));
        System.out.println("----------------------");
    }

    // 这是一个示例，可以放在一个main方法或者其他测试代码中
 public static void main(String[] args) {
     ManualAppConfigManager configManager = ManualAppConfigManager.getInstance();
     configManager.printAllConfigurations();

     System.out.println("API Key: " + configManager.getConfiguration("apiKey").orElse("未找到"));
     System.out.println("Feature X Enabled: " + configManager.isFeatureXEnabled());

     ManualAppConfigManager anotherRef = ManualAppConfigManager.getInstance();
     System.out.println("configManager 和 anotherRef 是同一个实例吗? " + (configManager == anotherRef)); // true
 }
}