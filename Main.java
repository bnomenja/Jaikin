public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Screen screen = new Screen(manager);

        for (;;) {
            screen.render();
            manager.update();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}