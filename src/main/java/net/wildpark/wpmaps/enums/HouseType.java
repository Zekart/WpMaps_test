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
public enum HouseType {
    
    SINGLE("Частный"),
    MULTYAPARTMENTS("Многоквартирный");
    
    private String house_type;

    private HouseType(String house_type) {
        this.house_type = house_type;
    }
    
    public String getHouseType() {
        return house_type;
    }   
}
