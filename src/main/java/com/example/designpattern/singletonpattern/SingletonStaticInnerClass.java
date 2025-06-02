// 文件名: SingletonStaticInnerClass.java (示意)
package com.example.designpattern.singletonpattern;

/**
 * 静态内部类单例 (Initialization-on-demand holder idiom)
 * 特点：延迟加载，线程安全，实现简洁。
 */
public class SingletonStaticInnerClass {

    // 1. 私有化构造函数
    private SingletonStaticInnerClass() {
        System.out.println("静态内部类单例：实例已创建。");
    }

    // 2. 定义一个私有的静态内部类来持有单例实例
    private static class SingletonHolder {
        // 关键：实例在 SingletonHolder 类被加载时才创建，并且只创建一次
        private static final SingletonStaticInnerClass INSTANCE = new SingletonStaticInnerClass();
    }

    // 3. 提供全局静态访问点
    public static SingletonStaticInnerClass getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void showMessage() {
        System.out.println("Hello from SingletonStaticInnerClass: " + this.hashCode());
    }
}