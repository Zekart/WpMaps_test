/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.enums;

public enum PillarMaterial{
    CONCRETE("Бетонный"),
    METALLIC("Металический"),
    WOODEN("Деревянный");
    
    private String pillar_material;

    private PillarMaterial(String pillar_material) {
        this.pillar_material = pillar_material;
    }

    public String getPillarMaterial() {
        return pillar_material;
    }
 
}