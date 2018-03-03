import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class life here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Life extends Actor
{
    private int x;
    private int y;
    GreenfootImage flamingo = new GreenfootImage("Flamingo2.png");
    
    public Life(int x, int y){
        flamingo.scale( (int)(flamingo.getWidth()*0.12), (int)(flamingo.getHeight()*0.12) );
        this.x = x;
        this.y = y;
        setImage(flamingo);
        turn(270);
        setLocation(x,y);
    }
    
    
}
