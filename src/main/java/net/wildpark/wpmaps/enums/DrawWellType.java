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
public enum DrawWellType {
    ККС1("ККС-1-80"),
    KKC2("ККС-2-10"),
    KKC3("ККС-3-10");
    
    private String drawWell_type;

    private DrawWellType(String drawWell_type) {
        this.drawWell_type = drawWell_type;
    }
    
    public String getDrawWellType() {
        return drawWell_type;
    }    
}
