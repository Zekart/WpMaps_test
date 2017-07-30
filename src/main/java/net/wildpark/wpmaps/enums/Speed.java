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
public enum Speed {
    M100("100/100 Mbps"),M1000("1/1 Gbps"),M10000("10/10 Gbps");
    private String about;
    
    Speed(String about){
        this.about=about;
    }
    
    public String toString(){
        return about;
    }
}
