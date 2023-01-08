package org.example;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import static org.apache.commons.io.FileUtils.copyURLToFile;
import static org.apache.commons.io.FileUtils.openOutputStream;

public class WebDownloader {

        //下载方法
        public static void downloader(String url,String path){
            try {
                 copyURLToFile(new URL(url),new File(path));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IO异常，downloader方法出现异常"+Thread.currentThread().getName());
            }
        }

        public static void myDownloader(String url,String path,String banngou) {
            try {
//                ProgressBar progressBar = new ProgressBar();
                URL urlObject= new URL(url);
                URLConnection urlConnection=urlObject.openConnection();
                urlConnection.connect();
                InputStream stream = urlConnection.getInputStream();
                int totalSize = urlConnection.getContentLength();
                System.out.println("开始下载"+banngou+" 大小："+totalSize);
                OutputStream out = openOutputStream(new File(path));
                int count;
                int n ;
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                for(count = 0; -1 != (n = stream.read(buffer)); count += n) {
                    out.write(buffer, 0, n);
//                    progressBar.show(count,totalSize);
                }
                stream.close();
                out.close();
                System.out.println(banngou+":完成下载");
            }catch (IOException e){
                e.printStackTrace();
                System.out.println("异常");
            }
        }

}
