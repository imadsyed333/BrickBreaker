import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class Gameplay extends JPanel implements MouseMotionListener, ActionListener{
    int paddleX, paddleY, paddleWidth, paddleHeight, xGap, yGap, brickWidth, brickHeight, ballX, ballY, ballDim;
    ArrayList<Brick> bricks;
    boolean play;
    Image backgroundImage, paddleImage, brickImage, ballImage;
    Timer timer = new Timer(5, this);
    Ball ball;
    Paddle paddle;

    public Gameplay () {
        addMouseMotionListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void initializeGame() {
        paddleWidth = 100;
        paddleHeight = 20;
        paddleX = getWidth() / 2 - paddleWidth;
        paddleY = getHeight() - 100 - paddleHeight;
        paddle = new Paddle(paddleX, paddleY, paddleWidth, paddleHeight);
        brickHeight = 60;
        brickWidth = 100;
        xGap = 10;
        yGap = 20;
        bricks = new ArrayList<>();
        buildBricks();
        ballDim = 30;
        ballX = getWidth() / 2;
        ballY = getHeight() / 2;
        ball = new Ball(ballX, ballY, ballDim);
        play = true;
    }

    public void buildBricks() {
        for (int i = yGap; i < getHeight() / 2; i += yGap + brickHeight) {
            for (int j = xGap; j < getWidth(); j += xGap + brickWidth) {
                bricks.add(new Brick(j, i, brickWidth, brickHeight));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play) {
            ballMovement();
            repaint();
            if(bricks.size() == 0) {
                play = false;
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        backgroundImage = Toolkit.getDefaultToolkit().getImage("./src/background.jpg");
        paddleImage = Toolkit.getDefaultToolkit().getImage("./src/breaker.png");
        brickImage = Toolkit.getDefaultToolkit().getImage("./src/bricks.png");
        ballImage = Toolkit.getDefaultToolkit().getImage("./src/ball.png");

        g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        g2.drawImage(paddleImage, paddle.x, paddle.y, paddle.width, paddle.height, this);
        g2.drawImage(ballImage, ball.x, ball.y, ball.dim, ball.dim, this);
        for (Brick brick: bricks) {
            g2.drawImage(brickImage, brick.x, brick.y, brick.width, brick.height, this);
        }
        timer.start();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (play) {
            paddle.moveX(e.getX());
        }
    }

    public void ballMovement() {
        ArrayList<Brick> copyBricks = new ArrayList<>();
        copyBricks.addAll(bricks);
        if (ball.boundingBox.intersects(paddle.boundingBox)) {
            ball.bounceY();
        }
        else if (ball.x >= getWidth() - ball.dim || ball.x <= 0) {
            ball.bounceX();
        }
        else if (ball.y > paddle.y || ball.y <= 0) {
            ball.bounceY();
        }
        for (Brick brick: copyBricks) {
            if (ball.boundingBox.intersects(brick.boundingBox)) {
                ball.bounceY();
                bricks.remove(copyBricks.indexOf(brick));
            }
        }
        copyBricks = new ArrayList<>();
        ball.moveBall();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
}
