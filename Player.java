import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    //Keyboard initialize
    public boolean left;
    public boolean right;
    public boolean up;
    
    //Movement variable
    private int x;
    private int y;
    private int accel = 2;
    private int xspeed;
    private int xspeedLimit = 11;
    private int yspeed;
    private int friction = 1;
    
    public Player(int x,int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getWidth(){
        return getImage().getWidth();
    }
    
    public int getXspeed(){
        return xspeed;
    }
    
    public void act() 
    {
        //Keyboard check
        left = (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a"));
        right = (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d"));
        //up = (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w"));
        
        if(left){xspeed -= accel;}
        if(right){xspeed += accel;}
        
        
        //Movement
        if(Math.abs(xspeed) > xspeedLimit){xspeed = xspeedLimit * Integer.signum(xspeed);}
        x += xspeed;
        y += yspeed;
        if(Math.abs(xspeed) > 0){
            if(Math.abs(xspeed)-friction < 0){xspeed = 0;}
            else{xspeed -= friction*Integer.signum(xspeed);}
        }
        if(x<(getImage().getWidth()/2)+10){x = (getImage().getWidth()/2)+10;}         //Edge
        if(x>(getWorld().getWidth()-(getImage().getWidth()/2))-10){x = (getWorld().getWidth()-(getImage().getWidth()/2))-10;}
       setLocation(x,y);
    }
    
    
}
