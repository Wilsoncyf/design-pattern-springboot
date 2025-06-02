// 文件名: SingletonEnum.java (示意)
package com.example.designpattern.singletonpattern;

/**
 * 枚举单例
 * 特点：实现最简洁，天然线程安全，防止反射和反序列化攻击。
 */
public enum SingletonEnum {
    INSTANCE; // 定义一个枚举元素，它本身就是单例的实例

    // 构造函数默认就是 private 的
    SingletonEnum() {
        System.out.println("枚举单例：实例已创建。 HashCode: " + this.hashCode());
    }

    public void showMessage() {
        System.out.println("Hello from SingletonEnum: " + this.hashCode());
    }

    public static void main(String[] args) {
        SingletonEnum s1 = SingletonEnum.INSTANCE;
        SingletonEnum s2 = SingletonEnum.INSTANCE;

        System.out.println("s1 和 s2 是同一个实例吗？ " + (s1 == s2)); // true
        s1.showMessage();
        s2.showMessage();

        // 尝试反射创建 (会失败)
        // try {
        //     Constructor<SingletonEnum> constructor = SingletonEnum.class.getDeclaredConstructor();
        //     constructor.setAccessible(true);
        //     SingletonEnum s3 = constructor.newInstance();
        //     System.out.println("s3: " + s3.hashCode());
        // } catch (Exception e) {
        //     e.printStackTrace(); // 会抛出异常，如 NoSuchMethodException 或 InstantiationException
        // }
    }
}