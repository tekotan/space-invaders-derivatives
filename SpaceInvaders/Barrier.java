import greenfoot.*;

/**
 * Write a description of class Barrier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Barrier extends Actor
{
    /**
     * Act - do whatever the Barrier wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if (getOneIntersectingObject (ShipBullet.class) != null)
        {
            getWorld().removeObject(this);
        }
    }    
}
