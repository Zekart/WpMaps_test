/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.enums;

/**
 *
 * @author Panker-RDP
 */
public enum PortType {
    FIBER("Оптический"),CUPRUM("RG-45"),SFP("SFP модуль");
    String about;
    PortType(String about){
        this.about=about;
    }
    
    public String toString(){
        return about;
    }
}
