import greenfoot.*;

/**
 * @author RaulLozano
 * @version 7-12-2015
 */
public class Floor extends FondoImagenes
{
    
    /**
     * Actua de Floor
     */
    public void act()
    {
        if(Greenfoot.isKeyDown("right"))
        {
          this.setLocation(getX()-2,getY());
        }
    }
}
