public class CountingWithMutualExclusion {
    public static void main(String[] args) throws InterruptedException {
        class Counter {
            private int count = 0;

            // try removing the synchronized
            private synchronized void increment() {
                ++count;
            }

            private int getCount() {
                return count;
            }
        }

        final Counter counter = new Counter();

        class CountingThread extends Thread {
            public void run() {
                for (int i = 0; i < 10000; ++i) {
                    counter.increment();
                }
            }
        }

        CountingThread t1 = new CountingThread();
        CountingThread t2 = new CountingThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.getCount());

        // Conclusion:
        // without synchronized:
        //  race conditions will happen
        //  between the threads when incrementing the count
        //  this will result in a final value that will
        //  vary from execution to execution
        //  I got: 13546, 12236, 11570
        // with synchronized (lock/mutex):
        //  the access to the increment method
        //  is blocked when one thread is executing
        //  it' body, the other thread trying to
        //  access it will wait until the other finishes
        //  the execution and the lock becomes free,
        //  this results in the final value to be 20.000
    }
}
