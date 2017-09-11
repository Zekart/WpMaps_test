/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitysController;

import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.wildpark.wpmaps.entitys.Clutch;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author zekart
 */
@Named(value = "clutchController")
@SessionScoped
public class ClutchController implements Serializable {

    private Clutch cl = new Clutch();
    
    private List<Clutch> added_clutch = new ArrayList<>();
    
    public ClutchController() {
    }
    
    public List<Clutch> takeList(){
        return this.added_clutch;
    }
    
    public void delLine(ActionEvent actionEvent) {
        this.added_clutch.clear();
    }
    public void newLine(ActionEvent actionEvent) {
        this.added_clutch.add(new Clutch());     
    }

    public Clutch getCl() {
        return cl;
    }

    public void setCl(Clutch cl) {
        this.cl = cl;
    }

    public List<Clutch> getAdded_clutch() {
        return added_clutch;
    }

    public void setAdded_clutch(List<Clutch> added_clutch) {
        this.added_clutch = added_clutch;
    }
    
    
    
}
