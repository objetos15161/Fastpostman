import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author RaulLozano
 * @version 7-12-2015
 */
public class Men extends Enemy
{
    private int vida;
    
    /**
     * Constructor de Men.
     */
    public Men()
    {
        super(8,0);
        vida=0;
        for(int i = 0; i < 8;i++)
        {
            super.insertaIma(i,"men");
        }
    }
    
    /**
     * Actua de men
    */
    public void act()
    {
        this.quitaVida();
        this.movimiento();
    }
    
    /**
     * Método para animar al enemigo
     */
    public void movimiento()
    {  
       super.setIzq(true);
       this.setLocation(this.getX()-5,this.getY());
       super.animar();
    } 
    
    /**
     * Método para restar vidas al jugador
     */
    public void quitaVida()
    {
        if(isTouching(JackCartero.class)) 
        {
            if(vida == 0)
            {
                if(((Mundo)getWorld()).getLives().getValue() >= 0)
                      ((Mundo)getWorld()).setLives();
                 vida++;
           }
        }
    }
}