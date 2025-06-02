// 文件名: LazySingletonUnsafe.java (示意)
package com.example.designpattern.singletonpattern;

/**
 * 懒汉式单例 (线程不安全)
 * 特点：第一次调用getInstance()时才创建实例。
 */
public class LazySingletonUnsafe {

    private static LazySingletonUnsafe instance;

    // 私有化构造函数
    private LazySingletonUnsafe() {
        System.out.println("懒汉式单例(线程不安全)：实例已创建。");
    }

    // 全局静态访问点
    public static LazySingletonUnsafe getInstance() {
        if (instance == null) { // 问题点：多线程环境下可能创建多个实例
            instance = new LazySingletonUnsafe();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from LazySingletonUnsafe: " + this.hashCode());
    }

    // (多线程测试代码会比较复杂，这里省略，但强调其线程不安全性)
}