package com.senlainc.git_courses.java_training.petushok_valiantsin.first_task;

public class FirstTask implements Runnable {

    public FirstTask() {
        super();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            if(Thread.currentThread().getName().equals("First thread")) {
                waitTime();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void waitTime() throws InterruptedException {
        wait();
    }
}
