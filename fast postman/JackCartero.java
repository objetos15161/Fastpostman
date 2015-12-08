import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * @author RaulLozano
 * @version 7-12-2015
 */
public class JackCartero extends Player
{
    private boolean ataque;
    private int posicion;
    private int cont;
    
    /**
     * Constructor de jack.
     */
    public JackCartero()
    {
        super(4,4,2);
        this.ataque = false;
        this.cont = 0;
        for(int i = 0; i < 10; i++)
        {
            super.insertaIma(i,"camina");
        }
    }
    
    /**
     * Actua de jack*/
    public void act()
    {
        this.movimiento();
        super.caida();
        super.tocoBolsa();
    }
    
    /**
     * Método para animar el movimiento del jugador
     */
    public void movimiento()
    {   
        if((Greenfoot.isKeyDown("right")) && ataque == false) {
            super.setIzq(false);
            if(this.getX() < getWorld().getWidth()/2)
            {           
                this.setLocation(getX()+super.getVelJugador(),getY());
            }
            super.animar();
        }

        if((Greenfoot.isKeyDown("left")) && ataque == false) {
            super.setIzq(true);
            if(this.getX()+10 > 0) {
                setLocation(getX()-super.getVelJugador(),getY());
            }
            super.animar();
        }

        if((Greenfoot.isKeyDown("up")) && ataque == false) {
            super.saltar();
        }
        
        if(Greenfoot.isKeyDown("a")) 
        {
            if(this.cont == 8) 
            {
                super.setBandAtaca(true);
                if(!super.getBanBolsa())
                {
                for(int i=0; i < super.getnumAtaca(); i++)
                {
                    super.animar();
                }
                super.disparar(1,this.getX(),this.getY());
                super.setBandAtaca(false);
                this.cont=0;
                }
            }
            this.cont++;
        }
    }
}