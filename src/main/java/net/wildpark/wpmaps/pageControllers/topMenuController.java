/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.pageControllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

@Named(value="topMenuController")
@SessionScoped

public class topMenuController implements Serializable{
    
    private boolean redirection = false;
    private String imgName = "basic";
    private String password;
    
    
    public String linkAction(ActionEvent actionEvent) {
        
        if(redirection != true){    
            
            redirection = true;            
            imgName = "powerfull";
            return "home?faces-redirect=true";

        }else{
            redirection = false;
            imgName = "basic";               
            return "basicPage?faces-redirect=true";             
        }
    }
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isRedirection() {
        return redirection;
    }

    public void setRedirection(boolean redirection) {
        this.redirection = redirection;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
    
    
}
