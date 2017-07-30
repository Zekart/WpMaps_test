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
public enum ConnectionPillar{
    SINGLEMODE("Одномодовый"),
    MULTYMODE("Многомодовый");
    
    private String connection_type;

    private ConnectionPillar(String connection_type) {
        this.connection_type = connection_type;
    }

    public String getConnection_type() {
        return connection_type;
    }

}