// 文件路径: src/main/java/com/example/designpattern/builderpattern/Computer.java
package com.example.designpattern.builderpattern;

/**
 * 产品类：电脑 (Computer)
 * 这是一个不可变类，其属性在构建完成后不能被修改。
 */
public class Computer {

    // 必需参数 (假设)
    private final String cpu;       // CPU型号
    private final String ram;       // 内存大小

    // 可选参数
    private final String storage;   // 存储类型和大小 (如 "1TB SSD")
    private final String graphicsCard; // 显卡型号 (如 "NVIDIA RTX 4090")
    private final String operatingSystem; // 操作系统 (如 "Windows 11 Pro")
    private final boolean bluetoothEnabled; // 是否启用蓝牙

    // 构造函数设为私有，强制通过Builder创建
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
        this.operatingSystem = builder.operatingSystem;
        this.bluetoothEnabled = builder.bluetoothEnabled;
    }

    // 只提供getter方法，不提供setter，确保不可变性
    public String getCpu() { return cpu; }
    public String getRam() { return ram; }
    public String getStorage() { return storage; }
    public String getGraphicsCard() { return graphicsCard; }
    public String getOperatingSystem() { return operatingSystem; }
    public boolean isBluetoothEnabled() { return bluetoothEnabled; }

    @Override
    public String toString() {
        return "Computer Specs: \n" +
                "  CPU: " + cpu + "\n" +
                "  RAM: " + ram + "\n" +
                (storage != null ? "  Storage: " + storage + "\n" : "") +
                (graphicsCard != null ? "  Graphics Card: " + graphicsCard + "\n" : "") +
                (operatingSystem != null ? "  OS: " + operatingSystem + "\n" : "") +
                "  Bluetooth Enabled: " + bluetoothEnabled + "\n";
    }

    // --- 静态内部建造者类 (Builder) ---
    public static class Builder {
        // 与外部类 Computer 相同的字段，但它们是可变的
        private final String cpu;       // 必需
        private final String ram;       // 必需

        private String storage;         // 可选
        private String graphicsCard;    // 可选
        private String operatingSystem = "Linux (Default)"; // 可选，带默认值
        private boolean bluetoothEnabled = false; // 可选，带默认值

        /**
         * Builder的构造函数，只接收必需参数
         * @param cpu CPU型号
         * @param ram 内存大小
         */
        public Builder(String cpu, String ram) {
            if (cpu == null || ram == null) {
                throw new IllegalArgumentException("CPU 和 RAM 是必填项！");
            }
            this.cpu = cpu;
            this.ram = ram;
            System.out.println("建造者初始化：CPU='" + cpu + "', RAM='" + ram + "'");
        }

        // 为可选参数提供setter方法 (返回Builder自身，实现链式调用)
        public Builder storage(String storage) {
            this.storage = storage;
            System.out.println("建造者：设置 Storage='" + storage + "'");
            return this; // 返回this以支持链式调用
        }

        public Builder graphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            System.out.println("建造者：设置 GraphicsCard='" + graphicsCard + "'");
            return this;
        }

        public Builder operatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            System.out.println("建造者：设置 OS='" + operatingSystem + "'");
            return this;
        }

        public Builder bluetoothEnabled(boolean bluetoothEnabled) {
            this.bluetoothEnabled = bluetoothEnabled;
            System.out.println("建造者：设置 BluetoothEnabled=" + bluetoothEnabled);
            return this;
        }

        /**
         * 核心的 build() 方法，用于创建并返回 Computer 实例
         * @return 构建好的 Computer 对象
         */
        public Computer build() {
            // 在这里可以添加一些构建前的校验逻辑，例如检查必需参数是否已设置（虽然我们放构造函数了）
            // 或者检查某些参数组合是否合法等。
            System.out.println("建造者：调用 build() 方法，正在创建 Computer 实例...");
            Computer computer = new Computer(this); // 将Builder自身传递给Computer的私有构造函数
            System.out.println("建造者：Computer 实例创建完毕！");
            return computer;
        }
    }
}