package com.tca.thread.communicate.join;

import java.util.Random;

/**
 * @author zhoua
 * @Date 2020/7/26
 */
public class JoinDemo {

    private static final Random RAND = new Random(10000);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("准备开会！");

        Meeting tom = new Meeting("tom");
        Meeting jerry = new Meeting("jerry");

        tom.start();
        jerry.start();
        tom.join();
        jerry.join();

        System.out.println("会议开始");


    }

    static class Meeting extends Thread {

        private String name;

        public Meeting(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(RAND.nextInt(10000));
                System.out.println(name + "准备就绪");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Jerry extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(RAND.nextInt(10000));
                System.out.println("Jerry准备就绪");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




}
