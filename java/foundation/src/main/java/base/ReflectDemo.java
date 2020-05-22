package base;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ReflectDemo {
    private static final String TAG = "Reflection";

    public static void main(String[] args) throws Exception {
//        getClassMethods();
        testReflection();
    }

    public static void getClassMethods() throws ClassNotFoundException {
        // 1.通过对象实例获取对应Class对象Object.getClass()--对于基本类型无法使用这种方法
        System.out.println();
        System.out.println("foo".getClass());
        System.out.println(byte[].class);
        System.out.println(HashSet.class);

        // 2.通过类的类型获取Class对象,基本类型同样可以使用这种方法
        System.out.println();
        System.out.println(boolean.class);
        System.out.println(int.class);
        System.out.println(double[][][][].class);

        // 3.通过类的全限定名获取Class对象， 基本类型无法使用此方法
        System.out.println();
        System.out.println(Class.forName("java.lang.String")); //通过Class.forName()方法加载的类，采用的是系统类加载器对于数组比较特殊
        System.out.println(Class.forName("[D"));    //相当于double[].class
        System.out.println(Class.forName("[[Ljava.lang.String;"));   //相当于String[][].class

        // 5.基本类型和void 类型的包装类可以使用TYPE字段获取
        System.out.println();
        System.out.println(Double.TYPE);
        System.out.println(Void.TYPE);

        // 6.另外还有一些反射方法可以获取Class对象，但前提是你已经获取了一个Class对象
        // Class.getSuperclass() --> 获得给定类的父类Class
    }

    public static void testReflection() {
        Class<?> c = HashMap.class;

        // 获取类名
        System.out.println(TAG + " Class : " + c.getCanonicalName());

        // 获取类限定符
        System.out.println(TAG + " Modifiers : " + Modifier.toString(c.getModifiers()));

        // 获取类泛型信息
        TypeVariable[] tv = c.getTypeParameters();
        if (tv.length != 0) {
            StringBuilder parameter = new StringBuilder(" Parameter : ");
            for (TypeVariable t : tv) {
                parameter.append(t.getName()).append(" ");
            }
            System.out.println(TAG + parameter.toString());
        } else {
            System.out.println(TAG + " -- No Type Parameters -- ");
        }

        // 获取类实现的所有接口
        Type[] interfaces = c.getGenericInterfaces();
        if (interfaces.length != 0) {
            StringBuilder intfs = new StringBuilder(" Implemented Interfaces : ");
            for (Type intf : interfaces) {
                intfs.append(intf.toString()).append(" ");
            }
            System.out.println(TAG + intfs.toString());
        } else {
            System.out.println(TAG + " -- No Implemented Interfaces -- ");
        }

        //获取类继承数上的所有父类
        List<Class> l = new ArrayList<>();
        printAncestor(c, l);
        if (l.size() != 0) {
            StringBuilder inheritance = new StringBuilder(" Inheritance Path : ");
            for (Class<?> cl : l){
                inheritance.append(cl.getCanonicalName());
                inheritance.append(" ");
            }
            System.out.println(TAG + inheritance.toString());
        } else {
            System.out.println(TAG + " -- No Super Classes -- ");
        }

        //获取类的注解(只能获取到 RUNTIME 类型的注解)
        Annotation[] ann = c.getAnnotations();
        if (ann.length != 0) {
            StringBuilder annotation = new StringBuilder(" Annotations : ");
            for (Annotation a : ann){
                annotation.append(a.toString());
                annotation.append(" ");
            }
            System.out.println(TAG + annotation.toString());
        } else {
            System.out.println(TAG +  " -- No Annotations -- ");
        }
    }

    private static void printAncestor(Class<?> c, List<Class> l) {
        Class<?> ancestor = c.getSuperclass();
        if (ancestor != null) {
            l.add(ancestor);
            printAncestor(ancestor, l);
        }
    }
}
