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









