import java.awt.Rectangle;

public class Paddle {
    int x, y, width, height;
    Rectangle boundingBox;

    public Paddle(int x, int y, int width, int height) {
        this. x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(this.x, this.y, this.width, this.height);
    }

    public void moveX(int x) {
        this.x = x;
        this.boundingBox.x = x;
    } 
}
