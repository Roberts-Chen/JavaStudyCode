package ThreadStudy;

import java.util.concurrent.locks.ReentrantLock;

public class FairLockDemo {
    // 设置为公平锁
    private static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        FairLockDemo demo = new FairLockDemo();
        Thread threadA = new Thread(() -> {
            demo.method();
        }, "ThreadA 1");
        Thread threadB = new Thread(() -> {
            demo.method();
        }, "ThreadB 2");
        Thread threadC = new Thread(() -> {
            demo.method();
        }, "ThreadC 3");
        Thread threadD = new Thread(() -> {
            demo.method();
        }, "ThreadD 4");
        Thread threadE = new Thread(() -> {
            demo.method();
        }, "ThreadE 5");
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
        threadE.start();
    }

    public void method() {
        try {
            Thread.sleep(3000 + Integer.valueOf(Thread.currentThread().getName().substring(8)));
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获取锁……");
            System.out.println(Thread.currentThread().getName() + "进入休眠……");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
