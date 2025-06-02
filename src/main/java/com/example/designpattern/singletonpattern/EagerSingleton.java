// 文件名: EagerSingleton.java (示意，非项目代码)
package com.example.designpattern.singletonpattern;

/**
 * 饿汉式单例
 * 特点：类加载时就初始化实例，线程安全。
 */
public class EagerSingleton {

    // 1. 类加载时就创建实例 (final确保实例不被修改)
    private static final EagerSingleton instance = new EagerSingleton();

    // 2. 私有化构造函数
    private EagerSingleton() {
        System.out.println("饿汉式单例：实例已创建。");
        // 防止通过反射创建实例 (可选，增加健壮性)
        if (instance != null) {
            throw new IllegalStateException("已存在实例，不能通过反射创建！");
        }
    }

    // 3. 提供全局静态访问点
    public static EagerSingleton getInstance() {
        return instance;
    }

    // 示例方法
    public void showMessage() {
        System.out.println("Hello from EagerSingleton: " + this.hashCode());
    }

    public static void main(String[] args) {
        EagerSingleton s1 = EagerSingleton.getInstance();
        EagerSingleton s2 = EagerSingleton.getInstance();
        System.out.println("s1 和 s2 是同一个实例吗？ " + (s1 == s2)); // true
        s1.showMessage();
        s2.showMessage();
    }
}