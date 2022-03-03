public class Ball {
    public Rect rect;
    public Rect leftPaddle,rightPaddle;
    public double vx,vy;
    public Text leftScoreText,rightScoreText;

    private boolean changedSpeed = false;

    public Ball(Rect rect,Rect leftPaddle,Rect rightPaddle, Text leftScoreText, Text rightScoreText) {
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.vx = constants.BALL_VX;
        this.vy = constants.BALL_VY;
        this.leftScoreText = leftScoreText;
        this.rightScoreText = rightScoreText;
    }

    public double calculateNewVelocityAngle(Rect paddle) {
        double relativeIntersectY = (paddle.y + paddle.height / 2.0) - (this.rect.y + (this.rect.height / 2.0));
        double normalIntersect = relativeIntersectY / (paddle.height / 2);
        double theta = normalIntersect * constants.MAX_ANGLE;

        return Math.toRadians(theta);
    }

    public void update(double dt) {

        if (Integer.parseInt(leftScoreText.text) % 2 == 0 && !changedSpeed) {
            if (vx > 0) {
                constants.BALL_VX += 1.5;
            } else if (vx < 0) {
                constants.BALL_VX -= 1.5;
            }
            changedSpeed = true;
        } else {
            changedSpeed = false;
        }

        if (vx < 0) {
            if (this.rect.x <= this.leftPaddle.x + this.leftPaddle.width && this.rect.x >= this.leftPaddle.x && this.rect.y >= this.leftPaddle.y && this.rect.y <= this.leftPaddle.y + this.leftPaddle.height) {
                double theta = calculateNewVelocityAngle(leftPaddle);
                double new_vx = Math.abs(Math.cos(theta) * constants.BALL_SPEED);
                double new_vy = -(Math.sin(theta) * constants.BALL_SPEED);
                double oldSign = Math.signum(vx);
                this.vx = new_vx * (-1.0 * oldSign);
                this.vy = new_vy;
            }
        } else if (vx > 0) {
            if (this.rect.x + this.rect.width >= this.rightPaddle.x && this.rect.x <= this.rightPaddle.x + this.rightPaddle.width && this.rect.y >= this.rightPaddle.y && this.rect.y <= this.rightPaddle.y + this.rightPaddle.height) {
                double theta = calculateNewVelocityAngle(rightPaddle);
                double new_vx = Math.abs(Math.cos(theta) * constants.BALL_SPEED);
                double new_vy = -(Math.sin(theta) * constants.BALL_SPEED);

                double oldSign = Math.signum(vx);
                this.vx = new_vx * (-1.0 * oldSign);
                this.vy = new_vy;
            }
        }

        if (vy > 0.0) {
            if (this.rect.y + this.rect.height > constants.SCREEN_HEIGHT) {
                this.vy *= -1;
            }
        } else if (vy < 0) {
            if (this.rect.y < constants.TOOLBAR_HEIGHT) {
                this.vy *= -1;
            }
        }

        if (this.rect.x + this.rect.width < leftPaddle.x) {
            int rightScore = Integer.parseInt(rightScoreText.text);
            rightScore++;
            rightScoreText.text = "" + rightScore;

            this.rect.x = constants.SCREEN_WIDTH / 2.0;
            this.rect.y = constants.SCREEN_HEIGHT / 2.0;
            this.vx = constants.BALL_VX;
            this.vy = constants.BALL_VY;

            constants.AI_DIFFICULTY -= 0.01;

            System.out.println("AI score");
            System.out.println("" + constants.AI_DIFFICULTY);

            if (rightScore >= constants.WIN_SCORE) {
                System.out.println("AI WINS!");
                Main.killall();
            }
        } else if (this.rect.x > rightPaddle.x + rightPaddle.width) {
            int leftScore = Integer.parseInt(leftScoreText.text);
            leftScore++;
            leftScoreText.text = "" + leftScore;

            this.rect.x = constants.SCREEN_WIDTH / 2.0;
            this.rect.y = constants.SCREEN_HEIGHT / 2.0;
            this.vx = constants.BALL_VX;
            this.vy = constants.BALL_VY;

            constants.AI_DIFFICULTY += 0.01;

            System.out.println("" + constants.AI_DIFFICULTY);

            if (leftScore >= constants.WIN_SCORE) {
                Main.killall();
            }
        }

        this.rect.x += vx * dt;
        this.rect.y += vy * dt;
    }



}