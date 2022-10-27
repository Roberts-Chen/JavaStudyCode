package jvm.stackspace;

public class StackTest {

    private static char CHAR;

    public static void main(String[] args) {
        StackTest stackTest = new StackTest();
        stackTest.methodA();

        char i = '\u0000';
        System.out.println(CHAR == i);

    }

    public void methodA() {
        int i = 10;
        int j = 20;
        methodB();
    }

    public void methodB() {
        int m = 30;
        int n = 40;
    }
    // int float double short byte boolean char long
    // boolean byte short int long float double char
}
