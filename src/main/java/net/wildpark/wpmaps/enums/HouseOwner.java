/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.enums;

/**
 *
 * @author Zekart
 */
public enum HouseOwner {
    
    GKX("ЖКХ"),
    OSMD("ОСМД");
    
    private String house_owner;

    private HouseOwner(String house_owner) {
        this.house_owner = house_owner;
    }
    
    public String getHouseOwner() {
        return house_owner;
    }       
}
