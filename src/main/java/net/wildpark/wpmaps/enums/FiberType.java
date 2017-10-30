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
public enum FiberType{
    SINGLEMODE("Одномодовый"),
    MULTYMODE("Многомодовый");
    
    private String fiber_type;

    private FiberType(String fiber_type) {
        this.fiber_type = fiber_type;
    }

    public String getFiber_type() {
        return fiber_type;
    }

}