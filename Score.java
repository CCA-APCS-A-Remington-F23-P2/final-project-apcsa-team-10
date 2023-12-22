import java.awt.Graphics;
import java.io.Serializable;
public class Score implements Serializable{
  private int GPAcoins;
  private int extracurricularCoins;
  private int teacherHits;
  public String college;
  public int rank;
  public Score() {
    GPAcoins = 0;
    extracurricularCoins = 0;
    teacherHits = 0;
    college="Default";
    rank = 0;
  }

  public void increaseGPA() {
    GPAcoins++;
  }

  public void increaseExtracurricular() {
    extracurricularCoins++;
  }
  public void increaseTeacher(){
    teacherHits++;
  }
  public int getGpaCoins(){
    return GPAcoins;
  }
  public int getExtracurricularCoins(){
    return extracurricularCoins;
  }
  public int getTeacherHits(){
    return teacherHits;
  }

    public void setGPA(int gpaCoins) {
        this.GPAcoins = gpaCoins;
    }

    public void setExtracurricular(int extracurricularCoins) {
        this.extracurricularCoins = extracurricularCoins;
    }


  public void draw(Graphics window) {
    window.drawString("GPA Coins: " + GPAcoins, 10, 20);
    window.drawString("Extracurricular Coins: " + extracurricularCoins, 10, 40);
    window.drawString("Teacher Hits: " + teacherHits, 10, 60);
  }
}
