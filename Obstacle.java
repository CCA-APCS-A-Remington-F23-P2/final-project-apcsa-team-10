gimport java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Obstacle {
  private int x;
  private int y;
  private int xSpeed;
  private Image image;

  public Obstacle(int startX, int startY, int speed) {
    try {
      URL url = getClass().getResource("teacher.png");
      image = ImageIO.read(url);
    } catch (Exception e) {
    }
    
    x = startX;
    y = startY;
    xSpeed = speed;
  }

  public void move() {
    x -= xSpeed;
  }

  public void setImage(Image img){
    image = img;
  }

  public void draw(Graphics window) {
    window.drawImage(image, x, y, 50, 50, null);
  }
}
