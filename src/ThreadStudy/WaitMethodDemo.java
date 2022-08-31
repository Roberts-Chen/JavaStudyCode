package ThreadStudy;

public class WaitMethodDemo {
    public static void main(String[] args) {
        Object lock = new Object();
        ThreadA threadA = new ThreadA(lock, "线程A");
        ThreadB threadB = new ThreadB(lock, "线程B");
        threadA.start();
        threadB.start();
        System.out.println("主线程执行完毕");
    }
}


class ThreadA extends Thread {
    private Object lock;
    private String name;

    public ThreadA(Object lock, String name) {
        this.lock = lock;
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("ThreadA方法开始执行……");
            try {
                System.out.println("ThreadA进入等待……");
                lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("ThreadA被唤醒……");
            System.out.println("ThreadA执行完毕……");
        }
    }
}

/**
 * 为什么wait()方法要放在Object类中，因为wait()、notify()、notifyAll()方法都是由当做对象锁的对象来调用的，与Thread类并无关系，
 * 每一个对象都有其对应的对象锁，所以放在Object类中再合适不过
 */
class ThreadB extends Thread {
    private Object lock;
    private String name;

    public ThreadB(Object lock, String name) {
        this.lock = lock;
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("线程B开始执行……");
            System.out.println("开始唤醒线程A……");
            lock.notify();
            System.out.println("线程B执行完毕……");
        }
    }
}

