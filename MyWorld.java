import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;


public class MyWorld extends World
{   
    public Player player;
    public Solid solid1;
    public Life lifeIcon;
    private Bar alarm0Bar;
    
    private double score;
    private double displayScore;
    private String scoreTmp;
    private int maxLife = 4;
    private int spareLife = 2;
    private int bonusTime = 2000;
    private int[] alarm = new int[2];
    //public TestAngle test;  //debug
    
    //public boolean debug = true;
    public boolean debug = false;
    private int debugLine = 25;
    Text[] debugText = new Text[debugLine];
    public Color white = new Color(250,250,250);
    public Color red = new Color(250,0,0);
    
    public MyWorld()
    {   
        super(1024,768, 1); 
        
        player = new Player(512,700);
        solid1 = new Solid(512,600);
        lifeIcon = new Life(getWidth()-120,60);
        alarm0Bar = new Bar(10,10);
        
        addObject(player,512,700);
        addObject(solid1,512,600);
        addObject(alarm0Bar,10,10);
        addObject(lifeIcon,getWidth()-120,60);
       
        spawnBlock(); 
       
        if(debug){
            for(int i=0 ; i<debugLine ; i++){   //debug
                debugText[i] = new Text("",10,i*20+20);
                addObject(debugText[i],10,i*20+20);
            }
        }
        
        for(int i=0 ; i<alarm.length ; i++){
            alarm[i] = -1;
        }
        
        setPaintOrder(Flamingo.class);
        solid1.fall();
    }
    
    public void print(String str){      //debug
        if(debug){
            for(int i=0 ; i<debugLine-1 ; i++){
                //debugText[i].setColor(debugText[i+1].getColor());
                debugText[i].setText(debugText[i+1].getText(),debugText[i+1].getColor());
            }
            debugText[debugLine-1].setText(str,white);
        }
    }
    public void printRed(String str){      //debug
        if(debug){
            for(int i=0 ; i<debugLine-1 ; i++){
                //debugText[i].setColor(debugText[i+1].getColor());
                debugText[i].setText(debugText[i+1].getText(),debugText[i+1].getColor());
            }
            //debugText[debugLine-1].setColor(red);
            debugText[debugLine-1].setText(str,red);
        }
    }
    public void printAt(String str, int x, int y){
        if(debug){              //debug
            showText(str,x,y);
        }
    }
    
    public void addScore(double n){
        score += n;
    }
    public int getLife(){
        return spareLife;
    }
    public void addLife(){
        if(spareLife < maxLife)spareLife ++;
        
    }
    public void decreaseLife(){
        spareLife--;
        if(spareLife == -1){Greenfoot.delay(100); Greenfoot.setWorld(new GameOver(score));}
    }
    
    public boolean isDebug(){
        return debug;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    
    public void spawnBlock(){
        for(int i=0 ; i<5 ; i++){
           int j=0;
           Block blockTmp = new Block(15,(i*55)+200,i);
           int originalWidth = blockTmp.getImage().getWidth();
           blockTmp.getImage().scale(blockTmp.getImage().getWidth()*(Greenfoot.getRandomNumber(3)+1),blockTmp.getImage().getHeight());
           j = 10+(blockTmp.getImage().getWidth()/2);
           blockTmp.setX(j);
           addObject(blockTmp,j,(i*55)+200);
           while(j < getWidth()){
                Block blockNew = new Block(15,(i*55)+200,i);
                addObject(blockNew,15,(i*55)+200);
                blockNew.getImage().scale(blockNew.getImage().getWidth()*(Greenfoot.getRandomNumber(3)+1),blockNew.getImage().getHeight());
                j = blockTmp.getX()+(blockTmp.getImage().getWidth()/2)+(blockNew.getImage().getWidth()/2)+5;
                blockNew.setX(j);
                
                if(getWidth() - (j+(blockNew.getImage().getWidth()/2)+5) < originalWidth){
                    blockNew.getImage().scale(getWidth()-(15+blockTmp.getX()+(blockTmp.getImage().getWidth()/2)),blockNew.getImage().getHeight());
                    j = blockTmp.getX()+(blockTmp.getImage().getWidth()/2)+(blockNew.getImage().getWidth()/2)+5;
                    blockNew.setX(j);
                    break;
                }
                blockTmp = blockNew;
           }
        }
    }
    
    private void alarm0(){      //RespawnBlock
        if(alarm[0] >= 0)alarm[0]--;
        if(alarm[0] == 0){
            if(getObjects(Block.class).size() == 0){showText("+1 LIFE",getWidth()/2,120); alarm[1] = 200;}
            else{showText("",getWidth()/2,120);}
            removeObjects(getObjects(Block.class));
            solid1.fall();
            spawnBlock();
            alarm0Bar.setVisible(false);
        }
        int width = ((( (getWidth()-20) * ((alarm[0]*100)/bonusTime) )/100) );
        if(alarm[0] > 0 && width >0){alarm0Bar.getImage().scale(width, alarm0Bar.getImage().getHeight()); showText("..BONUS.. clear stage to earn EXTAR LIFE",getWidth()/2,120);}
    }
    private void alarm1(){      //Clear Text
        if(alarm[1] >= 0)alarm[1]--;
        if(alarm[1] == 0){
            showText("",getWidth()/2,120);
        }
    }
    
    public void act(){
        //Score and Life
        showText("Score : " + String.format("%.0f",displayScore),100,50);
        if(solid1.getScore()==0 && solid1.getCombo()==0)scoreTmp = " ";
        if(solid1.getScore()!=0 && solid1.getCombo()==1)scoreTmp = solid1.getScore()+"";
        if(solid1.getScore()!=0 && solid1.getCombo()>1)scoreTmp = solid1.getScore()+" x "+solid1.getCombo();
        showText(scoreTmp,100,70);
        if(displayScore < score){
            displayScore += (score - displayScore)/10;
        }
        showText("x "+spareLife,getWidth()-70,60);
        
        //Respawn block
        if(getObjects(Block.class).size() <= 5){
            if(alarm[0]== -1)alarm[0] = bonusTime;
            alarm0Bar.setVisible(true);
        }
        if(getObjects(Block.class).size() == 0 && alarm[0]>0){
            addLife();
            alarm[0] = 1;
        }
        alarm0();
        alarm1();
    }
    
    
}
