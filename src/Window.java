import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame implements Runnable {

    public Graphics2D g2;
    public KL KeyListener = new KL();
    public Rect playerOne, ai, ballRect;
    public Ball ball;
    public PlayerController playerController;
    public AIController aiController;
    public Text leftScoreText,rightScoreText;
    public boolean isRunning = true;

    public Window() {
        this.setSize(constants.SCREEN_WIDTH,constants.SCREEN_HEIGHT);
        this.setTitle(constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(KeyListener);
        constants.TOOLBAR_HEIGHT = this.getInsets().top;
        constants.INSETS_BOTTOM = this.getInsets().bottom;
        g2 = (Graphics2D)this.getGraphics();

        playerOne = new Rect(constants.HZ_PADDING,40,constants.PADDLE_WIDTH,constants.PADDLE_HEIGHT,constants.PADDLE_COLOR);
        playerController = new PlayerController(playerOne,KeyListener);

        ai = new Rect(constants.SCREEN_WIDTH - constants.PADDLE_WIDTH - constants.HZ_PADDING,40,constants.PADDLE_WIDTH,constants.PADDLE_HEIGHT,constants.PADDLE_COLOR);

        ballRect = new Rect(constants.SCREEN_WIDTH / 2,constants.SCREEN_HEIGHT / 2,constants.BALL_RADIUS,constants.BALL_RADIUS,constants.PADDLE_COLOR);

        aiController = new AIController(new PlayerController(ai),ballRect);

        leftScoreText = new Text("" + 0, new Font("Courier New", Font.PLAIN,constants.TEXT_SIZE),constants.TEXT_X_POS,constants.TEXT_Y_POS,Color.WHITE);
        rightScoreText = new Text("" + -1, new Font("Courier New", Font.PLAIN,constants.TEXT_SIZE),constants.SCREEN_WIDTH - constants.TEXT_X_POS - 32,constants.TEXT_Y_POS,Color.WHITE);

        ball = new Ball(ballRect,playerOne,ai,leftScoreText,rightScoreText);
    }

    public void update(double dt) {

        Image dbImage = createImage(getWidth(),getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage,0,0,this);

        //System.out.println(Math.round(1 / dt) + "fps");

        playerController.update(dt);
        aiController.update(dt);
        ball.update(dt);

    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,constants.SCREEN_WIDTH,constants.SCREEN_HEIGHT);

        leftScoreText.draw(g2);
        rightScoreText.draw(g2);
        playerOne.draw(g2);
        ai.draw(g2);
        ballRect.draw(g2);
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
        return;
    }
}
