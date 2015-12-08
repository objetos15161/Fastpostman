import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author RaulLozano
 * @version 7-12-2015
 */
abstract public class Player extends Personaje
{
    private int numImagen;
    //Salto------------------
    private int velocidad;
    private int velocidadSalto;
    private int aceleracion;
    private boolean salto;
    private int jump;
    private int tiempo;
    private boolean estaSaltando;
    private int velJugador;
    private int numJug;
    private int numTes;
    private int numAtaca;
    private boolean bandBolsa;

    /**
     * Constructor de Player.
     */
    public Player(int numJug,int numTes,int numImaAtack)
    {
        super(numJug+numTes,numImaAtack);
        this.numJug=numJug;
        this.numTes=numTes;
        this.setnumAtaca(numImaAtack);
        this.bandBolsa=false;
        
        this.setNumImagen(0);
        this.velocidad = 7;
        this.velocidadSalto = 0;
        this.aceleracion = 2;
        this.salto = false;
        this.jump = 10;
        this.tiempo = 0;
        this.estaSaltando=true;
        this.setVelJugador(3);
    }
    
    /**
     * Regresa el valor dela velocidad del jugador
     * @return un numero entero con el valor de la velocidad del jugador
     */
    public int getVelJugador()
    {
        return this.velJugador;
    }
    
    /**
     * Establecer el valor de la velocidad del jugador
     * @param un numeor entero con el valor a establecer de la velocidad
     */
    public void setVelJugador(int unNum)
    {
        this.velJugador=unNum;
    }
    
    /**
     * Establecer un numero de imagen
     * @param un numero entero para el numero de imagen
     */
    public void setNumImagen(int unNum)
    {
        this.numImagen=unNum;
    }
    
    /**
     * Regresa el valor del numero de imagen
     * @return un numero entero con el valor del numero de imagen
     */
    public int getNumImagen()
    {
        return this.numImagen;
    }
    
    abstract public void movimiento();
    
    /**
     * Método para checar si esta saltando
     */
    public void saltar()
    {
        if(!this.estaSaltando)
        {
            this.estaSaltando=true;    
            this.velocidadSalto = -this.jump;
            this.caer();
        }
    }
     
    /**
     * Método para checar si esta tocando el suelo
     * @return true o false dependiendo de si lo esta o no tocando
     */
    public boolean estaEnPiso()
    {
        boolean avisa;
        if(!isTouching(FloorTwo.class))
            avisa=false;
        else
            avisa=true;
            
        return avisa;
    }
    
    /**
     * Método para establecer y modificar sus coordenadas al momento de caer
     */
    public void caida()
    {
        if(this.estaEnPiso()) {
            this.velocidadSalto = 0;
        }
        else {
            this.caer();
        }   
    }
     
    /**
     * Método para hacer que el jugador vaya hacia abajo
     */
    public void caer()
    {
        this.setLocation(getX(),getY()+velocidadSalto);
        this.velocidadSalto = this.velocidadSalto + this.aceleracion;
        this.estaSaltando=false;
    }  
    
    /**
     * Método para cambiar de imagen
     * @param una imagen a establecer
     * @param un numero de imagen
     * 
     */
    public void cambiaImagen(GreenfootImage unaImagen,int numI)
    {
         this.setImage(unaImagen);
         this.setNumImagen(numI);
    }
    
    /**
     * Método para insertar la imagen 
     * @param una posicion en el arreglo
     * @param un nombre de la imagen
     */
    public void insertaIma(int unaI,String unaCad)
    {
        super.setPos(unaI,new GreenfootImage(unaCad+(unaI+1)+".png"));
        super.setPos(unaI+this.numJug+this.numTes+this.numAtaca,new GreenfootImage(this.getPos(unaI)));
        super.getPos(unaI+this.numJug+this.numTes+this.numAtaca).mirrorHorizontally();
    }   

    /**
     * Método para animar al jugador checando desde si toca el tesoro hasta cambiar el numero de imagen a usar 
     */
    public void animar()
    {
        Mundo mundo;
        mundo=((Mundo)this.getWorld());
        int inicio,fin;
        
        if(!super.getIzq())
        {
            if(!this.getBanBolsa())
            {
                if(!super.getBandAtaca())
                {
                    inicio=0;
                    fin=this.numJug;
                }
                else
                {
                    inicio=this.numJug+numTes;
                    fin=this.numJug+numTes+numAtaca;
                }
            }
            else
            {
                inicio=this.numJug;
                fin=this.numJug+numTes;
            }
        }
        else
        {
            if(!this.getBanBolsa())
            {
                if(!super.getBandAtaca())
                {
                    inicio=this.numJug+this.numTes+this.numAtaca;
                    fin=(2*this.numJug)+numTes+numAtaca;
                }
                else
                {
                    inicio=(2*numJug)+(2*numTes)+numAtaca;
                    fin=(2*numAtaca)+(2*numJug)+(2*numTes);
                }
            }
            else
            {
                inicio=(2*this.numJug)+numTes+numAtaca;
                fin=(2*numJug)+(2*numTes)+numAtaca;
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
     * Cambiar la bandera del estado del tesoro si lo toco o no
     * @param true o false dependiendo
     */
    public void setBanBolsa(boolean unaBan)
    {
        this.bandBolsa=unaBan;
    }
    
    /**
     * Obtener el estado de la bandera del tesoro
     * @return true o false
     */
    public boolean getBanBolsa()
    {
        return this.bandBolsa;
    }
    
    /**
     * Establece el numero de imagen a atacar
     * @param un numero entero
     */
    public void setnumAtaca(int unNum)
    {
        this.numAtaca=unNum;
    }
    
    /**
     * Obtener el numero de imagen atacando
     * @return un numeor entero
     */
    public int getnumAtaca()
    {
        return this.numAtaca;
    }
    
    /**
     * Método para checar si toco el tesoro
     */
    public void tocoBolsa()
    {
        Mundo mundo=(Mundo)getWorld();
        if(isTouching(Bolsa.class))
        {
            mundo.removeObjects(mundo.getObjects(Bolsa.class));
            this.setBanBolsa(false);
        }
    }
}