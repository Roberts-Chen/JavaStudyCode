package relfect;

import sort.BubbleSort;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class RelfectExp {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        // 1.通过反射获取BubbleSort的Class对象
        Class<?> klass = Class.forName("sort.BubbleSort");
        // 2.获取BubbleSort的构造器
        Constructor<?> constructor = klass.getConstructor();
        // 3.通过构造器调用newInstance()方法来创建一个实例
        BubbleSort sort = (BubbleSort) constructor.newInstance();
        int[] nums = {2, 3, 1, 5, 4, 7, 9, 8, 6};
        int[] sorted = sort.bubbleSort(nums);
        System.out.println(Arrays.toString(sorted));
    }
}
