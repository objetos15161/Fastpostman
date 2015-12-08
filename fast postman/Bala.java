import greenfoot.*;

/**
 * @author RaulLozano
 * @version 7-12-2015
 */
public class Bala extends Actor
{
    private int tipo;
    private boolean banDir;
    private int cont;
    private int pause;
    
    /**
     * Constructor de Bala.
     */
    public Bala(int unTipo,boolean unaDir)
    {
        this.pause = 10;
        this.tipo=unTipo;
        this.setBanDir(unaDir);
        this.quienFue();
        this.cont=0;
    }
    
    /**
     * Método donde pone la bandera de direccion en true o false
     * @param true o false
     */
    public void setBanDir(boolean unaDir)
    {
        this.banDir=unaDir;
    }
    
    /**
     * Método donde donde regresa la bandera de dirección
     * @return true o false
     */
    public boolean getBanDir()
    {
        return this.banDir;
    }
    
    /**
     * Metodo donde se switchea quien de todos disparo
     */
    public void quienFue()
    {
        switch(this.tipo)
        {
            case 1://jack
                    this.setImage(new GreenfootImage("sobre.png"));
                    this.getImage().scale(this.getImage().getWidth()/3,this.getImage().getHeight()/3);
            break;
            case 2://
                    this.setImage(new GreenfootImage("ataca2.png"));
                    this.getImage().scale(this.getImage().getWidth()/3,this.getImage().getHeight()/3);
            break;
            case 3://arbok
                    this.setImage(new GreenfootImage("burg.png"));
                    this.getImage().scale(this.getImage().getWidth()/3,this.getImage().getHeight()/3);
            break;
            case 4://yanmega
                    this.setImage(new GreenfootImage("ataca4.png"));
                    this.getImage().scale(this.getImage().getWidth()/3,this.getImage().getHeight()/3);
            break;
        }
        if(this.getBanDir())
        {
            this.getImage().mirrorHorizontally();
        }
    }
    
    /**
     * Método donde se mueve la bala de cada animal del bosque
     */
    public void mueveBala()
    {
        switch(this.tipo)
        {
            case 1:
            case 2:
            case 3:
                   this.setLocation(getX()+ (!this.getBanDir() ? 10 : -10),getY());
            break;
            case 4:
                   this.setLocation(getX(),getY()+5);
            break;
        }
    }
    
    /**
     * Método donde interactua la bala con cada animal del bosque
     */
    public void aquienToque()
    {
        Actor aux;
        Mundo mundo=(Mundo)getWorld();
        
        switch(this.tipo)
        {
            case 1:
            case 2: this.aquienElimino();
                    mundo.setPoints();                    
            break;
            case 3:
                    aux=this.getOneObjectAtOffset(!this.getBanDir() ? 100 : -100,0,Player.class);
                    if(aux != null)
                    {
                           if(((Mundo)getWorld()).getLives().getValue() > 0)
                           {
                               ((Mundo)getWorld()).setLives();
                               ((Mundo)getWorld()).removeObject(this);
                           }
                    }
            break;    
            case 4:
                   aux=this.getOneObjectAtOffset(0,100,Player.class);
                   if(aux != null)
                   {
                       if(((Mundo)getWorld()).getLives().getValue() > 0)
                       {
                           ((Mundo)getWorld()).setLives();
                           ((Mundo)getWorld()).removeObject(this);
                       }
                   } 
            break;
        }
    }
    
    /**
     * Método donde se verifica a quien toco la bala
     */
    public void aquienElimino()
    {
        Actor aux;
        Xplosive mancha;
        Mundo mundo=(Mundo)getWorld();
        
        aux=this.getOneObjectAtOffset(!this.getBanDir() ? 100 : -100,0,Enemy.class);
        if(aux != null)
        {
            mundo.removeObject(aux);
            if(aux instanceof Men)
            {
                mancha=new Xplosive("men.png");
                mundo.addObject(mancha,this.getX(),this.getY());
            }        
            else if(aux instanceof Seño)
            {
                mancha=new Xplosive("seño.png");
                mundo.addObject(mancha,this.getX(),this.getY());
            }        
            else if(aux instanceof Pajaro)
            {
                mancha=new Xplosive("ManchaVerde.png");
                mundo.addObject(mancha,this.getX(),this.getY());
            }
        }
    }
    
    /**
     * Actua de Bala
     * 
     */
    public void act() 
    {
        this.mueveBala();
        this.aquienToque();
    }
}