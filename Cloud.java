import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

class Cloud extends Block{
  private int xSpeed;
  private Image image;
  private int width;
  private int size;

  public Cloud(int width) {
    try {
      URL url = getClass().getResource("Cloud.png");
      image = ImageIO.read(url);
    } catch (Exception e) {
    }
    this.width = width;

    this.setX(550);
    this.setY((int) (Math.random() * (width / 2) + 50));
    xSpeed = 1;
    size = (int) (Math.random() * 5 + 2);
  }

  public int getSize() {
    return size;
  }

  public void moveAndDraw(Graphics window) {
    this.setX(this.getX()-xSpeed);
    window.drawImage(image, this.getX(), this.getY(), 60 * size, 40 * size, null);
  }

}
