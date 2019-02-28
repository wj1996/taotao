package com.wj.test;

import org.omg.CORBA.Current;

public class Demo02 implements Runnable{

    private  boolean flag  = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        this.setFlag(true);
    }

    public static void main(String[] args) {
        Demo02 demo02 = new Demo02();
        Thread thread = new Thread(demo02,"thread-1");
        thread.start();
        while(true){
            if(demo02.isFlag()){
                break;
            }
        }
    }
}


