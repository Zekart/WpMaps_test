/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.enums;

public enum PillarType{
    CIRCULAR("Круглый"),
    SQUARE("Квадратный"),
    HEXAGONAL("Шестиугольный");
    
    private String pilar_type;

    private PillarType(String pilar_type) {
        this.pilar_type = pilar_type;
    }
    
    public String getPilarType() {
        return pilar_type;
    }
    
}