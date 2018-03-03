import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.Color;

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actor
{
    private int x;
    private int y;
    private String str = "";
    //private Color col;
    GreenfootImage text;
    
    public Text(String str,int x, int y){
        text = new GreenfootImage(str.length()*5+20,20);
        text.drawString(str,10,15);
        setImage(text);
        //col = white;
        this.x = x;
        this.y = y;
    }
    
    public void setText(String str, Color col){
        getImage().clear();
        text = new GreenfootImage(str.length()*10+20,20);
        text.setColor(col);
        text.drawString(str,10,15);
        setImage(text);
        this.str = str;
        x = 10 + (getImage().getWidth()/2);
        //x = 100;
        setLocation(x,y);
    }
    
    public String getText(){
        return str;
    }
    
    public Color getColor(){
        return text.getColor();
    }
    
    public void setPosition(int x, int y){
        setLocation(x,y);
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
