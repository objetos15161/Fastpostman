import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.UserInfo; 

/**
 * @author RaulLozano
 * @version 7-12-2015
 */
public class Record
{
    private UserInfo jugador;
    
    public Record() 
    { 
        if((UserInfo.isStorageAvailable()) && (UserInfo.getMyInfo() != null)) 
        {
            this.jugador = UserInfo.getMyInfo();
        }
    }
    
    public void saveRecords(int points)
    {
        if((UserInfo.isStorageAvailable()) && (this.jugador != null) && ((points> this.jugador.getScore())) || (this.jugador.getInt(0))==0)
        { 
            this.jugador.setScore(points);
            this.jugador.setInt(0,1); 
            this.jugador.store();
        }
        
        if((UserInfo.isStorageAvailable()) && (points > this.jugador.getScore()) && (this.jugador != null) )
        {
            this.jugador.setScore(points);
            this.jugador.setInt(0,1); 
            this.jugador.store();
        }
    }    
}