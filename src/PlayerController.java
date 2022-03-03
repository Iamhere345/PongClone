import java.awt.event.KeyEvent;

public class PlayerController {
    public Rect rect;
    public KL KeyListener;

    public PlayerController(Rect rect,KL KeyListener) {
        this.rect = rect;
        this.KeyListener = KeyListener;
    }

    public PlayerController(Rect rect) {
        this.rect = rect;
        this.KeyListener = null;
    }

    public void update(double dt) {
        if (KeyListener != null) {
            if (KeyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
                moveDown(dt);
            } else if (KeyListener.isKeyPressed(KeyEvent.VK_UP)) {
                moveUp(dt);
            }
        }
    }

    public void moveUp(double dt) {
        if ((rect.y - constants.PADDLE_SPEED * dt) > constants.TOOLBAR_HEIGHT) {
            this.rect.y -= constants.PADDLE_SPEED * dt;
        }
    }

    public void moveDown(double dt) {
        if ((this.rect.y + constants.PADDLE_SPEED * dt) + rect.height < constants.SCREEN_HEIGHT - constants.INSETS_BOTTOM) {
            this.rect.y += constants.PADDLE_SPEED * dt;
        }
    }

}
