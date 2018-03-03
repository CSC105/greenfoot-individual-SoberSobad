import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bar extends Actor
{
    private int x;
    private int y;
    private int originX;
    private int originY;
    private boolean visible = false;
    GreenfootImage bar = new GreenfootImage("25x50OrangeRect.png");
    GreenfootImage invisible = new GreenfootImage(1,1);
    
    public Bar(int x, int y){
        originX = x;
        originY = y;
        setImage(invisible);
    }
    
    public void act() 
    {
        if(visible){
            x = (getImage().getWidth()/2)+originX;
            y = (getImage().getHeight()/2)+originY;
            setLocation(x,y);
        }
    }
    
    public void setVisible(boolean b){
        if(b){setImage(bar); visible = true;}
        else {setImage(invisible); visible = false;}
    }
}
