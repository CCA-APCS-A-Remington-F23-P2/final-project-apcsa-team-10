import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

class Student {
  private int y;
  private int x;
  private int yVel;
  private Image image;

  public Student() {
    this(50, 500);
  }

  public Student(int x, int y) {
    this.y = y;
    this.x = x;
    try {
      URL url = getClass().getResource("pixelMan.png");
      image = ImageIO.read(url);
    } catch (Exception e) {
    }
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getYVel() {
    return yVel;
  }

  public void setYVel(int yVel) {
    this.yVel = yVel;
  }

  public void draw(Graphics window) {
    window.drawImage(image, x, y - 60, 170, 70, null);
  }
}
