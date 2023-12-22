import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Coin extends Block{
  private boolean isGpa;
  private Image image;
  public Coin(int startX, int startY, int speed, boolean gpa) {
    super(startX,startY,speed);
    isGpa = gpa;
  }
  public void move() {
    setX(getX()-getXVel());
  }
  public boolean isGpa(){
    return isGpa;
  }
  public void draw(Graphics window) {
      window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
  }
  public void setImage(Image img){
    image = img;
  }
}