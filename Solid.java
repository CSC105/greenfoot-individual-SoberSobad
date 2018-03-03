import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class solid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Solid extends Actor
{   
    private int x;
    private int y;
    private int xspeed;
    private int yspeed;
    private int startX;
    private int startY;
    private int xspeedLimit = 5;
    private int yspeedLimit = 4;
    private long scoreTmp;
    private int combo;
    
    private boolean flam = false;
    Flamingo flamingoTmp;
    
    private boolean respawning = false;
    private int wait;
    
    GreenfootImage visible = new GreenfootImage("25x25BlueRect.png");
    GreenfootImage invisible = new GreenfootImage(visible.getWidth(),visible.getHeight());
    Player player1;
    
    GreenfootSound hit1 = new GreenfootSound("beep-07.mp3");
    GreenfootSound hit2 = new GreenfootSound("beep-08b.mp3");
    GreenfootSound error = new GreenfootSound("beep-03.mp3");
    
    public Solid(int x,int y){
       this.x = x;
       this.y = y;
       startX = x;
       startY = y;
       setImage(invisible);
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    
    public int getXspeed(){
        return xspeed;
    }
    public long getScore(){
        return scoreTmp;
    }
    public int getCombo(){
        return combo;
    }
    
    public void hit(Block block,int x, int y, int angle, int layer){
        int xspeedTmp = xspeed; //debug
        int yspeedTmp = yspeed; //debug
        if(layer == 0){yspeedLimit = 9; xspeedLimit = 9;}
        if(layer == 1){yspeedLimit = 6; xspeedLimit = 7;}
        if(layer == 2){yspeedLimit = 5; xspeedLimit = 6;}
        ((MyWorld)getWorld()).print(block+ " : " + angle);          //debug
        if((angle>=45 && angle<=135) || (angle>=225 && angle<=315)){
            yspeed = yspeed*-1;
            int i = 100;
            while(isTouching(Block.class)){
                if(i<0){((MyWorld)getWorld()).printRed("INFINITE LOOP...!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"); break;}
                y += Integer.signum(yspeed);
                setLocation(x,y);
                i--;
            }
        }
        if((angle>135 && angle<225) || (angle>315 || angle<45)){
            xspeed = xspeed*-1;
            int i = 100;
            while(isTouching(Block.class)){
                if(i<0){((MyWorld)getWorld()).printRed("INFINITE LOOP...!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"); break;}
                x += Integer.signum(xspeed);
                setLocation(x,y);
                i--;
            }
        }
        ((MyWorld)getWorld()).print("angle = "+ angle + " || " + xspeedTmp + " : " + yspeedTmp + " || " + xspeed + " : " + yspeed);
        //((MyWorld)getWorld()).addScore(5*(5-layer));
        scoreTmp += 5*(5-layer);
        combo++;
    }
    
    public void fall(){
        xspeed = 0;
        yspeed = 0;
        x = startX;
        y = startY;
        setLocation(x,y);
        ((MyWorld)getWorld()).printRed("FALL...!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        respawning = true;
        wait = 100;
        if(flam)flamingoTmp.setVisible(false);
        ((MyWorld)getWorld()).addScore(scoreTmp * combo);
        scoreTmp = 0;
        combo = 0;
    }
    
    public void act() 
    {
        if(!flam){
            Flamingo flamingo = new Flamingo(x,y);
            getWorld().addObject(flamingo,x,y);
            flamingoTmp = flamingo;
            xspeed = Greenfoot.getRandomNumber(16)-8;
            //yspeed = -7;
            yspeed = -4;
            player1 = ((MyWorld)getWorld()).getPlayer();
            flam = true;
        }
        
        if(((MyWorld)getWorld()).isDebug()){
            if(Greenfoot.isKeyDown("down")){
                setImage(visible);
            }
            else{
                setImage(invisible);
            }
        }
        
        /*
        //Move
        if(Math.abs(xspeed) > xspeedLimit){xspeed = xspeedLimit * Integer.signum(xspeed);}
        x += xspeed;
        y += yspeed;
        */
        //Collision
        //Horizontal Edge
        if(x<(getImage().getWidth()/2)+10){
            while(x<(getImage().getWidth()/2)+10){x++; setLocation(x,y);}
            xspeed = xspeed *-1;
        }
        if(x>(getWorld().getWidth()-(getImage().getWidth()/2))-10){
            while(x>(getWorld().getWidth()-(getImage().getWidth()/2))-10){x--; setLocation(x,y);}
            xspeed = xspeed *-1;
        }
        //Upper Edge
        if(y<(getImage().getHeight()/2)+10){yspeed = yspeed*-1;}
        //Down Edge
        if(y>getWorld().getHeight()){
            /*
            xspeed = 0;
            yspeed = 0;
            x = startX;
            y = startY;
            setLocation(x,y);
            ((MyWorld)getWorld()).printRed("FALL...!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            respawning = true;
            wait = 100;
            flamingoTmp.setVisible(false);
            ((MyWorld)getWorld()).addScore(scoreTmp * combo);
            scoreTmp = 0;
            combo = 0;*/
            fall();
            error.play();
            ((MyWorld)getWorld()).decreaseLife();
        }
        //Touchh Player
        if(isTouching(Player.class) && y > ((MyWorld)getWorld()).player.getY()){
            while(isTouching(Player.class)){y--; setLocation(x,y);}
            Player player = ((MyWorld)getWorld()).player;
            int plX = player.getX();
            int plWidth = player.getWidth();
            double change = ((double)(Math.abs(plX-x)/(double)(plWidth/2))*12)*Math.signum(plX-x);
            //double change = ((double)(Math.abs(plX-x)/(double)(plWidth/2))*(xspeedLimit*2))*Math.signum(plX-x);
            ((MyWorld)getWorld()).print(((double)(Math.abs(plX-x)/(double)(plWidth/2)))+"  "+change);
            xspeed -= change;
            yspeed = yspeed*-1;
            ((MyWorld)getWorld()).addScore(scoreTmp * combo);
            scoreTmp = 0;
            combo = 0;
            hit1.play();
        }
        
        if(flam){
            flamingoTmp.setPos(x,y);
            flamingoTmp.setXspeed(xspeed);
        }
        
        //setLocation(x,y);
        
        if(respawning){
            if(wait>0){
                //setLocation(startX,startY);
                wait--;
            }
            else{
                respawning = false;
                xspeed = Greenfoot.getRandomNumber(16)-8;
                //yspeed = -7;
                yspeed = -4;
                yspeedLimit = 4;
                xspeedLimit = 5;
            }
            if(wait == 20){flamingoTmp.setVisible(true);}
        }
        else{
            //Move
            if(Math.abs(xspeed) > xspeedLimit){xspeed = xspeedLimit * Integer.signum(xspeed);}
            if(Math.abs(yspeed) < yspeedLimit){yspeed = yspeedLimit * Integer.signum(yspeed);}
            x += xspeed;
            y += yspeed;
            setLocation(x,y);
        }
    }
    
}
