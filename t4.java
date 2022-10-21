class Queue {
    static int a = 4;
   Queue(){}
    public synchronized void produce() {
            if (Queue.a == 0) {
                Queue.a += 4;
                System.out.println("Producer produces 4");
                notify();
            }
            else
            {
                try {
                    wait();
                } catch (Exception e) {
                }
            }
    }
    public synchronized void consume() {
        while(Queue.a == 0) {
            try {
                notify();
                wait(3000);
            } catch (Exception e) {
            }
        }
            Queue.a--;
            System.out.println("Consumer Consuming " + Queue.a);
        }
    }


class Producer extends Thread{
    Queue q;
    Producer(Queue q){this.q=q;}
    public void run(){
        while(true)
        {
           q.produce();
        try{Thread.sleep(3000);}catch (Exception e){}
    }
    }
}
class Consumer extends Thread {
    Queue q;

    Consumer(Queue q) {
        this.q = q;
    }

    public void run() {
        while (true) {
            q.consume();
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }
        }
    }
}
public class dp {
    public static void main(String[] args) throws InterruptedException {
       Queue q=new Queue();
        Producer p=new Producer(q);
       Consumer c=new Consumer(q);
       p.start();
       c.start();
       p.join();
       c.join();
    }
}
