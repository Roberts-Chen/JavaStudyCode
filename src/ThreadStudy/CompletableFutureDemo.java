package ThreadStudy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CompletableFutureDemo {
    public static void main(String[] args) throws FileNotFoundException {
        // 通过字节缓冲输出流多线程读取文件
        String[] filePathArray = {"C:\\Users\\22577\\Desktop\\由浅入深Java并发.pdf", "C:\\Users\\22577\\Desktop\\xjpic.jpg", "C:\\Users\\22577\\Desktop\\ReentrantLockDemo.java"};

        //        BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\22577\\Desktop\\由浅入深Java并发.pdf");
        List<String> filePaths = Arrays.asList(filePathArray);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
//        List<CompletableFuture<Integer>> completableFutureList = filePaths.stream().map(filePath -> {
//            return CompletableFuture.supplyAsync(() -> {
//                BufferedInputStream bufferedInputStream;
//                BufferedOutputStream bufferedOutputStream;
//                try {
//                    byte[] buffer = new byte[1024];
//                    bufferedInputStream = new BufferedInputStream(Files.newInputStream(Paths.get(filePath)));
//                    bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get("D:\\" + filePath.substring(23))));
//                    while (bufferedInputStream.read(buffer) != -1) {
//                        bufferedOutputStream.write(buffer);
//                    }
//                    bufferedOutputStream.flush();
//                    bufferedInputStream.close();
//                    bufferedOutputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return 1;
//            }, executorService);
//        }).collect(Collectors.toList());
//        // 在所有任务完成之前，主线程先进行等待
//        CompletableFuture.allOf(
//                completableFutureList.toArray(new CompletableFuture[0])
//        ).join();
//        int sum = 0;
//        for (CompletableFuture<Integer> completableFuture : completableFutureList) {
//            sum += completableFuture.join();
//        }
//        if (sum == 3) {
//            System.out.println("文件复制完毕");
//        }
//        executorService.shutdown();
//        while (!executorService.isTerminated()) {
//
//        }
//        try {
//            byte[] buffer = new byte[1024];
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(Paths.get(filePathArray[0])));
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get("D:\\" + filePathArray[0].substring(23))));
//            while (bufferedInputStream.read(buffer) != -1) {
//                bufferedOutputStream.write(buffer);
//            }
//            bufferedOutputStream.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        List<MyRunnableForFileReadAndWrite> workers = filePaths.stream().map(filePath -> {
//            return new MyRunnableForFileReadAndWrite(filePath, filePath.substring(23));
//        }).collect(Collectors.toList());
//        for (MyRunnableForFileReadAndWrite worker : workers) {
//            executorService.execute(worker);
//        }
//        executorService.shutdown();
//        while (!executorService.isTerminated()) {
//
//        }

        new CompletableFutureDemo().inheritableThreadLocalDemo();
    }


    public void inheritableThreadLocalDemo() {
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("hello world!");
        new Thread(()->{
            System.out.println("原线程的threadLocal值为：" + threadLocal.get());
        }).start();
    }
}

class MyRunnableForFileReadAndWrite implements Runnable {

    private String inputFilePath;

    private String outputFilePath;

    private ThreadLocal<ArrayList<String>> pathList = new ThreadLocal<>();

    private ThreadLocal<String> outPath = new ThreadLocal<>();

    private ThreadLocal<String> inPath = new ThreadLocal<>();

    public MyRunnableForFileReadAndWrite(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        pathList.set(new ArrayList<>(Arrays.asList(inputFilePath, outputFilePath)));
    }

    @Override
    public void run() {
        try {
            inPath.set(inputFilePath);
            outPath.set(outputFilePath);
            pathList.set(new ArrayList<>(Arrays.asList(inputFilePath, outputFilePath)));
            byte[] buffer = new byte[1024];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(Paths.get(inputFilePath)));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(outputFilePath)));
            while (bufferedInputStream.read(buffer) != -1) {
                bufferedOutputStream.write(buffer);
            }
            bufferedOutputStream.flush();
            bufferedInputStream.close();
            bufferedOutputStream.close();
            System.out.println(pathList.get().toString() + "  " + inPath.get() + "  " + outPath.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
