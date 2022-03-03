import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KL implements KeyListener {
    private boolean[] keyPressed = new boolean[128];

    @Override
    public void keyTyped(KeyEvent KeyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent KeyEvent) { keyPressed[KeyEvent.getKeyCode()] = true; }

    @Override
    public void keyReleased(KeyEvent KeyEvent) {
        keyPressed[KeyEvent.getKeyCode()] = false;
    }

    public boolean isKeyPressed(int KeyCode) {
        return keyPressed[KeyCode];
    }
}
