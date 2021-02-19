## 一、简单工厂
主要类: NoodlesFactory
主要方法: NoodlesFactory.create, 详细信息见方法上注释
### 1、特点
1. 简单工厂是一个具体的类, 一个create()方法, 使用if或者switch根据参数不同创建不同类型的实例;
2. create()方法是静态时, 称为静态工厂方法, 好处: 不需要创建工厂对象; 坏处: 无法通过继承修改创建方法的行为 。
### 2、缺点
1. 扩展性差, 需要新加一种产品不仅要新建一个产品对应类, 还要改工厂类方法;
2. **不同产品需要不同额外参数时不支持**。
### 3、实现
```java
/**
 * @desc : 面条基类(抽象类或者接口均可)
 **/
public abstract class INoodles {

    /**
     * 描述面条信息
     */
    public abstract void desc();
}

/**
 * @desc : 泡面
 **/
public class PaoNoodle extends INoodles {

    @Override
    public void desc() {
        System.out.println("泡面好吃");
    }
}

/**
 * @desc : 拉面
 **/
public class LaNoodle extends INoodles {

    @Override
    public void desc() {
        System.out.println("拉面口味不同");
  }
}

/**
 * @desc : 简单工厂
 **/
public class NoodlesFactory {

    public static final int TYPE_PAO = 1;
    public static final int TYPE_LA = 2;

    /**
     * 1、参数也可以用Class, 用反射创建对象 Class c,  ()Class.forName(c.getName()).newInstance())
     * 2、也可以不指定参数, 消费者无需关注产品信息, 通过工厂获取INoodles所有实现类, 然后随机产生对象(设计模式之禅)
     * 3、延迟始化(Lazy initialization): 一个对象创建后就不释放, 再次用到不再初始化, 直接在内存中拿(设计模式之禅), 类在初始化比较耗资源时适用, 比如连接硬件资源很多参数
     * @param type
     * @return
     */
    public static INoodles create(int type){
        
        // 上面的第3点 下面创建时先get, 没有再创建, 创建后put进去
        private static HashMap<String,INoodles> humans = new HashMap<String,INoodles>();

        switch (type){
            case TYPE_LA:
                return new LaNoodle();
            default:
                return new PaoNoodle();
        }
    }
}

/**
 * @desc : 使用
 **/
public class Main {

    public static void main(String[] args) {
        INoodles noodles = NoodlesFactory.create(NoodlesFactory.TYPE_LA);
        noodles.desc();
    }
}
```