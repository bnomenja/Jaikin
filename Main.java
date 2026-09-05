public class Main {
    public static void main(String[] args) {

        Manager manager = new Manager();
        Screen screen = new Screen(manager);

        final long stepIntervalMs = 700;

        long lastStepTime = System.currentTimeMillis();
        boolean wasAnimating = false;

        for (;;) {

            screen.render();

            boolean animating = manager.isAnimating();

            if (animating && !wasAnimating) {
                lastStepTime = System.currentTimeMillis();
            }

            if (animating) {

                long now = System.currentTimeMillis();

                if (now - lastStepTime >= stepIntervalMs) {
                    manager.nextStep();
                    lastStepTime = now;
                }
            }

            wasAnimating = animating;

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}