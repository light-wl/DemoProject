package com.light.javabase;

import java.util.LinkedList;

/**
 * @Author light
 * @Date 2023/7/16
 * @Desc 使用 wait 和 notifyAll 实现生产者和消费者
 **/
public class ProducerConsumerThread {
    public static void main(String[] args) {
        LinkedList<Integer> buffer = new LinkedList<>();
        int capacity = 10;

        Producer producer = new Producer(buffer, capacity, 20);
        Consumer consumer = new Consumer(buffer, capacity);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}


class Producer implements Runnable {
    private LinkedList<Integer> buffer;
    private int capacity;
    private int num;

    public Producer(LinkedList<Integer> buffer, int capacity, int num) {
        this.buffer = buffer;
        this.capacity = capacity;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            try {
                synchronized (buffer) {
                    // 如果缓冲区已满，则等待消费者消费数据
                    while (buffer.size() == capacity) {
                        buffer.wait();
                    }

                    // 生产数据并加入队列
                    int value = i + 1;
                    buffer.addLast(value);
                    System.out.println("生产者生产了：" + value);

                    // 通知等待的消费者线程
                    buffer.notifyAll();
                }

                // 模拟生产数据需要时间
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private LinkedList<Integer> buffer;
    private int capacity;

    public Consumer(LinkedList<Integer> buffer, int capacity) {
        this.buffer = buffer;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (buffer) {
                    // 如果缓冲区已空，则等待生产者生产数据
                    while (buffer.size() == 0) {
                        buffer.wait();
                    }

                    // 如果缓冲区已满，则等待生产者生产数据
                    while (buffer.size() == capacity) {
                        buffer.wait();
                    }

                    // 缓冲区非空，消费数据并从队列中移除
                    int value = buffer.removeFirst();
                    System.out.println("消费者消费了：" + value);

                    // 通知等待的生产者线程
                    buffer.notifyAll();
                }

                // 模拟消费数据需要时间
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


