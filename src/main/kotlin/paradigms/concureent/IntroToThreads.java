package paradigms.concureent;

public class IntroToThreads {


    public static void startThreadPooling() {
        for (int i = 0; i < 2; i++) {
            int finalT = i;
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    System.out.println("Thread" + finalT + ": " + j);
                }
            }
            ).start();

        }
    }

    public static void main(String[] args) {


        startThreadPooling();
    }
}
