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
public enum TypeConnection {
    Optic("Оптоволокно"),
    TwistedPair("Витая пара"),
    Optic_TwistedPair("Оптика и витая пара"),
    ElseConnection("Другое");
    
    private String type_connection;

    private TypeConnection(String type_connection) {
        this.type_connection = type_connection;
    }
    
    public String getTypeConnection() {
        return type_connection;
    }   
    
}
