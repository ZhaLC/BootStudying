## 工厂方法

简单工厂违背"开放-关闭原则", 一旦新添加产品就要修改工厂方法类的逻辑, 会造成工厂逻辑过于复杂。
针对于简单工厂(静态工厂)使用了静态工厂方法, 静态方法不能被重写, 会造成工厂角色无法形成基于继承的等级结构。
工厂方法模式, 新增工厂抽象类, 将类的实例化(具体产品的创建)转移到工厂类的子类(具体工厂)中完成, 即由子类决定创建哪一个类。

### 优点

- 即简单工厂的缺点, 每加一种产品就要修改工厂方法, 违背"开放-关闭原则";

- 符合单一职责原则, 每个具体工厂类只负责创建对应的产品;

- 不使用静态工厂方法, 可以形成基于继承的等级结构;

- 工厂方法模式是简单工厂的进一步抽象和扩展, 保留了简单工厂的封装优点的同时, 让扩展变得更简单, 让继承变得可行, 增加了**多态性**的体现。

### 缺点

- 每增加一种产品, 都要增加对应的工厂, 类的个数增加, 增加了系统的复杂度;

- 由于考虑到系统的可扩展性，需要引入抽象层，在客户端代码中均使用抽象层进行定义，增加了系统的抽象性和理解难度，且在**实现时可能需要用到DOM、反射等技术，增加了系统的实现难度**;

- 虽然保证了**工厂方法内**的**对修改关闭**, 但对于**使用工厂方法的类,** 如果要使用另一种产品, 仍然需要修改实例化的具体工厂类;

- 一个具体工厂只能创建一种具体产品

### 模式组成

| 组成（角色）                 | 关系                               | 作用                                                  |
| :--------------------------- | :--------------------------------- | :---------------------------------------------------- |
| 抽象产品（Product）          | 具体产品的父类                     | 描述具体产品的公共接口                                |
| 具体产品（Concrete Product） | 抽象产品的子类；工厂类创建的目标类 | 描述生产的具体产品                                    |
| 抽象工厂（Creator）          | 具体工厂的父类                     | 描述具体工厂的公共接口                                |
| 具体工厂（Concrete Creator） | 抽象工厂的子类；被外界调用         | 描述具体工厂；实现FactoryMethod工厂方法创建产品的实例 |

### 应用场景

- 当一个类不知道它所需要的对象的类时 
  在工厂方法模式中，**客户端不需要知道具体产品类的类名**，**只需要知道所对应的工厂即可**；
- 当一个类希望通过其子类来指定创建对象时 
  在工厂方法模式中，对于抽象工厂类只需要提供一个创建产品的接口，而由其子类来确定具体要创建的对象，利用面向对象的多态性和里氏代换原则，在程序运行时，子类对象将覆盖父类对象，从而使得系统更容易扩展。
- 将创建对象的任务委托给多个工厂子类中的某一个，**客户端在使用时可以无须关心是哪一个工厂子类创建产品子类**，**需要时再动态指定，可将具体工厂类的类名存储在配置文件或数据库中**。

### 代码实现

产品类

```java
//  面条基类(抽象类或者接口均可)
public abstract class INoodles {

    /**
     * 描述面条信息
     */
    public abstract void desc();
}

// 拉面
public class LaNoodle extends INoodles {

    @Override
    public void desc() {
        System.out.println("拉面口味不同");
    }
}

// 泡面
public class PaoNoodle extends INoodles {

    @Override
    public void desc() {
        System.out.println("泡面好吃");
    }
}
```

工厂类

```java
// 工厂方法 工厂基类
public abstract class NoodlesFactory {
    abstract INoodles create();
}

// 拉面具体工厂
public class LaNoodleFactory extends NoodlesFactory{

    @Override
    INoodles create() {
        return new LaNoodle();
    }
}

// 泡面具体工厂
public class PaoNoodle extends INoodles {

    @Override
    public void desc() {
        System.out.println("泡面好吃");
    }
}
```

测试方法

```java
public class Main {

    public static void main(String[] args) {
        LaNoodleFactory laNoodleFactory = new LaNoodleFactory();
        laNoodleFactory.create().desc();

        PaoNoodleFactory paoNoodleFactory = new PaoNoodleFactory();
        paoNoodleFactory.create().desc();
    }
}
```



