package java8.basicType;

/**
 * @author : ZLC
 * @create : 2020-05-18 16:18
 * @desc : https://www.jianshu.com/p/2607b9021d59?utm_campaign=haruki&utm_content=note&utm_medium=reader_share&utm_source=weixin
 **/
public class IntegerTest {

    public static void main(String[] args) {
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;
        System.out.println(i1 == i2); //true
        System.out.println(i3 == i4); //false

        Integer num1 = 400;
        Integer num2 = 400;
        System.out.println(num1 == num2); //false
        System.out.println(num1.equals(num2)); //true


        String str = "abc";
        String str2 ="abc";
        System.out.println(str==str2); //true
        String str3 = new String("abc");
        String str4 = new String("abc");
        System.out.print(str3==str4); //false
    }
}
