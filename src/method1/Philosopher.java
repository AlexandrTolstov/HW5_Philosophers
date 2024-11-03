package method1;

public class Philosopher implements Runnable{

    private Object leftFork;
    private Object rightFork;
    int num; //Количество раз которое философ поест

    public Philosopher(Object leftFork, Object rightFork){
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
                synchronized (leftFork){
                    doAction(": Поднял левую вилку");
                    synchronized (rightFork){
                        doAction(": Поднял правую вилку - ест; Поел: " + (startNum - num + 1) + " раза");
                        doAction(": Опустил правую вилку");
                    }
                    doAction(": Опустил левую вилку и опять думает");
                    num--;

                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
