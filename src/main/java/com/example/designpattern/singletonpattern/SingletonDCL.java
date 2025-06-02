// 文件名: SingletonDCL.java (示意)
package com.example.designpattern.singletonpattern;

/**
 * 双重检查锁定 (DCL - Double-Checked Locking) 单例
 * 特点：延迟加载，线程安全 (需要配合 volatile 关键字)。
 */
public class SingletonDCL {

    // 关键1: 使用 volatile 关键字确保 instance 的可见性和禁止指令重排序
    private static volatile SingletonDCL instance;

    // 私有化构造函数
    private SingletonDCL() {
        System.out.println("DCL单例：实例已创建。");
    }

    // 全局静态访问点
    public static SingletonDCL getInstance() {
        // 关键2: 第一次检查，如果实例已存在，则直接返回，无需同步
        if (instance == null) {
            // 关键3: 同步块，只在实例未创建时进入
            synchronized (SingletonDCL.class) {
                // 关键4: 第二次检查，防止多个线程同时通过第一次检查后重复创建实例
                if (instance == null) {
                    instance = new SingletonDCL();
                    // 指令重排序问题说明 (理论层面):
                    // new SingletonDCL() 不是原子操作，大致分为：
                    // 1. 分配内存空间
                    // 2. 初始化对象
                    // 3. 将 instance 引用指向分配的内存地址
                    // 如果没有 volatile，2和3可能会发生重排序，导致一个线程拿到一个未完全初始化的对象。
                    // volatile 可以禁止这种重排序。
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from SingletonDCL: " + this.hashCode());
    }
}