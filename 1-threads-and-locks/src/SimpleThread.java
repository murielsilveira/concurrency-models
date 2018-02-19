public class SimpleThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            public void run() {
                System.out.println("Hello from other thread");
            }
        };

        thread.start();

        // hint to scheduler that the current thread
        // is going to finish soon, this means that
        // the scheduler will try to start other thread
        // sooner than it would normally
        Thread.yield();

        System.out.println("Hello from main thread");

        // waits for the thread to finish,
        // it happens when the run method returns
        thread.join();

        // Conclusion:
        // in theory the prints from the thread and
        // the main thread should show up in different
        // order around 50/50 percent of the time
    }
}
