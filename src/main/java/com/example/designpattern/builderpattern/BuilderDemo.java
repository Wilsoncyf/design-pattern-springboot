// 文件路径: src/main/java/com/example/designpattern/builderpattern/BuilderDemo.java
package com.example.designpattern.builderpattern;

public class BuilderDemo {
    public static void main(String[] args) {
        System.out.println("--- 开始构建高性能游戏电脑 ---");
        // 使用链式调用来构建一个高性能游戏电脑
        Computer gamingPC = new Computer.Builder("Intel i9-13900K", "64GB DDR5")
                .storage("2TB NVMe SSD + 4TB HDD")
                .graphicsCard("NVIDIA GeForce RTX 4090")
                .operatingSystem("Windows 11 Pro")
                .bluetoothEnabled(true)
                .build(); // 最后调用build()方法获取Computer实例

        System.out.println("\n--- 高性能游戏电脑配置详情 ---");
        System.out.println(gamingPC);


        System.out.println("\n--- 开始构建基础办公电脑 ---");
        // 构建一个只有必需配置和默认可选配置的办公电脑
        Computer officePC = new Computer.Builder("Intel i5-12400", "16GB DDR4")
                .storage("512GB NVMe SSD")
                // graphicsCard 未设置，将为 null
                // operatingSystem 未设置，将使用Builder中的默认值 "Linux (Default)"
                // bluetoothEnabled 未设置，将使用Builder中的默认值 false
                .build();

        System.out.println("\n--- 基础办公电脑配置详情 ---");
        System.out.println(officePC);

        System.out.println("\n--- 尝试构建缺少必需参数的电脑 (预期抛出异常) ---");
        try {
            Computer errorPC = new Computer.Builder(null, "8GB")
                                   .build();
        } catch (IllegalArgumentException e) {
            System.err.println("捕获到异常: " + e.getMessage());
        }
    }
}