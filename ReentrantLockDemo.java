public class ReentrantLockDemo {
    private Object lock = new Object();

    public void method() {
        synchronized (lock) {
            System.out.println("first time get lock");
            synchronized (lock) {
                System.out.println("second time get lock");
                synchronized (lock) {
                    System.out.println("third time get lock");
                }
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        demo.method();
    }
}                                                                                                                                                                                                                                                                                                                                                                                                                                                          