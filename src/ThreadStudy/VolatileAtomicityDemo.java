package ThreadStudy;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 因为volatile关键字无法保证变量的原子性，所以最后得到的值实际上是<=2500的
 * 若要保证变量inc的原子性，可以在increase方法上加synchronized关键字，或者使用AtomicInteger将inc变量包装起来
 */
public class VolatileAtomicityDemo {

//    private volatile static int inc = 0;

    private static AtomicInteger inc = new AtomicInteger(0);

    public void increase() {
//        inc++;
        inc.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        VolatileAtomicityDemo volatileAtomicityDemo = new VolatileAtomicityDemo();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(()->{
                for (int j = 0; j < 500; j++) {
                    volatileAtomicityDemo.increase();
                }
            });
        }
        Thread.sleep(1500);
        System.out.println(inc.get());
        threadPool.shutdown();
    }
}
