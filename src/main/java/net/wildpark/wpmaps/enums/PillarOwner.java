/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.enums;

public enum PillarOwner{
    OBL_ENERGO("Облэнерго"),
    EL_TRANCE("Электротранс"),
    GOR_SVET("ГОРСВЕТ"),
    UKR_TELECOM("Укртелеком"),
    PRIVATE_USER("Частник");
    
    private String owner_type;

    private PillarOwner(String owner_type) {
        this.owner_type = owner_type;
    }

    public String getOwnerType() {
        return owner_type;
    }
        
}