// 文件名: LazySingletonSynchronizedBlock.java (示意 - 但这种方式通常不推荐)
package com.example.designpattern.singletonpattern;

/**
 * 懒汉式单例 (同步代码块 - 这种方式仍然存在问题或效率不高)
 */
public class LazySingletonSynchronizedBlock {
    private static LazySingletonSynchronizedBlock instance;
    private LazySingletonSynchronizedBlock() {
        System.out.println("懒汉式单例(同步代码块)：实例已创建。");
    }
    public static LazySingletonSynchronizedBlock getInstance() {
        if (instance == null) { // 多个线程可能同时通过这个检查
            synchronized (LazySingletonSynchronizedBlock.class) {
                // 仍然需要再次检查，因为可能有多个线程进入了外层if
                if (instance == null) { // 第一个进入锁的线程创建实例
                    instance = new LazySingletonSynchronizedBlock();
                }
            }
        }
        return instance;
    }
    // ...
}