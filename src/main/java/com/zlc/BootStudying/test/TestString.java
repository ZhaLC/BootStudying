package com.zlc.BootStudying.test;

import javax.xml.bind.PrintConversionEvent;
import javax.xml.bind.SchemaOutputResolver;
import java.lang.reflect.Field;

/**
 * @author : ZLC
 * @create : 2020-01-02 17:51
 * @desc : 通过反射改变String不可变对象
 **/
public class TestString {

    private final String name = "default";

    public void printName(){
        System.out.println(name);
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        String aa = "Hello World";
//        System.out.println("aa : "+ aa);
//        //Field valueFieldOfString = String.class.getDeclaredField("value");
//        Field valueFieldOfString = aa.getClass().getDeclaredField("value");
//        valueFieldOfString.setAccessible(true);
//        char[] value = (char[]) valueFieldOfString.get(aa);
//        value[5] = '_';
//        System.out.println("aa : " + aa);

        TestString p = new TestString();
        p.printName();
        Field nameField = p.getClass().getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(p, "111");
        p.printName(); //这里断点显示的其实是111(打印是default), 是jvm编译期优化,编译时直接把String处理成常量,
        // 所以printName方法相当于System.out.println("default");, 如果是StringBuilder类型的name就可以打印出来
        // 防止字符串编译时就被处理为常量, 可以经过一次运算实现, private final String name = (null == null ? "default4" : "");
        Object o = nameField.get(p);
        System.out.println(o); //这里打印的就是111
    }

}
