/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.pageControllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.FlowEvent;

 
@ManagedBean
@SessionScoped
public class PointWizard implements Serializable {
 
    private boolean skip = false;
    
     
    public boolean isSkip() {
        return skip;
    }
    
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
     
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = true;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
        
    }

    
    

}