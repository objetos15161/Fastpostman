import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author RaulLozano
 * @version 7-12-2015
 */
public class Pajaro extends Enemy
{  
   private int disparo;
    
    /**
     * Constructor de Pajaro.
     */
   public Pajaro()
   {
      super(14,0);
      for(int i = 0; i < 14;i++) 
      {
          super.insertaIma(i,"paj");
      }
   }
   
    /**
     * Actua de pajaro*/
   public void act()
   {
       this.movimiento();
       this.atacaJugador();
   }
   
   /**
    * Método para animar al enemigo
    */
   public void movimiento()
   {  
       super.setIzq(false);
       this.setLocation(getX()-3,getY());
       super.animar();
   }
   
   /**
    * Método para atacar del enemigo 
    */
   public void atacaJugador()
   {
     this.disparo = Greenfoot.getRandomNumber(100);
     if( this.disparo == 50 ) 
     { 
         super.disparar(4,this.getX(),this.getY());
     }
   }
}