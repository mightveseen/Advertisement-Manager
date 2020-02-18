package com.senlainc.git_courses.java_training.petushok_valiantsin;

import com.senlainc.git_courses.java_training.petushok_valiantsin.first_task.FirstTask;
import com.senlainc.git_courses.java_training.petushok_valiantsin.second_task.SecondTask;

public class Main {
    public static void main(String[] args) {
        try {
            Main main = new Main();
            System.out.println("First task:");
            main.firstTask();
            System.out.println("Second task:");
            main.secondTask();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void firstTask() throws InterruptedException {
        Thread firstThread = new Thread(new FirstTask(), "First thread");
        Thread secondThread = new Thread(new FirstTask(), "Second thread");
        /* NEW */
        System.out.println(firstThread.getState());
        /* RUNNABLE */
        firstThread.start();
        System.out.println(firstThread.getState());
        /* TIMED_WAITING */
        secondThread.start();
        System.out.println(firstThread.getState());
        /* WAITING */
        secondThread.join();
        System.out.println(firstThread.getState());
        /* TERMINATED */
        wakeUp(firstThread);
        firstThread.interrupt();
        System.out.println(firstThread.getState());
    }

    public synchronized void wakeUp(Thread thread) {
        thread.notify();
    }

    private void secondTask() throws InterruptedException {
        Thread firstThread = new Thread(new SecondTask(), "First thread");
        Thread secondThread = new Thread(new SecondTask(firstThread), "Second thread");
//        firstThread.start();
        secondThread.start();
    }
}
