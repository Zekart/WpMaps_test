/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.pageControllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import net.wildpark.wpmaps.entitys.Clutch;

@Named(value = "AddController")
@SessionScoped

public class pointAddController implements Serializable{

    List<Clutch> clutc_rend = new ArrayList<>();
    
    private double lat;     
    private double lng;
    private int menuIndex = 0;
    private String selectObj;
    private int idClutch;
    private int idCabel;
    
    @PostConstruct
    public void init() {selectObj = "select";}
    
    public pointAddController() {
    }
      
    
    public void changeIndex(){
        menuIndex++;
        if (menuIndex >=5) {
            menuIndex = 0;
        } 
    }
    
    public void goBack() throws IOException{
        if (menuIndex == 0) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
        }else{
            menuIndex--;
        }
    } 

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
   
    
    public int getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(int menuIndex) {
        this.menuIndex = menuIndex;
    }

    public String getSelectObj() {
        return selectObj;
    }

    public void setSelectObj(String selectObj) {
        this.selectObj = selectObj;
    }

    public int getIdClutch() {
        return idClutch;
    }

    public void setIdClutch(int idClutch) {
        this.idClutch = idClutch;
    }

    public int getIdCabel() {
        return idCabel;
    }

    public void setIdCabel(int idCabel) {
        this.idCabel = idCabel;
    }

    

    
    
    
    
}
