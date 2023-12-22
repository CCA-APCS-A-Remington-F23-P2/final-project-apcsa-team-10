import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class GpaCoin extends Coin{
  private Image image;

  public GpaCoin(int startX, int startY, int speed) {
    super(startX,startY,speed,true);
    try {
      URL url = getClass().getResource("Coin.png");
      image = ImageIO.read(url);
    } catch (Exception e) {
    }
    setImage(image);
  }


  

  
}