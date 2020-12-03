import greenfoot.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;
/**
 * Write a description of class ShipBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShipBullet extends Actor
{
    int speed;
    boolean check;
    /**
     * ShipBullet Constructor
     *
     */
    public ShipBullet()
    {
        speed = 10;

    }
    
    public static List<String> readFileInList(String fileName) {

        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        }

        catch (IOException e) {

            // do something
            e.printStackTrace();
        }
        return lines;
    }

    final List<String> questions = readFileInList("./images/questions.txt");
    final List<String> answers = readFileInList("./images/answers.txt");

    final List<String> questionsHard = readFileInList("./images/questions_hard.txt");
    final List<String> answersHard = readFileInList("./images/answers_hard.txt");

    String currQuestion;
    String currAnswer;
    Random rand = new Random();

    public void askQuestion(boolean hard) {
        // get random hard or easy question
        if (hard){
        int index = rand.nextInt(questionsHard.size());
        currQuestion = questionsHard.get(index);
        currAnswer = answersHard.get(index);

        } else {
        int index = rand.nextInt(questions.size());
        currQuestion = questions.get(index);
        currAnswer = answers.get(index);
        }
        String response = Greenfoot.ask("Find the Derivative of: " + currQuestion);
        if (!response.equals(currAnswer)){
        askQuestion(hard);
        }
    }
    /**
     * Act - do whatever the ShipBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if (getY() <= 0)
        {
            getWorld().removeObject(this);
        }
        else 
        {
            for (int i = 0; i < speed; i ++)
            {
                setLocation(getX(), getY() - 1);
                if (checkCollision())
                {
                    break;
                }
            }
        }

    }

    /**
     * Method checkCollision
     *
     */
    public boolean checkCollision()
    {

        if (getOneIntersectingObject (SpaceInvader.class) != null)
        {
            getWorld().removeObject(getOneIntersectingObject (SpaceInvader.class));
            if (getWorld().getTimerSpeed() >= 25)
            {
                getWorld().setTimerSpeed(getWorld().getTimerSpeed()-1);
            }
            Greenfoot.playSound("invaderkilled.wav");
            getWorld().setScore(getWorld().getScore()+ 50);

            askQuestion(false);

            if (getWorld().getObjects(SpaceInvader.class).size() == 0)
            {
                getWorld().gameOver(true);
            }
            getWorld().removeObject(this);
            return true;
        }
        else if (getOneIntersectingObject (Barrier.class) != null)
        {
            getWorld().removeObject(getOneIntersectingObject (Barrier.class));
            getWorld().removeObject(this);
            return true;
        }
        else if (getOneIntersectingObject (UFO.class) != null)
        {
            getWorld().removeObject(getOneIntersectingObject (UFO.class));
            getWorld().setScore(getWorld().getScore()+ 300);
            getWorld().removeObject(this);
            
            return true;
        }
        return false;

    }

    /**
     * Method getWorld
     *
     * @return getworld
     */
    public InvasionWorld getWorld()
    {
        return (InvasionWorld)(super.getWorld());
    }
}