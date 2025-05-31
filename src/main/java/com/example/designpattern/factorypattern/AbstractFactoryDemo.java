package com.example.designpattern.factorypattern;

// -------------------- 1. 抽象产品接口 --------------------
interface Chair {
    void sitOn();
    String getStyle();
}

interface Sofa {
    void lieOn();
    String getStyle();
}

// -------------------- 2. 具体产品实现 --------------------
// 现代风格产品
class ModernChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("坐在现代风格的椅子上。");
    }
    @Override
    public String getStyle() {
        return "现代风格";
    }
}

class ModernSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("躺在现代风格的沙发上。");
    }
    @Override
    public String getStyle() {
        return "现代风格";
    }
}

// 维多利亚风格产品
class VictorianChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("坐在维多利亚风格的椅子上。");
    }
    @Override
    public String getStyle() {
        return "维多利亚风格";
    }
}

class VictorianSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("躺在维多利亚风格的沙发上。");
    }
    @Override
    public String getStyle() {
        return "维多利亚风格";
    }
}

// -------------------- 3. 抽象工厂接口 --------------------
interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
}

// -------------------- 4. 具体工厂实现 --------------------
// 现代风格家具工厂
class ModernFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }
}

// 维多利亚风格家具工厂
class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public Sofa createSofa() {
        return new VictorianSofa();
    }
}

// -------------------- 5. 客户端 (演示) --------------------
class Application {
    private Chair chair;
    private Sofa sofa;

    // 客户端通过构造函数接收一个抽象工厂实例
    public Application(FurnitureFactory factory) {
        System.out.println("配置应用程序使用 " + factory.getClass().getSimpleName() + "...");
        this.chair = factory.createChair(); // 使用工厂创建椅子
        this.sofa = factory.createSofa();   // 使用工厂创建沙发
    }

    public void describeFurnitureStyle() {
        System.out.println("\n--- 家具描述 ---");
        System.out.print("这把椅子是 " + chair.getStyle() + " 的。");
        chair.sitOn();
        System.out.print("这个沙发是 " + sofa.getStyle() + " 的。");
        sofa.lieOn();
        System.out.println("--------------------");
    }
}

// 主程序入口
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        Application app;
        FurnitureFactory factory;

        // 场景1: 客户想要现代风格的家具
        System.out.println("客户选择: 现代风格");
        factory = new ModernFurnitureFactory();
        app = new Application(factory);
        app.describeFurnitureStyle();

        System.out.println("\n========================================\n");

        // 场景2: 客户切换到维多利亚风格的家具
        System.out.println("客户选择: 维多利亚风格");
        factory = new VictorianFurnitureFactory();
        app = new Application(factory); // 只需要更换工厂实例
        app.describeFurnitureStyle();
    }
}