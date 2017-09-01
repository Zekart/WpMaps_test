/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.pageControllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import net.wildpark.wpmaps.entitys.Clutch;
import net.wildpark.wpmaps.entitys.DrawWell;
import net.wildpark.wpmaps.enums.DrawWellOwner;
import net.wildpark.wpmaps.enums.DrawWellType;
import net.wildpark.wpmaps.facades.DrawWellFacade;
import net.wildpark.wpmaps.facades.HouseFacade;
import net.wildpark.wpmaps.facades.PillarFacade;
import net.wildpark.wpmaps.facades.PointFacade;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;

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
    

    
    
    public DrawWellOwner[] getDrawWellOwner() {
        return DrawWellOwner.values();
    }
    public DrawWellType[] getDrawWellType() {
        return DrawWellType.values();
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
