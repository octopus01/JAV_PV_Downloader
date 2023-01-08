package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        String line;
        System.out.println("是否开启代理 Y/N");
        line= scanner.nextLine();
        if(line.equals("Y")||line.equals("y")) {
            System.out.println("选择代理类型 1.http 2.sock");
            line=scanner.nextLine();
            if (line.equals("1")) {
                System.out.println("请输入代理端口：");
                line=scanner.nextLine();
                ProxySettings.http(line);
            }
            else if (line.equals("2")){
                System.out.println("请输入代理端口：");
                line=scanner.nextLine();
                ProxySettings.socks(line);
            }
        }
//        ProxySettings.socks("10808");
        System.out.println("请输入番号：如 ssis-557");
        JvmInfo jvmInfo = JvmInfo.getJvmInfo();

        List<String> banngous = new ArrayList<>();
        while (true){
            line = scanner.nextLine();
            if(line.equals("end")) break;
            else if (!line.matches("^[A-Za-z]{3,5}-[0-9]{1,3}$"))
                System.out.println("格式错误");
            else banngous.add(line);
        }
        System.out.println(banngous);
        int corePoolSize = jvmInfo.getCPU_CORES()*2;
        int maximumPoolSize = Math.max(banngous.size() + 1, corePoolSize);
        ThreadPoolExecutor pool= new ThreadPoolExecutor(corePoolSize,  maximumPoolSize,
                10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(4));
        try {
            for (String banngou : banngous) {
                    pool.execute(new Thread(() -> {
                        WebDownloader.myDownloader(
                                BanngouURL.getUrl(banngou),
                                "./video/" + banngou + "_pv.mp4",banngou);
                    }, banngou));
            }
        }finally {
            pool.shutdown();
        }
    }
}
