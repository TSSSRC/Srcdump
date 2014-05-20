/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scoringsystem.fabio;

import java.io.Serializable;

/**
 *
 * @author Fabio
 */
@SuppressWarnings("serial")
public class DataPackage implements Serializable {

    /**
     * 
     */
    public int ID = -1;
    /**
     * 
     */
    public String message="";
    public String username = "";
    /**
     * 
     */
    public int level;
    public int typeCreepSpawn = -1;
    /**
     * 
     */
    public int livesLost = 0;
    //public int upgradeCreeps = 0;  
    //public User user;
    /**
     * 
     */
    public float x = 0;
    /**
     * 
     */
    public float y = 0;

    public int[][] grid;
    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        return "ID: " + ID + "\tUsername: " + username + "\tCreep Spawn: " + typeCreepSpawn;
    }
}
