package com.wj.test;

public class MyThread implements Runnable{

    private boolean flag;


    public MyThread() {
        this.flag = true;
    }


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public static void main(String[] args) throws Exception{
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread,"myThread");
        thread.start();
        int sum = 0;
        /*for(int i = 0;i < 100000; i++){
            sum += i;
        }*/
        Thread.sleep(100);
        myThread.setFlag(false);
        System.out.println(Thread.currentThread().getName() + ":" + myThread.isFlag());
    }


    @Override
    public void run() {
        while(flag){
        }
        System.out.println("over");
    }
}

