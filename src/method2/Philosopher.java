package method2;

import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable{

    private Semaphore leftFork;
    private Semaphore rightFork;
    int num; //Количество раз которое философ поест

    public Philosopher(Semaphore leftFork, Semaphore rightFork){
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        num = 3;
    }

    private void doAction(String action) throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep((int)(Math.random()*100));
    }

    @Override
    public void run() {
        int startNum = num;

        while (num > 0){
            try {
                doAction(": Думает");
                leftFork.acquire();
                doAction(": Поднял левую вилку");
                rightFork.acquire();
                doAction(": Поднял правую вилку - ест; Поел: " + (startNum - num + 1) + " раза");
                rightFork.release();
                doAction(": Опустил правую вилку");
                leftFork.release();
                doAction(": Опустил левую вилку и опять думает");
                num--;
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
