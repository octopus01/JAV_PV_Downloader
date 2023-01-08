package org.example;


import java.util.stream.Stream;
@Deprecated
public class ProgressBar {
    private final StringBuffer builder= new StringBuffer();
    private final char incomplete = '░'; // U+2591 Unicode Character 表示还没有完成的部分
    private final char complete = '█'; // U+2588 Unicode Character 表示已经完成的部分

    private int progress;

    private int oldProgress;

    public ProgressBar() {
        create();
    }

    public void create(){
        Stream.generate(()->incomplete).limit(100).forEach(builder::append);
    }
    public void show(int n , int total) {
        StringBuilder completeV = new StringBuilder();
        progress =  n * 100 / total;
        if(progress<0) {
            complete();
            String progressBar = "\r" + builder;
            String percent = " " + progress + "%";
            System.out.print(progressBar + percent);
            return;
        }
        int i= Math.max(progress - oldProgress, 0);
        Stream.generate(()->String.valueOf(complete)).limit( i+1).forEach(completeV::append);
        builder.replace(oldProgress, (progress+1), completeV.toString());
        oldProgress=progress;
        String progressBar = "\r" + builder;
        String percent = " " + progress + "%";
        System.out.print(progressBar + percent);
    }
    public void complete(){
        StringBuilder completeV = new StringBuilder();
        Stream.generate(()->String.valueOf(complete)).limit( 100).forEach(completeV::append);
        progress=100;
        builder.replace(0,101, String.valueOf(completeV));
    }
}
