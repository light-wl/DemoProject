package com.light.javabase;

import com.alibaba.fastjson.JSON;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description
 * @Author light
 * @Date 2023/2/27 11:08
 **/
public class NIODemo {
    public void testOne(){
        Student student = new Student();
        student.setAge(11);
        student.setName("Tom");

         //直接输出，结果为：Student(id=123, name=张三, age=18)
        System.out.println(student);
       //转换为JSON格式输出，以下两种方法只有返回值不同
        Object objectJson = JSON.toJSON(student);
        String stringJSON = JSON.toJSONString(student);
        //结果为：{"name":"张三","id":"123","age":18}
        System.out.println(objectJson);
        //结果为：{"name":"张三","id":"123","age":18}
        System.out.println(stringJSON);
    }

    /**
     * 单向，只能读或者写
     */
    private static void buffer() {
        //创建一个Int型的buffer，大小为5。相当于创建了一个大小为5的int数组
        IntBuffer buffer = IntBuffer.allocate(5);

        //往buffer中添加数据
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(i * 2);
        }

        //buffer读写切换，之前为写数据，调用flip后切换为读
        buffer.flip();

        //读取数据
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }

    private static void fileChannel() throws IOException {
        //创建一个文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream("a.txt");

        //通过文件输出流得到一个FileChannel
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个buffer并写入数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("hello".getBytes());

        //反转，让指针指向数组开头
        buffer.flip();

        //将Buffer中数据写入FileChannel中
        fileChannel.write(buffer);
        fileOutputStream.close();
    }

    private static void server() throws IOException {
        //创建ServerSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到一个Selector对象
        Selector selector = Selector.open();
        //绑定一个端口6666
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);

        //把 serverSocketChannel 注册到 selector ，关心事件为：OP_ACCEPT，有新的客户端连接
        SelectionKey register = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println();
        //循环等待客户端连接
        while (true) {
            //等待1秒，如果没有事件发生，就返回
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }
            //如果返回的 > 0,表示已经获取到关注的事件
            // 就获取到相关的 selectionKey 集合，反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            //遍历 Set<SelectionKey>，使用迭代器遍历
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                //获取到SelectionKey
                SelectionKey key = keyIterator.next();
                //根据 key 对应的通道发生的事件，做相应的处理
                if (key.isAcceptable()) {//如果是 OP_ACCEPT，有新的客户端连接
                    //该客户端生成一个 SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功，生成了一个SocketChannel：" + socketChannel.hashCode());
                    //将SocketChannel设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到selector，关注事件为 OP_READ，同时给SocketChannel关联一个Buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isReadable()) {
                    //通过key，反向获取到对应的Channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取到该channel关联的Buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("from 客户端：" + new String(buffer.array()));
                }
                //手动从集合中移除当前的 selectionKey，防止重复操作
                keyIterator.remove();
            }
        }
    }

    private static void client() throws IOException {
        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞
        socketChannel.configureBlocking(false);
        //提供服务器端的IP和端口
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 6666);
        //连接服务器
        if (!socketChannel.connect(socketAddress)) { //如果不成功
            while (!socketChannel.finishConnect()) {
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其他工作。。。");
            }
        }

        //如果连接成功，就发送数据
        String str = "hello!!";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        //发送数据,实际上就是将buffer数据写入到channel
        socketChannel.write(byteBuffer);

    }

}
