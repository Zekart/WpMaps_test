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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import net.wildpark.wpmaps.entitys.Clutch;
import net.wildpark.wpmaps.entitys.MapPoint;
import net.wildpark.wpmaps.facades.ClutchFacade;
import net.wildpark.wpmaps.facades.PointFacade;

/**
 *
 * @author zekart
 */
@Named(value = "clutchController")
@SessionScoped
public class ClutchController implements Serializable {


    @EJB
    private PointFacade pointFacade;
    @EJB
    private ClutchFacade clutchFacade;
        
    DrawWellController draw;
    Clutch clutch_o = new Clutch();
    
    private String txt1;
        
    private List<MapPoint> mapPoint_list;
    
    @PostConstruct
    public void initPoint() {
        mapPoint_list = pointFacade.findAll();
    }

    
    public ClutchController() {

    }
        
    public List<String> completeText(String query) {
        List<String> results = new ArrayList<>();

        for(int i = 0; i < mapPoint_list.size(); i++) {
            if (mapPoint_list.get(i).getAddress().contains(query)) {
                results.add(mapPoint_list.get(i).getAddress());
            }                     
        }         
        if (results.isEmpty()) {
            results.add("Нету результатов");
        }
        
        return results;
    } 

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public List<MapPoint> getMapPoint_list() {
        return mapPoint_list;
    }

    public void setMapPoint_list(List<MapPoint> mapPoint_list) {
        this.mapPoint_list = mapPoint_list;
    }

    public ClutchFacade getClutchFacade() {
        return clutchFacade;
    }

    public void setClutchFacade(ClutchFacade clutchFacade) {
        this.clutchFacade = clutchFacade;
    }

    public Clutch getClutch_o() {
        return clutch_o;
    }

    public void setClutch_o(Clutch clutch_o) {
        this.clutch_o = clutch_o;
    }   
    
}
