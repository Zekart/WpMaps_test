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
public enum DrawWellOwner {

    UKRTELECOM("УкрТелеком");
    
    private String drawWell_owner;

    private DrawWellOwner(String drawWell_owner) {
        this.drawWell_owner = drawWell_owner;
    }
    
    public String getDrawWellOwner() {
        return drawWell_owner;
    }     
}
