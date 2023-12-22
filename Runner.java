import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.lang.Math;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Runner extends Canvas implements KeyListener, Runnable{
  public Student player;
  private boolean[] keys;
  private BufferedImage back;
  private int floorY = 500;
  int framesPast = 0;
  private int width;
  private int height;
  ArrayList<Cloud> clouds = new ArrayList<Cloud>();
  ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
  ArrayList<Coin> rewards = new ArrayList<Coin>();
  Score score = new Score();
  private Score highScore;
  private boolean dblJump = false;
  private boolean dblJumpReady = false;
  private int count;
  private long lastObstacle;
  private double obstacleTime;
  private long lastReward;
  private double rewardTime;
  private double lastCloud;
  private boolean done = false;
  private int numGPACoins;
  private int numExtracurricularCoins;
  private double GPA;
  private double ExtraGPA;
  private int numTeachers;
  private int subtract;
  String[] collegeList = {"Rehab", "The Homeless Center", "The McDonalds Kitchen","Point Loma College", "UC Merced", "SDSU", "University of Illinois", "USC", "Harvard"};

  public Runner(int width, int height) {
    player = new Student(100, floorY-80);
    setBackground(Color.BLACK);
    keys = new boolean[1];
    this.width = width;
    this.height = height;
    numGPACoins=0;
    numExtracurricularCoins=0;
    numTeachers = 0;
    /*obstacles.add(new Obstacle(width - 50, floorY - 50, 2));
    if(Math.random()>0.5){
      rewards.add(new GpaCoin(width-50,floorY -150, 2));
      numGPACoins++;
    }
    else{
      rewards.add(new SportsCoin(width-50,floorY -150, 2));
      numExtracurricularCoins++;
    }*/
    highScore = HighScoreManager.getHighScore();
    if (highScore == null) {
        highScore = new Score();
    }
    System.out.println(highScore.college);
    clouds.add(new Cloud(100));
    count =  0;
    lastObstacle = System.currentTimeMillis();
    obstacleTime = (Math.random()*2+2)*1000;
    rewardTime = (Math.random()*3+2)*1000;
    lastReward = System.currentTimeMillis();
    lastCloud = System.currentTimeMillis();
    

    /*
      for(int i = 0; i < 10; i++) {
        clouds.add(new Cloud(width));
      } 
    */

    this.addKeyListener(this);
    new Thread(this).start();

    setVisible(true);
  }

  public void update(Graphics window) {
    paint(window);
  }

  

  public void paint(Graphics window) {
    if(!done){
    Graphics2D twoDGraph = (Graphics2D) window;

    if (back == null)
      back = (BufferedImage) (createImage(getWidth(), getHeight()));

    Graphics graphToBack = back.createGraphics();
    graphToBack.setColor(Color.cyan);
    graphToBack.fillRect(0, 0, width, height);
    graphToBack.setColor(Color.green);
    graphToBack.fillRect(0, floorY, width, height);
    graphToBack.setColor(Color.black);
    score.draw(graphToBack);
      graphToBack.drawString("Best College: " + (highScore.college), 10,80);
    // My Jump Logic
    player.setY(player.getY() + player.getYVel());
    if(count++%5 == 1){
      player.setYVel(player.getYVel()+1);
    }
    if (player.getY() > floorY-80) {
      player.setY(floorY-80);
      player.setYVel(0);
    }
    if (keys[0] && player.getY() == floorY-80) {
     // dblJump = true;
      player.setYVel(-9);
    }
    else if(player.getY() < floorY-1000){
      player.setYVel(9);
    }

    for (int i = 0; i < clouds.size(); i++) {
      clouds.get(i).moveAndDraw(graphToBack);
    }
    for(Cloud cloud : clouds) {
      cloud.moveAndDraw(graphToBack);
      if(cloud.getX() < -1 * 60 * cloud.getSize()){
        clouds.remove(cloud);
      }
    }
    if(System.currentTimeMillis()-lastCloud>1500){
      clouds.add(new Cloud(100));
      lastCloud = System.currentTimeMillis();
    }
    for(Obstacle obstacle : obstacles) {
      obstacle.move();
      obstacle.draw(graphToBack);
      if(obstacle.getX()<0){
        obstacles.remove(obstacle);
        numTeachers++;
      }
      if(player.didCollideLeft(obstacle)){
        score.increaseTeacher();
        obstacles.remove(obstacle);
        numTeachers++;
      }
    }
    if(System.currentTimeMillis()-lastObstacle > obstacleTime) {
      obstacles.add(new Obstacle(width - 50, floorY - 50, 2));
      obstacleTime = (Math.random()*2+2)*1000;
      lastObstacle = System.currentTimeMillis();
    }
    for(Coin reward : rewards) {
      reward.move();
      reward.draw(graphToBack);
      if(reward.getX()<0){
        if(reward.isGpa()){
          numGPACoins++;
        }
        else{
          numExtracurricularCoins++;
        }
        rewards.remove(reward);
      }
      if(player.didCollide(reward)){
        if(reward.isGpa()){
          numGPACoins++;
          score.increaseGPA();
        }
        else{
          numExtracurricularCoins++;
          score.increaseExtracurricular();
        }
        rewards.remove(reward);
        
      }
    }
    if(System.currentTimeMillis()-lastReward > rewardTime) {
      if(Math.random()>0.5){
        rewards.add(new GpaCoin(width-50,floorY -150, 2));
      }
      else{
        rewards.add(new SportsCoin(width-50,floorY -150, 2));
      }
      rewardTime = (Math.random()*3+2)*1000;
      lastReward = System.currentTimeMillis();
    }
   
    player.draw(graphToBack);

    
    if(score.getTeacherHits()>=5){
      GPA = ((double)score.getGpaCoins()/numGPACoins)*4;
      ExtraGPA = ((double)score.getExtracurricularCoins()/numExtracurricularCoins)*4;
      graphToBack.setColor(Color.black);
      graphToBack.fillRect(0,0,width,height);
      graphToBack.setColor(Color.cyan);
      graphToBack.drawString("GPA: " + GPA, 10, 20);
      graphToBack.drawString("Extracurriculars participated in: " + score.getExtracurricularCoins(), 10, 40);
      int combinedGPA = (int) (GPA+ExtraGPA);
      score.rank=combinedGPA;
      score.college=collegeList[combinedGPA];
      subtract = 5-(numTeachers/8);
      if(subtract<0){
        subtract = 0;
      }
      int temp = combinedGPA/*-subtract*/;
      /*if(temp<0){
        temp = 0;
      }*/
      graphToBack.drawString("Due to your academic achievements, You were admitted to: " + collegeList[temp], 10, 60);
      graphToBack.drawString("Stop and Run to play again", 10, 80);

      
      if (highScore.rank<=score.rank) {
        System.out.println("Here");
          highScore = new Score(); // Create a copy of the current score
          highScore.setGPA(score.getGpaCoins());
          highScore.setExtracurricular(score.getExtracurricularCoins());
          highScore.college=score.college;
          System.out.println(highScore.college);
          highScore.rank=score.rank;
        HighScoreManager.saveHighScore(highScore);
      }
      done = true;
      
    }
    
      twoDGraph.drawImage(back, null, 0, 0);
    
    }
  }
 
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      keys[0] = true;
    }
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      keys[0] = false;
      if (dblJump) {
        dblJumpReady = true;
      }
    }
  }

  public void keyTyped(KeyEvent e) {
    // no code needed here
  }

  public void run() {
    try {
      while (true) {
        Thread.currentThread().sleep(7);
        repaint();
      }
    } catch (Exception e) {
    }
  }
}
