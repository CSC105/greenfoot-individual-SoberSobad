import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Start extends World
{

    PlayButton playButton = new PlayButton();
    
    public Start()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 768, 1); 
        
        for(int i=0 ; i<15 ; i++){
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            Particle particle = new Particle(x,y);
            addObject(particle,x,y);
        }
        
        addObject(playButton,getWidth()/2,getHeight()/2);
        showText("Flamingo Breakout",getWidth()/2,250);
    }
}
