import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author RaulLozano
 * @version 7-12-2015
 */
   
public class Signboard extends Actor
{
    private int tiempo;
    
    /**
     * Constructor de Signboard.
     */
    public Signboard(String mensaje)
    {
        GreenfootImage letrero;
        letrero= new GreenfootImage(mensaje ,50 ,java.awt.Color.BLACK ,java.awt.Color.BLUE);
        setImage(letrero);
        tiempo=10;
    } 
}
