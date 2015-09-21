package bravura.sonata.buddy.quietperiod;

public class QuietPeriod<T> extends Thread {

    private static final int SLEEP_DURATION = 100;

    private volatile boolean threadRunning = true;
    private volatile boolean quietPeriodRunning;
    private final long quietPeriodDuration;
    private volatile long currentDelayStartTime;
    private final QuietPeriodListener listener;
    private T token;

    public QuietPeriod(QuietPeriodListener<T> listener, long quietPeriodDuration) {
        this.listener = listener;
        this.quietPeriodDuration = quietPeriodDuration;
    }

    @Override
    public void run() {
        while (threadRunning) {
            try {
                Thread.sleep(SLEEP_DURATION);
            } catch (InterruptedException e) {
                quietPeriodRunning = false;
            }
            long currentDelay = currentDelay();
            if (quietPeriodRunning && currentDelay >= quietPeriodDuration) {
                quietPeriodFinished();
            }
        }
    }

    public void restartQuietPeriod(T token) {
        currentDelayStartTime = System.nanoTime();
        this.token = token;
        quietPeriodRunning = true;
    }

    private long currentDelay() {
        return System.nanoTime() - currentDelayStartTime;
    }

    private void quietPeriodFinished() {
        quietPeriodRunning = false;
        T token = this.token;
        this.token = null;
        listener.quietPeriodFinished(token);
    }
}
