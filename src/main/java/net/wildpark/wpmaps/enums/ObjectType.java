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
public enum ObjectType {
    PILLAR("Столб"),
    HOUSE("Дом"),
    DRAW_WELL("KKC");
    
    private String object_type;

    public String getObject_type() {
        return object_type;
    }

    private ObjectType(String object_type) {
        this.object_type = object_type;
    }
    
    

    
}
