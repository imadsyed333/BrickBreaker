import javax.swing.JFrame;

public class BrickBreaker {
    public static void main(String[] args) {
        JFrame game = new JFrame();
        game.setBounds(0, 0, 1000, 1000);
        Gameplay mechanics = new Gameplay();
        game.setTitle("Brick Breaker");
        game.setResizable(false);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.add(mechanics);
        game.setVisible(true);
        mechanics.initializeGame();
    }
}
