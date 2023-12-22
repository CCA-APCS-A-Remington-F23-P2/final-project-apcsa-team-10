import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class SportsCoin extends Coin{
  private Image image;

  public SportsCoin(int startX, int startY, int speed) {
    super(startX,startY,speed,false);
    image=coinType();
    setImage(image);
  }
  public Image coinType(){

      if(Math.random()>0.5){
      try {
        URL url = getClass().getResource("basketball.png");
        return ImageIO.read(url);
      } catch (Exception e) {
      }
    }
    else{
      try {
        URL url = getClass().getResource("baseball.png");
        return ImageIO.read(url);
      } catch (Exception e) {
      }
      }
    return image;
  }
}