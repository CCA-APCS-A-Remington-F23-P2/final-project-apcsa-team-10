import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

public class Runner extends Canvas implements KeyListener, Runnable {
  public Student player;
  private boolean[] keys;
  private BufferedImage back;
  private int floorY = 500;
  private int width;
  private int height;
  private Cloud[] clouds;
  private boolean dblJump = false;
  private boolean dblJumpReady = false;

  public Runner(int width, int height) {
    player = new Student(50, floorY);
    setBackground(Color.BLACK);
    keys = new boolean[1];
    this.width = width;
    this.height = height;
    clouds = new Cloud[3];
    for (int i = 0; i < clouds.length; i++) {
      clouds[i] = new Cloud(width);
    }

    this.addKeyListener(this);
    new Thread(this).start();

    setVisible(true);
  }

  public void update(Graphics window) {
    paint(window);
  }

  public void paint(Graphics window) {
    Graphics2D twoDGraph = (Graphics2D) window;

    if (back == null)
      back = (BufferedImage) (createImage(getWidth(), getHeight()));

    Graphics graphToBack = back.createGraphics();
    graphToBack.setColor(Color.cyan);
    graphToBack.fillRect(0, 0, width, height);
    graphToBack.setColor(Color.green);
    graphToBack.fillRect(0, floorY, width, height);

    // My Jump Logic
    player.setY(player.getY() + player.getYVel());
    player.setYVel(player.getYVel() + 1);
    if (player.getY() > floorY) {
      player.setY(floorY);
      player.setYVel(0);
    }
    if (keys[0] && player.getY() == floorY) {
      dblJump = true;
      player.setYVel(-15);
    } else if (keys[0] && dblJumpReady) {
      player.setYVel(-15);
      dblJumpReady = false;
      dblJump = false;
    }

    for (int i = 0; i < clouds.length; i++) {
      clouds[i].moveAndDraw(graphToBack);
    }

    player.draw(graphToBack);

    twoDGraph.drawImage(back, null, 0, 0);
  }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      keys[0] = true;
    }
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      keys[0] = false;
      if (dblJump) {
        dblJumpReady = true;
      }
    }
  }

  public void keyTyped(KeyEvent e) {
    // no code needed here
  }

  public void run() {
    try {
      while (true) {
        Thread.currentThread().sleep(7);
        repaint();
      }
    } catch (Exception e) {
    }
  }
}
