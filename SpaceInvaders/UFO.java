import greenfoot.*;

/**
 * Write a description of class UFO here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UFO extends Actor
{
    /**
     * Act - do whatever the UFO wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if (getX() >= 999)
        {
            getWorld().removeObject(this);
        }    
        else 
        {
            for (int i = 0; i < 5; i ++)
            {
                setLocation(getX()+1, getY());
            }
        }
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
