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
public enum PillarCapacity{
    EMPTY("Пустой"),
    HASCLUTCH("Содержит муфту"),
    HASCABELCAPACITY("Содержит запас кабеля");
    
    private String capacity_pillar;

    private PillarCapacity(String capacity_pillar) {
        this.capacity_pillar = capacity_pillar;
    }
    
    public String getCapacity_pillar() {
        return capacity_pillar;
    }



}