# 用途
一个类只允许创建一个对象, 主要用于无状态工具类、全局配置信息等
# 单例四大原则
1. 构造私有
2. 以静态方法或者枚举返回实例
3. 确保实例只有一个, 尤其是多线程环境
4. 确保反序列化时不会重新构造对象
# 几种实现方式
主要就是饿汉式(非懒加载)、懒汉式(懒加载)、是否线程安全、静态内部类、枚举  
下面是几种可用的实现方式
## 饿汉式
简单, 静态常量实现, 类装载的时候进行了实例化, 避免了线程问题, 线程安全, 可以使用, 但是没有实现懒加载, 也就是所谓的饿汉式
~~~
public class Singleton{
    private static final Singleton instance = new Singleton();
    private Singleton(){}
    public static Singleton getInstance(){
        return instance;
    }
}
~~~
## DCL单例
双重检查, 实现了懒加载, 效率较高, 线程安全
~~~
//DCL单例模式
public class Singleton{
    // 此处必须是volatile
    private static volatile Singleton singleton;
    private Singleton(){}
    // 注意: 方法必须是 public static
    public static Singleton getSingleton(){
       if(singleton == null){
           synchronized(Singleton.calss){
               if(singleton == null){
                   // 这里实际是三步 不是原子的
                   // 为Singleton分配一块内存M
                   // 在内存M上对Singleton进行初始化
                   // 将内存M地址赋值给singleton变量
                   singleton = new Singleton();
               }
           }
       } 
       return singleton;
    }
}
~~~

## 静态内部类单例
利用jvm加载的特性, 实现了懒加载, 避免了线程问题
~~~
public class Singleton{
    private Singleton(){}
    // 静态内部类在外部类装载的时候并不会被加载
    // 类的静态属性只有在第一次装载时才会被实例化
    private static class Holder{
        private static final Singleton instance = new Singleton();
    } 
    public Singleton getInstance(){
        // 调用时才会装载内部类SingletonUtil 从而完成Singleton的实例化
        return Holder.instance;    
    }
}
~~~
## 枚举单例
~~~
public enum Singleton{
    INSTANCE;
    public static Singleton getInstance(){
        return INSTANCE;
    }
}
~~~
### effective java 书中推荐使用的单例模式  
### 不会受到反序列化、反射的破坏:   
**Java的序列化专门对枚举的序列化做了规定: 在序列化时, 只是将枚举对象的name属性输出到结果中, 在反序列化时通过java.lang.Enum的valueOf
方法根据名字查找对, 而不是新建一个新的对象,所以防止了反序列化对单例的破坏**  
**对于发射, 枚举类同样有防御措: 反射在通过newInstance创建对象时会检查这个类是否是枚举类, 如果是枚举类就会
throw new IllegalArgumentException("Cannot reflectively create enum objects")**  
### 枚举编译后是一个继承自java.lang.Enum的final类, 单例是类中的static final实例
~~~
// 枚举: 
public enum EnumTest{
	A,B;
}
// javap 更多: https://www.jianshu.com/p/6a8997560b05
// javap反编译后: 
javap EnumTest.class
Compiled from "EnumTest.java"
public final class EnumTest extends java.lang.Enum<EnumTest> {
  // 静态常量 jvm级别保证了单例
  public static final EnumTest A;
  public static final EnumTest B;
  public static EnumTest[] values();
  public static EnumTest valueOf(java.lang.String);
  static {};
}
~~~ 
# 单例模式存在的问题
单例对 OOP 特性的支持不友好  
单例会隐藏类之间的依赖关系  
单例对代码的扩展性不友好  
单例不支持有参数的构造函数  
...
# 重新理解单例
1. 进程唯一的单例 上面说的都是单进程下的单例 不支持部署多个进程
2. 线程唯一的单例 ThreadLocal 自己实现的话HashMap
3. 集群唯一的单例 分布式锁 对象存放在哪需要考虑
4. 多例: 指定N个实例... 推荐枚举, 还可以用map
# 参考
https://blog.csdn.net/weixin_36586120/article/details/105522491




