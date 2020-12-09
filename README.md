# 反射
JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。

# 获取对象的运行时对象的类  
object.getClass(): 获取对象的运行时对象的类  
返回: class packagename/classname  

# 类名
object.getClass().getName(): 获取类名
返回: packagename/classname

# Class类的实例化
由于Class类没有构造方法，所以实例化Class类的方式有点特殊，有三种方式：
1. 对象.getClass( )  
2. 类.Class  
3. forName()  
forName()可以在类不确定的情况下实例化Class，更具灵活性  

# 创建对象实例
Class类中有一个方法叫做newInstance( ),它可以用来创建一个Class类对象的新实例  

# Class类的有参构造对象
构造方法需要使用getConstructor( )方法获得，参数类型则是：原有类型.class  
无论是有参还是无参，这里所使用的构造方法，原本的类里面必须对应存在  

# 获取类的构造方法
使用getConstructors()方法获取，该方法返回的是Constructor数组  
getDeclaredConstructors()的返回所有类型的构造器，包括public和非public  
getConstructors()只返回public。

# 取得类所实现的接口
使用getInterfaces()获取一个类所实现的接口，该方法返回的是一个Class数组  

# 取得父类对象
getSuperclass():返回一个类的父类对象，java中是单继承，父类只有一个  

# 取得类的全部方法
getDeclaredMethod：获取当前类的所有声明的方法，包括public、protected和private修饰的方法。需要注意的是，这些方法一定是在当前类中声明的，从父类中继承的不算，实现接口的方法由于有声明所以包括在内。  
getMethod：获取当前类和父类的所有public的方法。这里的父类，指的是继承层次中的所有父类。比如说，A继承B，B继承C，那么B和C都属于A的父类。  

# 取得本类的全部属性
getFields()：获得某个类的所有的公共（public）的字段，包括父类中的字段。  
getDeclaredFields()：获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段。    

# 获取本类中属性的值
获取公共属性的值:
```Java
Field f1 = c.getField("name");
String str = (String) f1.get(p); // get(p)表明要获取是哪个对象的值
```
获取私有属性的值:
```Java
Field f2 = c.getDeclaredField("age");
f2.setAccessible(true); // age是私有属性，所以要设置安全检查为true
int age = (int) f2.get(p);
```
# 通过反射修改属性
```Java
Field f = c.getDeclaredField("name");
f.setAccessible(true);
f.set(p, "张二蛋"); // 修改属性，传入要设置的对象和值
```

# 通过反射调用方法
```Java
//getMethod()方法需要传入方法名，和参数类型  
Method m1 = c.getMethod("print", int.class);  
//invoke()表示调用的意思，需要传入对象和参数  
m1.invoke(p, 10);  
```

# 通过反射操作数组  
getComponentType( )返回的是数组元素的Class  









