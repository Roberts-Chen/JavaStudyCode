package jvm.heapspace;

import java.io.Serializable;

public class HeapSpaceTest extends Object implements Comparable<String>, Serializable {

    public int num = 10;

    private static String str = "测试方法的内部结构";

    public void test1() {
        int count = 20;
        System.out.println("count = " + count);
    }

    public static int test2(int cal) {
        int result = 0;
        try {
            int value = 30;
            result = value / cal;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        builder.append("sss");
        System.out.println(builder.reverse());
    }
}
