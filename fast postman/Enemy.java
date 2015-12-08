import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author RaulLozano
 * @version 7-12-2015
 */
abstract public class Enemy extends Personaje
{
    private int numeroImagen;
    private int numEne;
    private int numAtaca;
    
    /**
     * Constructor de Enemy.
     */
    public Enemy(int numImagenes,int numImaAtack)
    {
        super(numImagenes,numImaAtack);
        this.numEne=numImagenes;
        this.numAtaca=numImaAtack;
        this.setNum(0);
    }
    
    /**
     * actua del jugador
     */
    public void act() 
    {
        this.setLocation(this.getX()-2,this.getY());
    }
    
    /**
     * Establecer numero de imagen
     * @param un numero de imagen
     */
    public void setNum(int unNum)
    {
        this.numeroImagen=unNum;
    }
   
    /**
     * Obtener numero de imagen
     * @return un numero de imagen
     */
    public int getNum()
    {
        return this.numeroImagen;
    }
    
    /**
     * Cambia la imagen actual por otra 
     * @param recibe la siguiente imagen a establecer
     * @param un numero de imagen a establecer
     */
    public void cambiaImagen(GreenfootImage unaImagen,int num)
    {
        this.setImage(unaImagen);
        this.setNum(num);
    }
    
    abstract public void movimiento();
    
    /**
     * Movimiento para animar al enemigo cambiando la badera de su posicion ya sa izquierda o derecha
     * asi como cambiando las imagenes y estableciendo siguientes imagenes
     */
    public void animar()
    {
        Mundo mundo;
        mundo=((Mundo)this.getWorld());
        int inicio,fin;
        
        if(!super.getIzq())
        {
            if(!super.getBandAtaca())
            {
                inicio=0;
                fin=this.numEne;
            }
            else
            {
                inicio=this.numEne;
                fin=this.numEne+numAtaca;
            }
        }
        else
        {
            if(!super.getBandAtaca())
            {
                inicio=this.numEne+this.numAtaca;
                fin=(2*this.numEne)+numAtaca;
            }
            else
            {
                inicio=(2*this.numEne)+numAtaca;
                fin=(2*numAtaca)+(2*numEne);
            }
        }
        if(super.getActual() >= inicio && super.getActual() < fin)
        {
            this.setImage(super.getPos(super.getActual()));
            super.setActual(super.getActual()+1);
        }
        else
            super.setActual(inicio);
    } 
    
    /**
     * Establece un numero de iamgen
     *@param un numero entero para establecer 
     */
     public void setnumAtaca(int unNum)
    {
        this.numAtaca=unNum;
    }
    
    /**
     * Devuelve el numero de imagen atacando
     * @param el valor entero de ataque
     */
    public int getnumAtaca()
    {
        return this.numAtaca;
    }
}