import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Particle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Particle extends Actor
{
    private int x;
    private int y;
    private int xspeed;
    private int yspeed;
    private int speedLimit = Greenfoot.getRandomNumber(8)+1;
    GreenfootImage img;
    private int step = 0;
    private int alpha;
    
    public Particle(int x, int y){
        this.x = x;
        this.y = y;
        
        xspeed = Greenfoot.getRandomNumber(speedLimit+1) * (Greenfoot.getRandomNumber(3)-1);
        yspeed = Greenfoot.getRandomNumber(speedLimit+1) * (Greenfoot.getRandomNumber(3)-1);
        turn(Greenfoot.getRandomNumber(360));
        
        int i = Greenfoot.getRandomNumber(9);
        if(i==0 || i==8)img = new GreenfootImage("25x25BlueRect.png");
        if(i==1)img = new GreenfootImage("25x50GreenRect1.png");
        if(i==2)img = new GreenfootImage("25x50GreenRect2.png");
        if(i==3)img = new GreenfootImage("25x50GreenRect3.png");
        if(i==4)img = new GreenfootImage("25x50GreenRect4.png");
        if(i==5)img = new GreenfootImage("25x50OrangeRect.png");
        if(i==6)img = new GreenfootImage("25x50OrangeRect2.png");
        if(i==7)img = new GreenfootImage("25x50YellowRect2.png");
        
        setImage(img);
        alpha = Greenfoot.getRandomNumber(176)+80;
        if(alpha > 255){alpha = 255;}
        if(alpha < 0){alpha = 0;}
        getImage().setTransparency(alpha);
        //getImage().setTransparency(256);
    }
    public void act() 
    {
       if(xspeed == 0)xspeed = Greenfoot.getRandomNumber(speedLimit+1) * (Greenfoot.getRandomNumber(3)-1);
       if(yspeed == 0)yspeed = Greenfoot.getRandomNumber(speedLimit+1) * (Greenfoot.getRandomNumber(3)-1);
        
       if(x > getWorld().getWidth()-10){x = getWorld().getWidth()-10; xspeed = xspeed*-1;}
       if(x < 10){x = 10; xspeed = xspeed*-1;}
       if(y > getWorld().getHeight()-10){y = getWorld().getHeight()-10; yspeed = yspeed*-1;}
       if(y < 10){y = 10; yspeed = yspeed*-1;}
      
       x += xspeed;
       y += yspeed;
       
       turn(xspeed/2);
       
       setLocation(x,y);
       /*
       if(step == 100){alpha += Greenfoot.getRandomNumber(21)-10; step=0;}
       if(alpha > 255){alpha = 255;}
       if(alpha < 0){alpha = 255;}
       getImage().setTransparency(alpha);
       step++;*/
    }    
}
