import greenfoot.*;

public class Ship extends Actor
{
    private int speed;
    private int counter;
    private int RELOAD_TIME = 40;
    /**
     * Ship Default Constructor
     *
     */
    public Ship()
    {
        speed = 5;
        counter = 0;
    }

    /**
     * Act - do whatever the Ship wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        speed = 5;
        checkKeys();
    }    

    /**
     * Method checkKeys
     *
     */
    public void checkKeys()
    {
        //WASD or arrow keys 
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d"))
        {
            setLocation(getX() + speed, getY());

        }
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a"))
        {
            setLocation(getX() - speed, getY());

        }

        if (counter > 0)
        {
            counter --;
        }
        // You can also press space instead of the up arrow
        if ((Greenfoot.isKeyDown("up") || (Greenfoot.isKeyDown("space")) || (Greenfoot.isKeyDown("w"))) && counter == 0)
        {
            ShipBullet shipBullet = new ShipBullet();
            getWorld().addObject(shipBullet, getX() , getY());
            Greenfoot.playSound("shoot.wav");
            counter = RELOAD_TIME;
        }

    }
}
