import java.awt.Rectangle;

public class Ball {
    int x, y, dim, xDir = 1, yDir = 1;
    Rectangle boundingBox;

    public Ball(int x, int y, int dim) {
        this.x = x;
        this.y = y;
        this.dim = dim;
        this.boundingBox = new Rectangle(this.x, this.y, this.dim, this.dim);
    }

    public void bounceX() {
        xDir *= -1;
    }

    public void bounceY() {
        yDir *= -1;
    }

    public void moveBall() {
        this.x += xDir;
        this.y += yDir;
        this.boundingBox.x += xDir;
        this.boundingBox.y += yDir;
    }
}
