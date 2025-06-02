// 文件名: LazySingletonSynchronizedMethod.java (示意)
package com.example.designpattern.singletonpattern;

/**
 * 懒汉式单例 (同步方法，线程安全)
 * 特点：实现了延迟加载，并且是线程安全的。
 */
public class LazySingletonSynchronizedMethod {

    private static LazySingletonSynchronizedMethod instance;

    // 私有化构造函数
    private LazySingletonSynchronizedMethod() {
        System.out.println("懒汉式单例(同步方法)：实例已创建。");
    }

    // 全局静态访问点，使用 synchronized 关键字确保线程安全
    public static synchronized LazySingletonSynchronizedMethod getInstance() {
        if (instance == null) {
            instance = new LazySingletonSynchronizedMethod();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from LazySingletonSynchronizedMethod: " + this.hashCode());
    }
}