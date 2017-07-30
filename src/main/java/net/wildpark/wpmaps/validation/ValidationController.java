/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.validation;

import javax.annotation.ManagedBean;

/**
 *
 * @author Zekart
 */

@ManagedBean
public class ValidationController {
    private String text;
    private Integer integer;
 
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
 
    public Integer getInteger() {
        return integer;
    }
    public void setInteger(Integer integer) {
        this.integer = integer;
    }  
}
