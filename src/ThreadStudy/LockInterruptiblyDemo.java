package ThreadStudy;

import java.util.concurrent.locks.ReentrantLock;

//Interruptibly
public class LockInterruptiblyDemo {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        LockInterruptiblyDemo demo = new LockInterruptiblyDemo();
        Thread threadA = new Thread(() -> {
            demo.method();
        }, "ThreadA");
        Thread threadB = new Thread(() -> {
            demo.method();
        }, "ThreadB");
        threadA.start();
        Thread.sleep(100);
//        threadA.interrupt();
        threadB.start();
        Thread.sleep(1000);
        threadB.interrupt();
        if (threadB.isInterrupted()) {
            System.out.println("被中断");
        }
    }

    public void method() {
        try {
            System.out.println(Thread.currentThread().getName());
            lock.lockInterruptibly();
            // sleep()方法并不会释放锁，所以线程A一直占据着锁
            Thread.sleep(8000);
            for (long i = 0; i < 2000000000; i++) {
//                System.out.println(Thread.currentThread().getName() + "获取到锁  " + i);
            }
            System.out.println("未中断");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断");
            throw new RuntimeException(e);
        } finally {
            try {
                lock.unlock();
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + "尝试释放锁");
            }
        }
    }
}
