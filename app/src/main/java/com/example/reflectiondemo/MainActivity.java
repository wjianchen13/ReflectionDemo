package com.example.reflectiondemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 通过对象取得包名和类名
     * @param v
     */
    public void onTest1(View v) {
        Test t = new Test();
        println(t.getClass());
        println(t.getClass().getName());
    }

    /**
     * Class类的实例化
     * @param v
     */
    public void onTest2(View v) {
        //方式一：
        Test t = new Test();
        Class<? extends Test> c1 = t.getClass();
        println(c1);

        //方式二：
        //为了避免特殊性，这里不用Test类，而用java库中的String类
        Class<String> c2 = String.class;
        println(c2);

        //方式三：
        //forName()方法会抛出异常
        Class<?> c3 = null;
        try {
            c3 = Class.forName("com.example.reflectiondemo.Test");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        println(c3);
    }

    /**
     * 创建对象实例
     * @param v
     */
    public void onTest3(View v) {
        Class<?> c = null;
        try {
            //这里需要完整的包名和类名
            c = Class.forName("java.lang.String");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //生成一个字符串的引用
        String s = null;
        try {
            //将构造好的对象向下转型为String类
            //newInstance()方法会抛异常
            s = (String) c.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        println("字符串长度： " + s.length());
    }

    /**
     * Class类的有参构造对象
     * @param v
     */
    public void onTest4(View v) {
        Class<?> c = null;
        try {
            c = Class.forName("java.lang.String");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        char[] ch = {'h','e','l','l','o'};
        String s = null;
        try {
            //获得Class类对象的有参构造方法，括号里面参数的写法是：类型.class
            Constructor<?> con = c.getConstructor(char[].class);
            //用此构造方法构造一个新的字符串对象，参数为一个char数组
            s = (String) con.newInstance(ch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        println("构造的字符串：" + s);
    }

    /**
     * 取得类的构造方法
     * @param v
     */
    public void onTest5(View v) {
        Class<?> c = null;
        try {
            c = Class.forName("java.lang.Boolean");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //这里的getConstructors()方法返回的是一个Constructor数组
        Constructor<?>[] cons = c.getConstructors();
        //打印的方式你可以自己写，为了方便我用Arrays.toString()，凑合着看
        println(Arrays.toString(cons));
    }

    /**
     * 取得类所实现的接口
     * @param v
     */
    public void onTest6(View v) {
        Class<?> c = null;
        try {
            c = Class.forName("java.lang.Boolean");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Class<?>[] in = c.getInterfaces();
        println(Arrays.toString(in));
    }

    /**
     * 取得父类对象
     * @param v
     */
    public void onTest7(View v) {
        Class<?> c = null;
        try {
            c = Class.forName("java.lang.Boolean");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //注意了，这里不会是数组，why？
        Class<?> su = c.getSuperclass();
        println(su);
    }

    /**
     * 取得类的全部方法
     * @param v
     */
    public void onTest8(View v) {
        Class<?> c = null;
        try {
            c = Class.forName("java.lang.Boolean");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] m = c.getMethods();
        //好吧，这次我就大发慈悲的写个打印列表出来
        for (int i = 0; i < m.length; i++) {
            println(m[i]);
        }
    }

    /**
     * 取得本类的全部属性
     * @param v
     */
    public void onTest9(View v) {
        Class<?> c = null;
        try {
            c = Class.forName("com.example.reflectiondemo.Test");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Field[] f = c.getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
            println(f[i]);
        }
    }

    /**
     * 获取本类中属性的值
     * @param v
     */
    public void onTest10(View v) {
        try {
            Person p = new Person("zhangsan", 12);

            Class<?> c = p.getClass();

            //获取公共属性的值
            Field f1 = c.getField("name");
            //get(p)表明要获取是哪个对象的值
            String str = (String) f1.get(p);
            println("姓名： " + str);

            //获取私有属性的值
            Field f2 = c.getDeclaredField("age");
            //age是私有属性，所以要设置安全检查为true
            f2.setAccessible(true);
            int age = (int) f2.get(p);
            println("年龄： " + age);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射修改属性
     * @param v
     */
    public void onTest11(View v) {
        try {
            Person p = new Person("王二狗");
            System.out.println(p);
            Class<?> c = p.getClass();

            //定义要修改的属性
            Field f = c.getDeclaredField("name");
            f.setAccessible(true);
            //修改属性，传入要设置的对象和值
            f.set(p, "张二蛋");
            println(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射调用方法
     * @param v
     */
    public void onTest12(View v) {
        try {
            Person p = new Person();
            Class<?> c = p.getClass();

            //getMethod()方法需要传入方法名，和参数类型
            Method m1 = c.getMethod("print", int.class);
            //invoke()表示调用的意思，需要传入对象和参数
            m1.invoke(p, 10);

            Method m2 = c.getMethod("say", String.class);
            //这里的null表示不由对象调用，也就是静态方法
            m2.invoke(null, "你妹");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射操作数组
     * @param v
     */
    public void onTest13(View v) {
        int[] arr = {1,2,3,4,5};
        Class<?> c = arr.getClass().getComponentType();

        println("数组类型： " + c.getName());
        int len = Array.getLength(arr);
        println("数组长度： " + len);
        println("遍历数组： ");
        for (int i = 0; i < len; i++) {
            println(Array.get(arr, i) + " ");
        }
        System.out.println();
        //修改数组
        println("修改前的第一个元素： " + Array.get(arr, 0));
        Array.set(arr, 0, 3);
        println("修改后的第一个元素： " + Array.get(arr, 0));
    }

    /**
     * 
     * @param v
     */
    public void onTest14(View v) {

    }

    /**
     * JAVA 反射修改static,final修饰的变量  
     * @param source
     * @param target
     * @param name
     * @param value
     * @return
     */
    public static boolean setValue(@Nullable Object source, @NonNull Class<?> target,
                                   @NonNull String name, @Nullable Object value) {
        Field field = null;
        int modify = 0;
        Field modifiersField = null;
        boolean removeFinal = false;
        try {
            field = target.getDeclaredField(name);
            modify = field.getModifiers();
            //final修饰的基本类型不可修改
            if (field.getType().isPrimitive() && Modifier.isFinal(modify)) {
                return false;
            }
            //获取访问权限
            if (!Modifier.isPublic(modify) || Modifier.isFinal(modify)) {
                field.setAccessible(true);
            }
            //static final同时修饰
            removeFinal = Modifier.isStatic(modify) && Modifier.isFinal(modify);
            if (removeFinal) {
                modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(field, modify & ~Modifier.FINAL);
            }
            //按照类型调用设置方法
            if (value != null && field.getType().isPrimitive()) {
                if ("int".equals(field.getType().getName()) && value instanceof Number) {
                    field.setInt(source, ((Number) value).intValue());
                } else if ("boolean".equals(field.getType().getName()) && value instanceof Boolean) {
                    field.setBoolean(source, (Boolean) value);
                } else if ("byte".equals(field.getType().getName()) && value instanceof Byte) {
                    field.setByte(source, (Byte) value);
                } else if ("char".equals(field.getType().getName()) && value instanceof Character) {
                    field.setChar(source, (Character) value);
                } else if ("double".equals(field.getType().getName()) && value instanceof Number) {
                    field.setDouble(source, ((Number) value).doubleValue());
                } else if ("long".equals(field.getType().getName()) && value instanceof Number) {
                    field.setLong(source, ((Number) value).longValue());
                } else if ("float".equals(field.getType().getName()) && value instanceof Number) {
                    field.setFloat(source, ((Number) value).floatValue());
                } else if ("short".equals(field.getType().getName()) && value instanceof Number) {
                    field.setShort(source, ((Number) value).shortValue());
                } else {
                    return false;
                }
            } else {
                field.set(source, value);
            }
        } catch (Exception e) {
            return false;
        } finally {
            try {
                //权限还原
                if (field != null) {
                    if (removeFinal && modifiersField != null) {
                        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
                        modifiersField.setAccessible(false);
                    }
                    if (!Modifier.isPublic(modify) || Modifier.isFinal(modify)) {
                        field.setAccessible(false);
                    }
                }
            } catch (IllegalAccessException e) {
                //
            }
        }
        return true;
    }


    private void println(Object str) {
        System.out.println("==============================> " + str);
    }
}