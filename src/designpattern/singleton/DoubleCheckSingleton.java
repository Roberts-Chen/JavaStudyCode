package designpattern.singleton;

/**
 * 双重检查单例模式
 * 防止了多线程环境下，得到的对象不唯一的情况
 */
public class DoubleCheckSingleton {
    private volatile static String ID;

    public static String getID() {
        if (ID == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (ID == null) {
                    ID = "hahahahaha";
                }
            }
        }
        return ID;
    }
}
