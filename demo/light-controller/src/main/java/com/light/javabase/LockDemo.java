package com.light.javabase;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author light
 * @Date 2023/6/25
 * @Desc 锁
 * 可重入锁:又称递归锁，当一个外层函数获得可重入锁后，内层函数可以直接获取该锁的控制权(可重入锁有：synchronized，ReentrantLock)
 * 可重入锁中又分为：公平锁和非公平锁、读写锁
 * 乐观锁，悲观锁、自旋锁、互斥锁）
 **/
public class LockDemo implements Runnable {

    ReentrantLock lock = new ReentrantLock();
    // 外层函数先获取了所有，set 函数内部又再获取锁，如果不是可重入锁，则会产生死锁；
    public synchronized void get(){
        lock.lock();
        System.out.println("外层锁：" + Thread.currentThread().getId());
        set();
        lock.unlock();
    }

    public synchronized void set(){
        lock.lock();
        System.out.println("内层锁：" + Thread.currentThread().getId());
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        LockDemo entry = new LockDemo();
        new Thread(entry).start();
        new Thread(entry).start();
    }
}

class ReadWriteLock {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();
    private int num = 0;

    public void read() {
        int cnt = 0;
        while (cnt++ < 3) {
            try {
                readLock.lock();
                System.out.println(Thread.currentThread().getId() + " start to read");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId() + " reading " + num);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }
    }

    public void write() {
        int cnt = 0;
        while (cnt++ < 3) {
            try {
                writeLock.lock();
                System.out.println(Thread.currentThread().getId() + " start to write");
                Thread.sleep(1000);
                num = (int) (Math.random() * 10);
                System.out.println(Thread.currentThread().getId() + " writing " + num);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReadWriteLock();
        for (int i = 0; i < 3; i++) {
            new ReadThread(readWriteLock).start();
            new WriteThread(readWriteLock).start();

        }
    }
}

class ReadThread extends Thread{
    private ReadWriteLock readWriteLock;
    public ReadThread(ReadWriteLock readWriteLock){
        this.readWriteLock = readWriteLock;
    }
    public void run(){
        readWriteLock.read();
    }
}

class WriteThread extends Thread{
    private ReadWriteLock readWriteLock;
    public WriteThread(ReadWriteLock readWriteLock){
        this.readWriteLock = readWriteLock;
    }
    public void run(){
        readWriteLock.write();
    }
}

