package ThreadStudy;

public class ReentrantLockDemo {
    private Object lock = new Object();

    public void method() {
        synchronized (lock) {
            System.out.println("第一次获取lock锁");
            synchronized (lock) {
                System.out.println("第二次获取lock锁");
                synchronized (lock) {
                    System.out.println("第三次获取lock锁");
                }
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        demo.method();
    }
}
