import greenfoot.*;

public class InvasionWorld extends World
{

    private int invaderLevel;
    private int timerSpeed ;
    private int fireSpeed ;
    private int counter;
    private int sound;
    private Ship player;
    private int LAYERS = 6;
    private int COLUMNS=11;
    private int xBuffer = 55;
    private int yBuffer = 5;
    private int score;
    Score scoreObj= null;
    /**
     * Constructor for objects of class InvasionWorld.
     * 
     */
    public InvasionWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 700, 1); 

        invaderLevel = 0;
        timerSpeed = 50;
        fireSpeed = 60;
        counter = 0;
        sound = 4;
        score = 0;
        player = new Ship();
        addObject(player, getWidth()/2, getHeight()/2 + getHeight()/3 + getHeight()/10);

        SpaceInvader spaceInvader = new SpaceInvader();

        int BarrierLength = 32;
        int BarrierHeight = 2 ;
        //add center barrier
        addBarriers(getWidth()/2, BarrierLength, BarrierHeight);
        // add left barrier
        addBarriers(getWidth()/4,BarrierLength, BarrierHeight);
        // add right barrier
        addBarriers(getWidth()/2+getWidth()/4,BarrierLength, BarrierHeight);
        addSpaceInvaders(LAYERS,COLUMNS);
        xBuffer = 55;
        yBuffer = 5;
        scoreObj = new Score();
        scoreObj.setScore(0);
        addObject(scoreObj, 60, 375);
    }

    /**
     * Method gameOver
     *
     * @param playerWin A parameter
     */
    public void gameOver(boolean playerWin)
    {
        if (playerWin)
        {
            addSpaceInvaders(LAYERS, COLUMNS);
            addBarriers(getWidth()/2+getWidth()/4, 32, 2);
            addBarriers(getWidth()/2, 32, 2);
            addBarriers(getWidth()/4,32, 2);
            Greenfoot.playSound("WinLevel.mp3");
            invaderLevel = 0;
        }
        else
        {
            showText( "GAME OVER, YEAH", getWidth()/2, getHeight()/2);
            Greenfoot.playSound("explosion.wav");
            Greenfoot.stop();
        }
        
    }

    /**
     * Method addSpaceInvaders
     *
     * @param layers A parameter
     * @param columns A parameter
     */
    private void addSpaceInvaders(int layers, int columns)
    {

        for (int i = 0; i < columns; i ++)
        {
            for (int j = 1; j <= layers/3; j ++)
            {
                SpaceInvader spaceInvader = new SpaceInvader(3,j,i);
                int x = (int)((i  * (spaceInvader.getImage().getWidth()+ 5))+ xBuffer);
                int y = (int)((j * (spaceInvader.getImage().getHeight() + yBuffer)));
                addObject(spaceInvader, x , y);
            }
            for (int j = 3;  layers/3 < j && j <= 2*layers/3; j ++)
            {
                SpaceInvader spaceInvader = new SpaceInvader(2,j,i);
                int x = (int)((i  * (spaceInvader.getImage().getWidth()+ 5))+ 55);
                int y = (int)((j * (spaceInvader.getImage().getHeight() + yBuffer)));
                addObject(spaceInvader, x , y);
            }
            for (int j = 5;  2 * layers/3 < j && j <= layers; j ++)
            {
                SpaceInvader spaceInvader = new SpaceInvader(1,j,i);
                int x = (int)((i  * (spaceInvader.getImage().getWidth()+ 5 ))+ 55);
                int y = (int)((j * (spaceInvader.getImage().getHeight() + yBuffer)));
                addObject(spaceInvader, x , y);
            }
        }

    }

    /**
     * Method addBarriers
     *
     * @param position A parameter
     * @param width A parameter
     * @param layer A parameter
     */
    public void addBarriers(int position, int width, int layer)
    {
        //Adds a generic barrier with a certian position, width, and layer
        for(int i =0; i < width; i++)
        {
            for(int j = 0; j <layer; j ++)
            {
                Barrier barrier = new Barrier();
                addObject(barrier, (position)+i*2 - width, getHeight()/2 + getHeight()/4 + j*2);
            }
        }

    }

    /**
     * Method checkLeftEdge
     *
     */
    public void checkLeftEdge(java.util.List invader)
    {
        SpaceInvader spaceInvader = (SpaceInvader)invader.get(0);
        for (int i = 0; invader.size() > i; i ++)
        {
            if (((SpaceInvader)invader.get(i)).getColumn() < spaceInvader.getColumn())
            {
                spaceInvader = (SpaceInvader)invader.get(i);
            }
        }
        if (spaceInvader.getX() + spaceInvader.getSpeed() - spaceInvader.getImage().getWidth()/2 < 0)
        {
            invaderLevel++;
        }
    }

    /**
     * Method checkRightEdge
     *
     */
    public void checkRightEdge(java.util.List invader)
    {
        SpaceInvader spaceInvader = (SpaceInvader)invader.get(0);
        for (int i = 0; invader.size() > i; i ++)
        {
            if (((SpaceInvader)invader.get(i)).getColumn() > spaceInvader.getColumn())
            {
                spaceInvader = (SpaceInvader)invader.get(i);
            }
        }
        if (spaceInvader.getX() + spaceInvader.getSpeed()+ spaceInvader.getImage().getWidth()/2 > getWidth())
        {
            invaderLevel++;
        }
    }

    /**
     * Method findLowestInvader
     *
     * @param invader A parameter
     * @param column A parameter
     * @return The return value
     */
    public SpaceInvader findLowestInvader(java.util.List invader, int column)
    {
        SpaceInvader spaceInvader= new SpaceInvader(1, -1, 12);
        for (int i = 0; invader.size() > i; i ++)
        {
            if (((SpaceInvader)invader.get(i)).getColumn() == column)
            {
                if (((SpaceInvader)invader.get(i)).getLayer() >= spaceInvader.getLayer())
                {
                    spaceInvader = (SpaceInvader)invader.get(i);
                }
            }
        }
        if (spaceInvader.getLayer() == -1)
        {
            return null;
        }
        return spaceInvader;
    }
    /**
     * Method UFO
     *
     */
    public void UFO()
    {
        if (Greenfoot.getRandomNumber(400)==0)
        {
            UFO aw = new UFO();
            addObject(aw, 20, 100);
        }
        
    }
    /**
     * ACT METHOD
     *
     */
    public void act()
    {
        java.util.List invaders = getObjects(SpaceInvader.class);
        counter ++;
        scoreObj.setScore(score);
        
        if (counter % timerSpeed==0)
        {
            switch (sound) {
                case 1: Greenfoot.playSound("fastinvader1.wav");
                sound++;
                break;
                case 2: Greenfoot.playSound("fastinvader2.wav");
                sound++;
                break;
                case 3: Greenfoot.playSound("fastinvader3.wav");
                sound ++;
                break;
                case 4: Greenfoot.playSound("fastinvader4.wav");
                sound = 1;
                break;

            }
        }
        if (invaderLevel % 2== 0)
        {
            checkRightEdge(invaders);
        }
        else {
            checkLeftEdge(invaders);
        }
        if (counter % fireSpeed ==0)
        {
            int firenum = COLUMNS - 4 - Greenfoot.getRandomNumber(5);
            for (int i = 0; i < firenum; i ++)
            {
                SpaceInvader spaceInvader = findLowestInvader(invaders, Greenfoot.getRandomNumber(COLUMNS));
                if (spaceInvader != null)
                {
                    spaceInvader.fire();
                }
            }
            
        }
        UFO();
    }

    /**
     * Method getPlayer
     *
     * @return The return value
     */
    public Ship getPlayer()
    {
        return player;
    }

    /**
     * Method getTimerSpeed
     *
     * @return The return value
     */
    public int getTimerSpeed()
    {
        return timerSpeed;
    }

    /**
     * Method setTimerSpeed
     *
     * @param timer A parameter
     */
    public void setTimerSpeed(int timer)
    {
        timerSpeed = timer;
    }
    /**
     * Method getScore
     *
     * @return The return value
     */
    public int getScore()
    {
        return score;
    }
    /**
     * Method setScore
     *
     * @param newScore A parameter
     */
    public void setScore(int newScore)
    {
        score = newScore;        
    }
    /**
     * Method setInvaderLevel
     *
     * @param level A parameter
     */
    public void setInvaderLevel(int level)
    {
        invaderLevel = level;
    }

    /**
     * Method getInvaderLevel
     *
     * @return The return value
     */
    public int getInvaderLevel()
    {
        return invaderLevel;
    }
    public int getLayers()
    {
        return LAYERS;
    }
    public int getColumns()
    {
        return COLUMNS;
    }
}
