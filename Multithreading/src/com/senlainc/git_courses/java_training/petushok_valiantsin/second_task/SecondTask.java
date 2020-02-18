package com.senlainc.git_courses.java_training.petushok_valiantsin.second_task;

public class SecondTask extends Thread {
    private SecondTask nextThread;
    private static int counter = 10;

    public SecondTask() {

    }

    public SecondTask(Thread thread) {
        setName(thread.getName());
    }

    public SecondTask(String threadName, SecondTask nextThread) {
        setName(threadName);
        this.nextThread = nextThread;
        nextThread.nextThread = this;
    }

    @Override
    public void run() {
        if(counter == 0 || nextThread == null) {
            return;
        }
        counter--;
        System.out.println(Thread.currentThread().getName());
        nextThread = new SecondTask(nextThread.getName(), this);
        nextThread.start();
        try {
            nextThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
