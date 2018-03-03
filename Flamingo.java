import greenfoot.*;

public class Flamingo extends Actor
{
   public int x;
   public int y;
   private int img;
   private int imgSpeed = 1;
   private int xspeed;
   private double scale = 0.18;
   private boolean visible = true;
   
   GreenfootImage[] flamingoImg = new GreenfootImage[18];
   GreenfootImage invisible;
   
   public Flamingo(int x,int y){
        for(int i=0 ; i<18 ; i++){
            flamingoImg[i] = new GreenfootImage("Flamingo"+((i+1)*2)+".png");
            flamingoImg[i].scale((int)(flamingoImg[i].getWidth()*scale),(int)(flamingoImg[i].getHeight()*scale));
        }
        setImage(flamingoImg[0]);
        invisible = new GreenfootImage(getImage().getWidth(),getImage().getHeight());
        this.x = x;
        this.y = y;
   }
   
   public void setPos(int x, int y){
       this.x = x;
       this.y = y;
    } 
   public void setXspeed(int xspeed){
       this.xspeed = xspeed;
    }
    public void setVisible(boolean b){
        visible = b;
    }
    
   //*************************************************************************************
    
   public void act() 
    {
    if(Greenfoot.isKeyDown("left") || (Greenfoot.isKeyDown("a"))){
        img -= imgSpeed;
    }
    if(Greenfoot.isKeyDown("right") || (Greenfoot.isKeyDown("d"))){
        img += imgSpeed;
    }
    
    if(img>=flamingoImg.length){img-=flamingoImg.length;}       //Img cap
    if(img<0){img+=flamingoImg.length;}
    
    turn(xspeed/2);
    
    setLocation(x,y);
    
    if(visible){ setImage(flamingoImg[img]); }
    else{ setImage(invisible); }
    }
}
