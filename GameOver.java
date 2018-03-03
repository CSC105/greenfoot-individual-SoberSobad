import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends World
{

    RestartButton restart = new RestartButton();
    private double score;
    
    public GameOver(double score)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 768, 1);  
        
        for(int i=0 ; i<15 ; i++){
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            Particle particle = new Particle(x,y);
            addObject(particle,x,y);
        }
        
        addObject(restart,getWidth()/2,getHeight()/2);
        this.score = score;
        showText("YOUR SCORE IS : " + String.format("%.0f",score),getWidth()/2,getHeight()-250);
    }
}
