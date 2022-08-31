package ThreadStudy;

public class SingletonDemo {
    public static void main(String[] args) {
        GetSingletonIntegerAndPrintThread thread = new GetSingletonIntegerAndPrintThread();
        for (int i = 0; i < 100; i++) {
            new Thread(thread, "Thread" + i).start();
        }
    }
}

class SingletonInteger {
    private volatile static Integer num;

    public SingletonInteger() {

    }

    public static Integer getSingletonInteger() {
        if (num == null) {
            synchronized (SingletonInteger.class) {
                if (num == null) {
                    num = new Integer(1000);
                }
            }
        }
        return num;
    }
}

class GetSingletonIntegerAndPrintThread implements Runnable {
    @Override
    public void run() {
        Integer singletonInteger = SingletonInteger.getSingletonInteger();
        System.out.println(singletonInteger);
    }
}
