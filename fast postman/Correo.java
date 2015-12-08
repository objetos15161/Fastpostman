import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author RaulLozano
 * @version 7-12-2015
 */
public class Correo extends Bonus
{
    /**
     * Constructor de correo.
     */
    public Correo() 
    {
       this.getImage().scale(this.getImage().getWidth()/2,this.getImage().getHeight()/2);
    }    
    
    /**
     * Actua de Star
     */
    public void act()
    {
        this.movimiento();
        this.tocoCorreo();
    }
    
    /**
     * Método donde verifica si lo toco jack*/
    public void tocoCorreo()
    {
        Mundo mundo=(Mundo)getWorld();
        if(isTouching(JackCartero.class)) 
        {
            mundo.setCorreo();
            this.getWorld().removeObject(this);
        }
    }
    
    /**
     * Método donde el correo se mueven en el mundo
     */
    public void movimiento()
    {
        this.setLocation(this.getX()-5,this.getY());
    }
}