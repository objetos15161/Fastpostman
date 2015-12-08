import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * @author RaulLozano
 * @version 7-12-2015
 */
abstract public class Personaje extends Actor
{
    private GreenfootImage[] arrIma;
    private boolean izq;
    private int numImagenes;
    private int numImagenesAtack;
    private int imaActual;
    private boolean bandAtaca;
    private SimpleTimer time;
    
    /**
     * Constructor de personaje.
     */
    public Personaje(int numImaJug,int numImaAttack)
    {
        this.time=new SimpleTimer();
        this.arrIma=null;
        this.izq=false;
        this.numImagenes=numImaJug;
        this.imaActual=0;
        this.numImagenesAtack=numImaAttack;
        this.creaArregloIma(numImaJug);
        this.setBandAtaca(false);
    }

    /**
     * Establece una dirección para la posicion del jugador
     * @param un boolean ya sea true o false para izquierda o derecha
     */
    public void setIzq(boolean unaDir)
    {
        this.izq=unaDir;
    }
    
    /**
     * Método para obtener la posicion actual de la imagen 
     * @return una expresión boolean true o false
     */
    public boolean getIzq()
    {
        return this.izq;
    }
    
    /**
     * Método para establecer una imagen
     * @param el numero de imagen a establecer
     */
    public void setActual(int unaActual)
    {
        this.imaActual=unaActual;
    }

    /**
     * Regresar el numero de imagen actual
     * @return un entero con el numero de la imagen actual
     */
    public int getActual()
    {
        return this.imaActual;
    }

    /**
     * Número de imagenes que tiene el animal ya sea jugador o enemigo
     * @param una entero con el numero total de imagenes a usar
     */
    public void setNumIma(int unNum)
    {
        this.numImagenes=unNum;
    }
    
    /**
     * Regresar el numero total de imagenes que tiene un animal ya sea jugador o enemigo
     * @return un entero con el total de imagenes que usa
     */
    public int getNumIma()
    {
        return this.numImagenes;
    }
    
    /**
     * Crea un nuevo arreglo del tamaño de imagenes que nesesita un animal ya sea jugador o enemigo
     * @param un entero con el total de imagenes que usa para estabecer el tamaño del arreglo
     */
    public void creaArregloIma(int unTam)
    {
        this.arrIma=new GreenfootImage[(unTam+this.numImagenesAtack) * 2];
    }
    
    /**
     * Inserta la imagen del ataque en el arreglo 
     * @param unaI, la posicion en donde se insertara la imagen
     * @param String unaCad, el nombre que tiene la imagen a insertar
     */
    public void insertaAtaque(int unaI,String unaCad)
    {
        this.setPos(unaI,new GreenfootImage(unaCad+(unaI+1)+".png"));
        this.setPos(unaI+(2*numImagenes)+numImagenesAtack,new GreenfootImage(this.getPos(unaI)));
        this.getPos(unaI+(2*numImagenes)+numImagenesAtack).mirrorHorizontally();
    }
        
    /**
     * Inserta la imagen del jugador o enemigo en el arreglo 
     * @param unaI, la posicion en donde se insertara la imagen
     * @param String unaCad, el nombre que tiene la imagen a insertar
     */
    public void insertaIma(int unaI,String unaCad)
    {
        this.arrIma[unaI]=new GreenfootImage(unaCad+(unaI+1)+".png");
        this.arrIma[unaI+this.numImagenes+numImagenesAtack]=new GreenfootImage(this.arrIma[unaI]);
        this.arrIma[unaI+this.numImagenes+numImagenesAtack].mirrorHorizontally();
    }   

    abstract public void movimiento();

    /**
     * Método para animar a los animales del bosque con sus imagenes
     */
    public void animar()
    {  
     if(!this.getIzq())
     {
         if(!this.getBandAtaca())
         {
             if(this.getActual() >= 0 && getActual() < this.numImagenes)
             {
                 setImage(this.getPos(this.getActual()));
                 this.setActual(this.getActual()+1);
             }
             else
                 this.setActual(0);
         }
         else
         {
             if(this.getActual() >= this.numImagenes && this.getActual() < numImagenes+numImagenesAtack)
             {        
                setImage(this.getPos(this.getActual()));
                this.setActual(this.getActual()+1);
             }
             else
                 this.setActual(numImagenes);
         }
     }
     else
     {
         if(!this.getBandAtaca())
         {
             if(this.getActual() >= numImagenes+numImagenesAtack && this.getActual() < 2*this.numImagenes+numImagenesAtack)
             {
                setImage(this.getPos(this.getActual()));
                this.setActual(this.getActual()+1);
             }
             else
                this.setActual(numImagenes+numImagenesAtack);
         }
         else
         {
             if(this.getActual() >= 2*this.numImagenes+numImagenesAtack && this.getActual() < (2*numImagenes)+(2*numImagenesAtack))
             {
                setImage(this.getPos(this.getActual()));
                this.setActual(this.getActual()+1);
             }
             else
                this.setActual(2*numImagenes+numImagenesAtack);
         }
     }  
    }
    
    /**
     * Método para cambiar la bandera de atacar ya sea true o false
     * @param un boolean yasea true o false
     */
    public void setBandAtaca(boolean unaBan)
    {
        this.bandAtaca=unaBan;
    }
    
    /**
     * Método para conocer la bandera de ataque
     * @return true o false
     */
    public boolean getBandAtaca()
    {
        return this.bandAtaca;
    }
    
    /**
     * Método para crear un objeto bala en el mundo simulando un diparo
     * @param un tipo de imagen a disparar
     * @param una posicion en x 
     * @param una posicion en y
     */
    public void disparar(int unTipo,int x,int y)
    {
        if(time.millisElapsed()>=1000) 
        {
            this.time.mark();
            getWorld().addObject(new Bala(unTipo,this.getIzq()),x,y);
        }
    }
    
    /**
     * Regresa la posicion actual del personaje
     * @param una posicion
     * @return la imagen en la posicion recibida como parametro
     */
    public GreenfootImage getPos(int unaPos)
    {
        return this.arrIma[unaPos];
    }
    
    /**
     * Establecer una imagen en la posicion recibida como parametro
     * @param una posicion
     * @param una imagen para establecerla en la posicion 
     */
    public void setPos(int unaPos,GreenfootImage unaImagen)
    {
        this.arrIma[unaPos]=unaImagen;
    }
}