import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Student extends Block implements Collideable<Block>{
  private Image image1;
  private Image image2;
  private Image image3;
  private int costume = 0;

  public Student() {
    super(50, 500);
  }

  public Student(int x, int y) {
    super(x,y);
    try {
      URL url = getClass().getResource("ForwardRun.png");
      image1 = ImageIO.read(url);
    } catch (Exception e) {
    }
    try {
      URL url = getClass().getResource("BackRun.png");
      image2 = ImageIO.read(url);
    }catch (Exception e) {
        }try {
      URL url = getClass().getResource("Jump.png");
      image3 = ImageIO.read(url);
    }catch (Exception e) {
    }

  }

  public void draw(Graphics window) {
    costume++;
    if(getYVel()!=0){
      window.drawImage(image3, getX(), getY(), getWidth(), getHeight(), null);
    }
    else if (costume > 40) {
      window.drawImage(image1, getX(),  getY(), getWidth(), getHeight(), null);
      if(costume > 80){
        costume = 0;
      }
    }
    else {
      window.drawImage(image2, getX(),  getY(), 80, 80, null);
    }
  }
  public boolean didCollideLeft(Block obj){
    return getX() <= obj.getX() + obj.getWidth() && getX() >= obj.getX()
      && getY() + getHeight() >= obj.getY() && getY() <= obj.getY() + obj.getHeight();
  }
  public boolean didCollideRight(Block obj){
    return getX() +getWidth()>= obj.getX() && getX() + getWidth() <= obj.getX() + obj.getWidth()
      && getY() + getHeight() >= obj.getY() && getY() <= obj.getY() + obj.getHeight();
  }
  public boolean didCollide(Block obj){
    return didCollideLeft(obj) || didCollideRight(obj);
  }
}
