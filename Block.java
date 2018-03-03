import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Block here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Block extends Actor
{   
    private int x;
    private int y;
    private int layer;
    
    GreenfootSound hit2 = new GreenfootSound("beep-08b.mp3");
    Solid solid1;
    
    public Block(int x, int y, int layer){
        this.x = x;
        this.y = y;
        this.layer = layer;
        if(layer == 3){setImage("25x50GreenRect3.png");}
        if(layer == 2){setImage("25x50GreenRect2.png");}
        if(layer == 1){setImage("25x50OrangeRect.png");}
        if(layer == 0){setImage("25x50OrangeRect2.png");}
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x = x;
        setLocation(x,y);
    }
    
    public void act() 
    {
        if(isTouching(Solid.class)){
            solid1 = ((MyWorld)getWorld()).solid1;
            turnTowards(solid1.getX(),solid1.getY());
            int angle = getRotation();
            solid1.hit(this,x,y,angle,layer);
            getWorld().removeObject(this);
            hit2.play();
        }
        setLocation(x,y);
        
        if(Greenfoot.getKey() == ("down") && ((MyWorld)getWorld()).isDebug() ){
            getWorld().removeObject(this);
        }
    }    
}
