import greenfoot.*;

public class Score extends Actor
{

    public Score()
    {
        GreenfootImage newImage = new GreenfootImage(108,36);
        setImage(newImage);
    }

    public void setScore(int score)
    {
        GreenfootImage newImage = getImage();
        newImage.clear();

        Font f = new Font("OCR A STD", 24);
        newImage.setFont(f);
        Color c = new Color(127,127,127,0);
        newImage.setColor(c);
        newImage.fill();
        newImage.setColor(Color.WHITE);
        newImage.drawString("" + score, 6, 30);
        setImage(newImage);  
    } 
}
