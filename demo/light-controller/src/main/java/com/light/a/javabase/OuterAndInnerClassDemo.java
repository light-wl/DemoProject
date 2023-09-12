package com.light.a.javabase;

/**
 * @Author light
 * @Date 2023/4/20
 * @Desc 外部类和内部类的使用
 * 成员内部类、局部内部类、匿名内部类、静态内部类
 **/
interface Dao {
    void show();
}

public class OuterAndInnerClassDemo {
    String name = "外部类的类名";
    static String type = "外部类的type属性";

    public static void show() {
        System.out.println("掉用外部类中的show方法");
    }

    public void print() {
        System.out.println("调用外部类中的打印方法");
    }

    /**
     * 成员内部类
     * 1.成员内部类可以无条件访问外部类的所有成员属性和成员方法（包括private成员和静态成员）；
     * 2.同名的属性名/方法名访问外部类时 → 外部类.this.成员名
     * 成员内部类是依附外部类而存在的，也就是说，如果要创建成员内部类的对象，前提是必须存在一个外部类的对象。
     * 所以在外部类访问内部类的时候必须先实例化外部类对象
     * （1.成员内部类可以使用四种权限修饰符进行修饰；2.成员内部类中不能书写静态变量和方法。）
     * (四种权限修饰符：public（公有的） >protected（受保护的） > (default)（缺省/默认的） > private(私有的))；
     */
    public class Inner {
        //static double weight = 1.8;  //成员内部类中不能使用static修饰变量和方法
        String name = "内部类的类名";

        public void innerShow() {
            //成员内部类可以直接访问外部类的属性和方法
            show();
            print();
            System.out.println(type);
            System.out.println("我是：" + name);
            //进行特指访问时 使用类名.this.变量名进行访问
            System.out.println("我是：" + OuterAndInnerClassDemo.this.name);

        }
    }

    /**
     * 静态内部类
     * 1.静态内部类也是定义在另一个类里面的类，只不过在类的前面多了一个关键字static;
     * 2.静态内部类是不需要依赖于外部类的，这点和类的静态成员属性有点类似，并且它不能使用外部类的非static成员变量或者方法;
     * 3.静态内部类中即能声明静态成员也可以声明非静态成员。
     * */
    public static class InnerStatic {
        //四种权限修饰符可以修饰静态内部类
        public String name = "静态内部类的类名";
        static double weight = 1.8;
        String type = "静态内部类的type属性";

        public void show() {
            System.out.println("我是：" + weight);
            System.out.println("我是：" + type);
            System.out.println("我是：" + name);
            //System.out.println("我是：" + Static.type);//静态内部类中不能访问外部类非静态成员
            System.out.println("我是：" + OuterAndInnerClassDemo.type);
        }
    }

    /**
     * 局部内部类（感觉使用场景很少啊，谁会这么用）
     * 编写在方法的内部的类称之为局部内部类
     * 局部内部类不可使用权限修饰符 静态修饰符进行修饰 同局部变量相同
     * 局部内部类与局部变量使用范围一样 在此方法内部
     * 局部内部类可以直接访问方法中的属性 重名时使用参数传递完成访问
     */
    public void demo() {
        class Inner {
            //局部内部类 可以访问方法外部类中属性和方法
            final String name = "局部类的类名";

            public void showInner(String name) {
                show();
                print();
                System.out.println("我是：" + type);
                System.out.println("我是：" + this.name);
                System.out.println("我是：" + name);
                System.out.println("我是：" + OuterAndInnerClassDemo.this.name);
            }
        }
        //局部内部类 创建对象 要在方法内部 局部内部类的外部声明
        Inner inner = new Inner();
        inner.showInner(name);
    }

    /**
     * 匿名内部类(感觉这也用不到啊)
     */
    public void callInner() {
        // 接口关系下的匿名内部类
        new Dao() {
            //实现子类 但是没有名字 所以叫匿名内部类
            @Override
            public void show() {
                System.out.println("接口方法...");
            }
        }.show();
    }

    public static void main(String[] args) {
        /**
         * 成员内部类对象的创建步骤
         * 1.第一步需要实例化外部类对象
         * 2.第二步正常实例化内部类对象 但是new关键字要改成 外部类对象名.new
         * OuterAndInnerClassDemo outer = new OuterAndInnerClassDemo();
         * Inner inner = outer.new Inner();
         * 合并成一步也可以：
         * OuterAndInnerClassDemo.Inner inner = new OuterAndInnerClassDemo().new Inner();
         */
        OuterAndInnerClassDemo outer = new OuterAndInnerClassDemo();
        Inner inner = outer.new Inner();
        inner.innerShow();

        //局部内部类
        outer.demo();

        //匿名内部类
        outer.callInner();
    }
}
