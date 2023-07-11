package com.light.designpattern.creates;

import com.light.model.UserInfo;

import javax.annotation.PostConstruct;

/**
 * @Author light
 * @Date 2023/3/21
 * @Desc 单例模式，饿汉式，懒汉式，双检锁，静态内部类
 *
 * 问：单例模式是指线程内只允许创建一个对象，还是指进程内只允许创建一个对象？
 * 答：答案是后者，也就是说，单例模式创建的对象是进程唯一的。
 * 进程之间是不共享地址空间的，如果我们在一个进程中创建另外一个进程（比如，代码中有一个 fork() 语句，进程执行到这条语句的时候会创建一个新的进程），
 * 操作系统会给新进程分配新的地址空间，并且将老进程地址空间的所有内容，重新拷贝一份到新进程的地址空间中，这些内容包括代码、数据（比如 user 临时变量、User 对象）。
 *
 * 问：如何实现线程唯一的单例？
 * 答：ConcurrentHashMap<Long, IdGenerator>根据不同线程，保存不同的实例
 *
 * 问：如何实现集群环境下的单例？
 * 答：具体来说，我们需要把这个单例对象序列化并存储到外部共享存储区（比如文件）。进程在使用这个单例对象的时候，
 * 需要先从外部共享存储区中将它读取到内存，并反序列化成对象，然后再使用，使用完成之后还需要再存储回外部共享存储区。
 *
 *
 * 问：在你所熟悉的编程语言的类库中，有哪些类是单例类？又为什么要设计成单例类呢？
 * 答：在某个功能使用前需要初始化，且只需要初始化一次即可。比如：配置信息类、连接池类、ID 生成器类。
 *
 * 应用：
 * 1、Spring 产生 Bean 的方式，默认是单例的；
 *
 *
 *
 * 缺点：1. 单例对 OOP 特性的支持不友好：如果需要ID生成器需要分类，则需要修改所有用到的地方；
 * 2. 单例会隐藏类之间的依赖关系：单例类不需要显示创建、不需要依赖参数传递，在函数中直接调用就可以了。
 * 3. 单例对代码的扩展性不友好：
 **/
public class SingleDesignPattern {
    private static volatile UserInfo user;

    /**
     * 饿汉式
     * SpringBoot 中对于饿汉式的应用是使用注解 @PostConstruct
     * 初始化顺序：当 springboot 按照顺序初始化Bean实例时，如果一个 bean 所在的类或者接口存在 @PostConstruct 注解，
     * springboot 就会在执行完这个 bean 的构造方法之后执行标记有 @PostConstruct 注解的方法，然后实例化下一个 bean。
     * 注意：如果初始化的时候调用了另一个类的实例，但是实例化却在这个 bean 之后，可能会报空指针异常；
     * 优点：1-如果初始化耗时长，那我们最好不要等到真正要用它的时候，才去执行这个耗时长的初始化过程，这会影响到系统的性能；
     * 2-如果实例占用资源多，按照 fail-fast 的设计原则（有问题及早暴露），那我们也希望在程序启动时就将这个实例初始化好；
     * 缺点：1-不支持延迟加载，如果用不到，初始化则浪费了资源；
     */
    @PostConstruct
    public void init() {
        user = new UserInfo();
    }

    /**
     * 懒汉式
     * 优点：支持延迟加载
     * 缺点：懒汉式有性能问题，不支持高并发。我们给 getUser() 这个方法加了一把大锁（synchronized），导致这个函数的并发度很低。量化一下的话，
     * 并发度是 1，也就相当于串行操作了。而这个函数是在单例使用期间，一直会被调用。如果这个单例类偶尔会被用到，
     * 那这种实现方式还可以接受。但是，如果频繁地用到，那频繁加锁、释放锁及并发度低等问题，会导致性能瓶颈，
     * 这种实现方式就不可取了。
     */
    public static synchronized UserInfo getUser1() {
        if (user == null) {
            user = new UserInfo();
        }
        return user;
    }

    /**
     * 双重检测
     * 优点：既支持延迟加载、又支持高并发的单例实现方式；
     * 注意：CPU 指令重排序可能导致在 IdGenerator 类的对象被关键字 new 创建并赋值给 instance 之后，还没来得及初始化（执行构造函数中的代码逻辑），
     * 就被另一个线程使用了。这样，另一个线程就使用了一个没有完整初始化的 IdGenerator 类的对象。要解决这个问题，我们只需要给 instance 成员变量
     * 添加 volatile 关键字来禁止指令重排序即可。
     */
    public static UserInfo getUser2() {
        if (user == null) {
            // 此处为类级别的锁
            synchronized (UserInfo.class) {
                if (user == null) {
                    user = new UserInfo();
                }
            }
        }
        return user;
    }

    /**
     * 静态内部类
     * 优点：实现方式比双检锁简单，且也具备既支持延迟加载、又支持高并发的单例实现方式；
     * 原理：SingletonHolder 是一个静态内部类，当外部类 IdGenerator 被加载的时候，并不会创建 SingletonHolder 实例对象。
     * 只有当调用 getInstance() 方法时，SingletonHolder 才会被加载，这个时候才会创建 instance。instance 的唯一性、
     * 创建过程的线程安全性，都由 JVM 来保证。所以，这种实现方法既保证了线程安全，又能做到延迟加载。
     */
    private static class SingletonHolder {
        private static final UserInfo user = new UserInfo();
    }

    public static UserInfo getUser3() {
        return SingletonHolder.user;
    }
}
