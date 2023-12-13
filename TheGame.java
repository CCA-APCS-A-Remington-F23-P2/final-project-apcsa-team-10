import javax.swing.JFrame;
import java.awt.Component;
import java.util.Scanner;

public class TheGame extends JFrame {
  private static int WIDTH;
  private static int HEIGHT;

  public TheGame() {
    super("CCA: The Life");
    WIDTH = 600;
    HEIGHT = 600;

    setSize(WIDTH, HEIGHT);

    Runner game = new Runner(WIDTH, HEIGHT);

    ((Component) game).setFocusable(true);
    getContentPane().add(game);

    setVisible(true);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String args[]) {
    TheGame run = new TheGame();
  }

}
