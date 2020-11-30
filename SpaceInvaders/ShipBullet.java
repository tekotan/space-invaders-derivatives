import greenfoot.*;

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
