import java.awt.Rectangle;

public class Brick {
    int x, y, width, height;
    Rectangle boundingBox;

    public Brick(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(this.x, this.y, this.width, this.height);
    }
}
