import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author RaulLozano
 * @version 7-12-2015
 */
public class Camion extends Bonus
{
    /**
     * Constructor de camion.
     */
    public Camion() 
    {
        this.getImage().scale(this.getImage().getWidth()/4,this.getImage().getHeight()/4);
    }  
    
    /**
     * Actua de Camion
     */
    public void act()
    {
        this.tocoCamion();
    }
    
    /**
     * Método para checar si alguno de los jugadores toco la llave
     */
    public void tocoCamion()
    {
        Mundo mundo=(Mundo)getWorld();
        if(isTouching(JackCartero.class)) 
        {
            mundo.setLevel(mundo.getLevel()+1);
            mundo.removeObject(this);
            mundo.setExisteCamion(false);
        }
    }
}