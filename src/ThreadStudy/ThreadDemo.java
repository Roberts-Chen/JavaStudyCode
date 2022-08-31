package ThreadStudy;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yuanfu
 * @date 2021/9/24 20:10
 */
public class ThreadDemo {
    public static String threadName = "A"; // 默认为A
    public static Lock lock = new ReentrantLock();
    public static Condition conA = lock.newCondition();
    public static Condition conB = lock.newCondition();
    public static Condition conC = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo obj = new ThreadDemo();
//        ExecutorService es = Executors.newFixedThreadPool(3);
//        // 线程开始执行的顺序和结果是无关的
//        es.execute(obj.new ThreadB());
//        es.execute(obj.new ThreadC());
//        es.execute(obj.new ThreadA());
//        // es.execute(new ThreadB());
//        // es.execute(new ThreadC());
//
//        // 单独运行test()方法时，当前线程需要暂停一段时间才能保证每个任务都能顺利输出10次；
//        // 如果把整个java项目启动运行起来，就不需要下面暂停当前线程这一步
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        es.shutdown();
        for (int i = 0; i < 100; i++) {
            Thread a = new Thread(obj.new ThreadD());
            Thread b = new Thread(obj.new ThreadE());
            Thread c = new Thread(obj.new ThreadF());
            a.start();
            b.start();
            c.start();
            a.join();
            b.join();
            c.join();
            Thread d = new Thread(obj.new ThreadG());
            d.start();
            d.join();
            System.out.println();
        }
    }

    class ThreadD implements Runnable {
        @Override
        public void run() {
            System.out.printf("A ");
        }
    }


    class ThreadE implements Runnable {
        @Override
        public void run() {
            System.out.printf("B ");
        }
    }


    class ThreadF implements Runnable {
        @Override
        public void run() {
            System.out.printf("C ");
        }
    }

    class ThreadG implements Runnable {
        @Override
        public void run() {
            System.out.printf("D");
        }
    }



    class ThreadA implements Runnable {

        @Override
        public void run() {
            for(int k=1; k<=10; k++) {
                lock.lock();
                try {
                    // 这里必须用while, 不断地尝试才行
                    while (!threadName.equals("A")) {
                        try {
                            // 在await即放弃锁前，就得先把其他可能阻塞的线程激活，避免出现所有线程都阻塞的状态
                            conB.signal();
                            conC.signal();
                            conA.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("A" + k);
                    threadName = "B";
                    conB.signal();
                } finally {
                    // 自己释放锁，就会进入同步队列；和调用await进入等待队列是不同的；前者释放锁后仍然可以加入锁竞争
                    lock.unlock();
                }
            }
        }
    }

    class ThreadB implements Runnable {

        @Override
        public void run() {
            for(int k=1; k<=10; k++) {
                lock.lock();
                try {
                    while (!threadName.equals("B")) {
                        try {
                            conC.signal();
                            conA.signal();
                            conB.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("B" + k);
                    threadName = "C";
                    conC.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class ThreadC implements Runnable {

        @Override
        public void run() {
            for(int k=1; k<=10; k++) {
                lock.lock();
                try {
                    while (!threadName.equals("C")) {
                        try {
                            conA.signal();
                            conB.signal();
                            conC.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("C" + k);
                    threadName = "A";
                    conA.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}



