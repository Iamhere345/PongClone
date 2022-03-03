public class Main {

    public static boolean state = false;
    public static Thread mainThread;
    public static MainMenu menu;
    public static Window window;

    public static void main(String[] args) {
        //Window window = new Window();
        menu = new MainMenu();

        mainThread = new Thread(menu);
        mainThread.start();
    }

    public static void changeState(boolean newState) {
        if (newState && !state) {
            menu.stop();
            window = new Window();

            mainThread = new Thread(window);
            mainThread.start();
        } else if (!newState && state) {
            window.stop();
            menu = new MainMenu();

            mainThread = new Thread(menu);
            mainThread.start();
        }
        state = newState;
    }

    public static void killall() {
        if (window != null) {
            window.stop();
        }
        if (menu != null) {
            menu.stop();
        }
    }
}
