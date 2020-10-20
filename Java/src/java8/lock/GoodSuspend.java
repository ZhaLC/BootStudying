package java8.lock;

/**
 * @ClassName GoodSuspend
 * @Description 好的挂起例子
 * @Author hanlei
 * @Date 2020/9/2 4:05 下午
 * @Version 1.0
 */
public class GoodSuspend {
    private static Object u = new Object();

    static class SuspendThread extends Thread {

        private volatile boolean suspendme = false;

        public void suspendMe() {
            suspendme = true;
        }

        public void resumeMe() {
            suspendme = false;
            synchronized (this) {
                notify();
            }
        }

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    while (suspendme) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (u) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("in SuspendThread");
                }
                Thread.yield();
            }
        }
    }

    static class ResumeThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (u) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("in ResumeThread");
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SuspendThread t1 = new SuspendThread();
        ResumeThread t2 = new ResumeThread();
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t1.suspendMe();
        System.out.println("suspend t1 2 sec");
        Thread.sleep(2000);
        System.out.println("resume t1");
        t1.resumeMe();
    }
}
