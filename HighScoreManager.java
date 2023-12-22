import java.io.*;

public class HighScoreManager {
    private static final String FILE_PATH = "highscore.ser";

    public static Score getHighScore() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (Score) in.readObject();
        } catch (FileNotFoundException e) {
            // File not found (first run or no high score yet)
        } catch (Exception e) {

        }
        return null;
    }

    public static void saveHighScore(Score highScore) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(highScore);
        } catch (Exception e) {

        }
    }
}
