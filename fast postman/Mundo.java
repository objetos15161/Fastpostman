import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.LinkedList;
import java.awt.Color;

/**
 * @author RaulLozano
 * @version 7-12-2015
 */
public class Mundo extends World
{
    //medidas del mundo---------------
    public static final int TAM_X=900;
    public static final int TAM_Y=600;
    public static final int TAM_YFLOOR=530;
    public static final int TAM_XFLOOR=216;
    
   
    JackCartero jack; 
    Camion camion;

    private SimpleTimer time;
    private SimpleTimer livesT;
    private SimpleTimer pointsT;
    private SimpleTimer CartasT;
    private Counter msjClock;
    private Counter msjLives;
    private Counter msjPoints;
    private Counter msjCorreo;
    
    private LinkedList<Floor> listaF;
    private Button[] arrBotones;

    private int level;
    private boolean estaJuego;
    private boolean perdio;
    private int numEnemy;
    private boolean existeCamion;
    private ScoreBoard record;
    private Record recordAlmacena;
    private GreenfootSound intro;
    private GreenfootSound sonido;
    
    private boolean levelUno;
    private boolean levelDos;
    private boolean levelTres;
    private int puntos;
    private int acumuladorPuntos;
    
    /**
     * Constructor for objects of class mundo.
     * 
     */
    public Mundo()
    {    
        super(TAM_X,TAM_Y, 1,false);
        setActOrder(Floor.class,FloorTwo.class,Camion.class,Bolsa.class,Correo.class,JackCartero.class,Seño.class,Bala.class);
        
        puntos=0;
        recordAlmacena=new Record();
        record = new ScoreBoard(400,200);
        this.listaF=new LinkedList<Floor>();
        this.existeCamion=false;
        
        this.level=0;
        this.time=new SimpleTimer();
        this.msjClock=new Counter("Tiempo:  ");
        this.msjClock.setValue(0);

        this.livesT=new SimpleTimer();
        this.msjLives=new Counter("Vidas:  ");
        this.msjLives.setValue(10);

        this.pointsT=new SimpleTimer();
        this.msjPoints=new Counter("Puntos:  ");
        this.msjPoints.setValue(0);
        acumuladorPuntos = 0;
        
        this.CartasT=new SimpleTimer();
        this.msjCorreo=new Counter("Cartas:  ");
        this.msjCorreo.setValue(0);
        
        boolean levelUno = false;
        boolean levelDos = false;
        boolean levelTres = false;
    
        this.sonido = new GreenfootSound("walk.mp3");
        this.intro=new GreenfootSound("smash.mp3");
        this.estaJuego=false;
        this.perdio=false;
        this.creaMenu();
    }
    
    public Counter getTime()
    {
        return msjClock;
    }
    
    /**
     * Método para actualizar los records de los jugadores
     */
    public void actualizaRecord()
    {
        if (UserInfo.isStorageAvailable()) 
        {
           UserInfo myInfo = UserInfo.getMyInfo();
           if (this.getPoints().getValue() > myInfo.getScore()) 
           {
               myInfo.setScore(this.getPoints().getValue());
               myInfo.store();  
           }
        }
    }
    
    /**
     * Método para detener el sonido
     */
    public void stopped()
    {
        super.stopped();
        sonido.pause();
        intro.pause();    
    }
    
    /**
     * Método para crear una llave dependiendo el numero de puntos
     */
    public void apareceCamion()
    {
        if(this.msjPoints.getValue() == 1000 && !this.getExisteCamion()) 
        {
            this.camion=new Camion();
            this.addObject(this.camion,600,500);
            this.setExisteCamion(true);
        }
        else if(this.msjPoints.getValue() == 3000 && !this.getExisteCamion()) 
        {
            this.camion=new Camion();
            this.addObject(this.camion,600,500);
            this.setExisteCamion(true);
        }
        else if(this.msjPoints.getValue() == 5000) 
        {
            puntos=this.msjPoints.getValue();
            this.setBackground("winner.png");
            recordAlmacena.saveRecords(puntos);
            this.borrar();
             
        }
    }
    
    /**
     * Método para cambiar el estado de camion si existe o no
     * @param true o false 
     */
    public void setExisteCamion(boolean unaBan)
    {
        this.existeCamion=unaBan;
    }

    /**
     * Método para saber si existe o no camion
     * @return true o false respectivamente
     */
    public boolean getExisteCamion()
    {
        return this.existeCamion;
    }
    
    /**
     * regresa la cantidad de puntos
     * @return numero de puntos
     */
    public Counter getPoints()
    {
        return msjPoints;
    }
    
    /**
     * Método para modificar los puntos
     */
    public void setPoints()
    {
        msjPoints.add(100);
    }
    
    public Counter getCorreo()
    {
        return msjCorreo;
    }
    
    /**
     * Método para modificar los puntos
     */
    public void setCorreo()
    {
        msjCorreo.add(+1);
    }

    /**
     * Método para regresar las vidas restantes
     * @return numero de vidas
     */
    public Counter getLives()
    {
        return msjLives;
    }
    
    /**
     * Método para modificar los valores de las vidas
     */
    public void setLives()
    {
        this.msjLives.add(-1);
    }

    /**
     * Método donde interactua el mundo con todos los personajes
     */
    public void act()
    { 
      
        if(!estaJuego) 
        {  
            this.checaBotones();
        }  
        else 
        {
            this.apareceCamion();
            this.tiempo();
            this.creaEnemigos();
            this.creaCorreo();
            
            if(this.getLives().getValue() > 0 && this.getLevel() >= 1 ) 
            {
                this.mueveScroll();
            }
              
            else if(this.getLives().getValue() <= 0) 
            {
                this.setBackground("gameover.png");
                puntos=this.msjPoints.getValue();
                recordAlmacena.saveRecords(puntos);
                this.borrar();   
            }
        }
    }
   
    /**
     * Método para borrar los objetos del escenario
     */
    public void borrar()
    {
        sonido.stop();
        this.getJackCartero().setBanBolsa(false);
        this.removeObjects(this.getObjects(Personaje.class));
        this.removeObjects(this.getObjects(Counter.class));
        this.removeObjects(this.getObjects(Signboard.class));
        this.removeObjects(this.getObjects(FondoImagenes.class));
        this.removeObjects(this.getObjects(Camion.class));
        this.removeObjects(this.getObjects(Correo.class));
        this.remueveObjetos();
        this.listaF.clear();
        this.addObject(this.arrBotones[4],730,530);
        this.setLevel(0);
                
        if(this.arrBotones[4].getSedioclick()) 
        {
            this.arrBotones[4].setSedioclick(false);
            this.getJackCartero().setBanBolsa(false);
            this.msjLives.setValue(10);
            this.msjPoints.setValue(0);
            this.pintaMenu();
            this.estaJuego=false;
        }
    }
    
    /**
     * Remueve todos los personajes asi como los letreros 
     * e imagenes correspondientes del nivel actual al nivel
     * inicial
     */
    public void remueveObjetos()
    {
        this.removeObjects(this.getObjects(Personaje.class));
        this.removeObjects(this.getObjects(Counter.class));
        this.removeObjects(this.getObjects(Signboard.class));
        this.removeObjects(this.getObjects(FondoImagenes.class));   
    }

    /**
     * Método donde se inicializan los objetos principales
     */
    public void prepararMundo()
    {
        this.intro.stop();
        Floor pisito;
        FloorTwo piso1=new FloorTwo();
        this.estaJuego=true;
      
        jack = new JackCartero();
       
        this.setBackground("1.jpg"); 
        addObject(piso1,getWidth()/2,getHeight()-20);
        addObject(new Bolsa(),200,550);
        for(int i=0,xPos=TAM_XFLOOR; i < 100; i++, xPos += TAM_XFLOOR) 
        {
            pisito=new Floor();
            listaF.add(pisito);
            addObject(pisito, xPos, TAM_YFLOOR);
        }

        this.sonido.playLoop(); 
        if(this.getLevel()== 1) 
       {
            addObject(jack, 116, 200);
            addObject(msjLives,100,30);
            addObject(msjClock,220,30);
            addObject(msjPoints,340,30); 
            addObject(msjCorreo,460,30);
        }
    }
    
    /**
    * Método para desplegar el boton de ayuda
    */
    public void ayudaP()
    {
        this.setBackground("ayuda.png");
        this.addObject(this.arrBotones[4],60,500);
    }
    
    /**
     * Método para desplegar el boton de creditos
     */
    public void creditosP()
    {
        super.setBackground("creditos.png");
        this.addObject(this.arrBotones[4],60,400);
    }
    
    /**
     * Método para desplegar el boton de records
     */
    public void recordsP()
    {
        super.setBackground("records.png");
        this.addObject(this.record,400,200);
        this.addObject(this.arrBotones[4],700,500);
        if(this.arrBotones[4].getSedioclick())
        {
            this.removeObject(this.record);
        }
    }

    /**
     * Método para desplegar eliminar el menu 
     */
    public void eliminaMenu()
    {
        for(int i = 0;i < arrBotones.length-1; i++) 
        {
            this.removeObject(this.arrBotones[i]);
        }
    }
    
    /**
     * Método para desplegar todos los botones
     */
    public void creaMenu()
    {
        this.creaBotones();
    }
    
    /**
     * Método para crear los botones
     */
    public void creaBotones()
    {
        this.arrBotones=new Button[5];
        for(int i = 0;i < arrBotones.length; i++)
        {
            this.arrBotones[i]=new Button(i);
        }
        this.pintaMenu();
    }
    
    /**
     * Método para checar que botones se seleccionan
     */
    public void checaBotones()
    {
        this.intro.playLoop(); 
        for(int i = 0; i < this.arrBotones.length; i++) 
        {
            if(this.arrBotones[i].getSedioclick())
            {
                this.arrBotones[i].setSedioclick(false); 
                this.eliminaMenu();
                switch(i) 
                {
                    case 0:
                            this.setLevel(1);
                            this.estaJuego=true;
                            this.prepararMundo();
                    break;
                    case 1:
                            this.ayudaP();
                    break;
                    case 2:
                            this.recordsP();    
                    break;
                    case 3:
                            this.creditosP();
                    break;
                    case 4: 
                            this.removeObjects(this.getObjects(ScoreBoard.class));
                            this.pintaMenu();
                    break;        
                }
            }
        }
    }
    
    /**
     * Método para pintar el menu con fondo y los botones
     */
    public void pintaMenu()
    {
        this.setBackground("portada.png");
        this.removeObject(arrBotones[4]);
        for(int i = 0,y=100;i < arrBotones.length-1; i++,y+=220)
        {
            this.addObject(this.arrBotones[i],y,this.getWidth()/2-40);  
        }   
    }

    /**
     * Método que devuelve el numero actual de nivel
     * @return numero de nivel en que se encuentra
     */
    public int getLevel()
    {
        return this.level;
    }
    
    /**
     * Método para cambiar de nivel
     * @param el numero del nivel
     */
    public void setLevel(int unLevel)
    {
        this.level = unLevel;
    }

    /**
     * Método para mostrar el tiempo
     */
    public void tiempo()
    {
        if(time.millisElapsed()>=1000) 
        {
            this.time.mark();
            this.msjClock.add(+1);
        }
    }
    
    /**
     * Método donde crea estrellas en el escenario
     */
    public void creaCorreo()
    {
        int num = Greenfoot.getRandomNumber(100);
        switch(num) 
        {
            case 10:
                    addObject(new Correo(), getWidth(),getHeight()-90);
            break;
        }
    }
    
    /**
     * Método para crear enemigos aleatoriamente dependiendo del nivel
     */
    public void creaEnemigos()
    {
        numEnemy = Greenfoot.getRandomNumber(80);
        if(this.getLevel() == 1)
        {
            
            if(this.levelUno == false)
            {
                this.addObject(new Signboard("Level: 1"),650,30);
                this.levelUno = true;
            }
            
            switch(numEnemy) 
            {
                case 10:
                    addObject(new Men(), getWidth(),getHeight()-50);
                break;
            }
        }
        else if(this.getLevel() == 2)
        {
            this.setBackground("2.jpg");
             if(this.levelDos == false)
             {
                this.addObject(new Signboard("Level: 2"),650,30);
                this.levelDos = true;
            }
            
            switch(numEnemy) 
            {
                case 10:
                    addObject(new Men(), getWidth(),getHeight()-50); 
                break;
                case 20:
                    addObject(new Seño(),getWidth(),getHeight()-50);
                break;
            }
        } 
        else if(this.getLevel() == 3) 
        {
            this.setBackground("3.jpg");
             if(this.levelTres == false)
             {
                this.addObject(new Signboard("Level: 3"),650,30);
                this.levelTres = true;
            }
            switch(numEnemy) 
            {
                case 10:
                    addObject(new Seño(),getWidth(),getHeight()-50);
                break;
                case 20:
                    addObject(new Men(), getWidth(),getHeight()-50); 
                break;
                case 30:
                    addObject(new Pajaro(),getWidth(),200);
                break;
            }
        }
    }
    
    /**
     * Método para mover la parte del scrool
     */
    public void mueveScroll()
    {
       Floor inicial=listaF.getFirst();
       Floor fin=listaF.getLast();
       Floor piso;

        if(inicial.getX()+(inicial.getImage().getWidth()/2) <= 0) {
            removeObject(inicial);
            listaF.removeFirst();
        }

        if(fin.getX()+(fin.getImage().getWidth()/2) <= this.getWidth()) {
            piso=new Floor();
            addObject(piso,this.getWidth()+(piso.getImage().getWidth()/2),508);
            listaF.add(piso);
        } 
    }    
     /**
     * Regresa el personaje para poder ingresar a sus variables
     * @return el personaje usado
     */
    public JackCartero getJackCartero()
    {
        return jack;
    }
}