import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author RaulLozano
 * @version 7-12-2015
 */
public class Xplosive extends Actor
{   
    private GreenfootImage imagen;
    private int tiempo;
    
    /**
     * Constructor de Xplosive. 
     */
    public Xplosive(String unNombre)
    {
        this.imagen=new GreenfootImage(unNombre);
        this.imagen.scale(this.imagen.getWidth()/4,this.imagen.getHeight()/4);
        this.setImage(imagen);
        tiempo = 30;
    }
    
    /**
     * Método donde se elimina la bala
     */
    public void quitoBala()
    {
      if(tiempo != 0) {
          tiempo = tiempo-1;
      }else {
          ((Mundo)getWorld()).removeObject(this);
      }
    }
    
    /**
     * Actua de Xplosive
     * 
     */
    public void act()
    {
        this.quitoBala();   
    }
}