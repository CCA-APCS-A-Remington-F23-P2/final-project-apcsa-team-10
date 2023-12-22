import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Obstacle extends Block{
  private Image image;

  public Obstacle(int startX, int startY, int speed) {
    super(startX,startY,speed);
    try {
      URL url = getClass().getResource("teacher.png");
      image = ImageIO.read(url);
    } catch (Exception e) {
    }
  }

  public void move() {
    setX(getX()-getXVel());
  }
  public int getWidth(){
    return super.getWidth();
  }

  public void setImage(Image img){
    image = img;
  }

  public void draw(Graphics window) {
      window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
  }
}