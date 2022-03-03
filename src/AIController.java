public class AIController {
    public PlayerController playerController;
    public Rect ball;

    public AIController(PlayerController plrController, Rect ball) {
        this.playerController = plrController;
        this.ball = ball;
    }

    public void update(double dt) {
        playerController.update(dt);

        if (ball.y < playerController.rect.y && Math.random() >= constants.AI_DIFFICULTY) {
            playerController.moveUp(dt);
        } else if (ball.y + ball.height > playerController.rect.y + playerController.rect.height && Math.random() >= constants.AI_DIFFICULTY) {
            playerController.moveDown(dt);
        }
    }
}
