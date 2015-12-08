import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author RaulLozano
 * @version 7-12-2015
 */
public class Seño extends Enemy
{
    private int cont;

    /**
     * Constructor de Arbok.
     */
    public Seño()
    {
        super(6,2);
        this.cont = 0;
        for(int i = 0; i < 8; i++)
        {
            super.insertaIma(i,"señora");
        }
    }

    /**
     * Actua del enemigo seño
     */
    public void act()
    {
        this.movimiento();
    }

    /**
     * Método para animar el movimiento de seño
     */
    public void movimiento()
    {  
        super.setIzq(true);
        this.setLocation(getX()-2,getY());
        this.animar();
        if(this.cont == 80) 
        {
            super.setBandAtaca(true);
            for(int i=0; i < super.getnumAtaca(); i++)
            {
                super.animar();
            }
            super.disparar(3,this.getX(),this.getY());
            super.setBandAtaca(false);
            this.cont=0;
        }
        this.cont++;
    }
}