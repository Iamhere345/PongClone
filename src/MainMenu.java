import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame implements Runnable {

    public Graphics2D g2;
    public KL KeyListener = new KL();
    public ML mouseListener = new ML();
    public Text startGame, exitGame, title;
    public boolean isRunning = true;

    public MainMenu() {
        this.setSize(constants.SCREEN_WIDTH, constants.SCREEN_HEIGHT);
        this.setTitle(constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(KeyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.startGame = new Text("Start Game", new Font("Courier", Font.PLAIN, 50), constants.SCREEN_WIDTH / 2.0 - 140, constants.SCREEN_HEIGHT / 2.0, Color.WHITE);
        this.exitGame = new Text("Exit", new Font("Courier", Font.PLAIN, 50), constants.SCREEN_WIDTH / 2.0 - 80, constants.SCREEN_HEIGHT / 2.0 + 60, Color.WHITE);
        this.title = new Text("Ping: A Ripoff Of Pong", new Font("Courier", Font.PLAIN, 50), constants.SCREEN_WIDTH / 2.0 - 315, 225, Color.WHITE);

        g2 = (Graphics2D) this.getGraphics();
    }

    public void update(double dt) {

        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);

        //System.out.println(Math.round(1 / dt) + "fps");
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, constants.SCREEN_WIDTH, constants.SCREEN_HEIGHT);

        startGame.draw(g2);
        exitGame.draw(g2);
        title.draw(g2);

        if (mouseListener.getMouseX() > startGame.x && mouseListener.getMouseX() < startGame.x + startGame.width &&
                mouseListener.getMouseY() > startGame.y - startGame.height / 2 && mouseListener.getMouseY() < startGame.y + startGame.height / 2.0) {
            startGame.colour = new Color(164, 164, 164);

            if (mouseListener.IsMousePressed()) {
                Main.changeState(true);

            }
        } else {
            startGame.colour = Color.white;
        }

        if (mouseListener.getMouseX() > exitGame.x && mouseListener.getMouseX() < exitGame.x + exitGame.width &&
                mouseListener.getMouseY() > exitGame.y - exitGame.height / 2 && mouseListener.getMouseY() < exitGame.y + exitGame.height / 2.0) {
            exitGame.colour = new Color(164, 164, 164);

            if (mouseListener.IsMousePressed()) {
                Main.killall();
            }
        } else {
            exitGame.colour = Color.white;
        }
    }

    public void stop() {
        isRunning = false;
    }

    public void run() {
        double lastFrameTime = 0.0;
        while (isRunning) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);

            try {
                Thread.sleep(20);
            } catch (Exception e) {

            }
        }
        this.dispose();
    }
}
