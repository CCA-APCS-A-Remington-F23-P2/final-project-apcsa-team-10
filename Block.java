import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
public class Block{
  private int y;
  private int x;
  private int yVel;
  private int xVel;
  private int width;
  private int height;
  public Block(){
    y = 0;
    x = 0;
    yVel = 0;
    xVel = 0;
    width = 10;
    height = 10;
  }
  public Block(int x, int y){
    this.y = y;
    this.x = x;
    yVel = 0;
    xVel = 0;
    width = 80;
    height = 80;
  }
  public Block(int startX, int startY, int speed){
    x = startX;
    y = startY;
    xVel = speed;
    yVel = 0;
    width = 50;
    height = 50;
  }
  public int getY() {
    return y;
  }
  public int getX(){
    return x;
  }

  public void setY(int y) {
    this.y = y;
  }
  public void setX(int x) {
    this.x = x;
  }
  public int getYVel() {
    return yVel;
  }
  public int getXVel() {
    return xVel;
  }
  public void setYVel(int yVel) {
    this.yVel = yVel;
  }
  public void setXVel(int xVel) {
    this.xVel = xVel;
  }
  public int getWidth(){
    return width;
  }
  public int getHeight(){
    return height;
  }
}
