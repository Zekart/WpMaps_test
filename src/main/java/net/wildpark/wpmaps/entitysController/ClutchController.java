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
import javax.faces.event.ActionEvent;
import net.wildpark.wpmaps.entitys.Clutch;
import net.wildpark.wpmaps.entitys.MapPoint;
import net.wildpark.wpmaps.entitys.Pillar;
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

    
    private List<Pillar> pillar_list;

    private String txt1;
    
    private Clutch cl = new Clutch();
    
    private List<Clutch> added_clutch = new ArrayList<>();
    
    private List<Clutch> clutch_list;
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

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public List<Pillar> getPillar_list() {
        return pillar_list;
    }

    public void setPillar_list(List<Pillar> pillar_list) {
        this.pillar_list = pillar_list;
    }

    public List<Clutch> getClutch_list() {
        return clutch_list;
    }

    public void setClutch_list(List<Clutch> clutch_list) {
        this.clutch_list = clutch_list;
    }

    public List<MapPoint> getMapPoint_list() {
        return mapPoint_list;
    }

    public void setMapPoint_list(List<MapPoint> mapPoint_list) {
        this.mapPoint_list = mapPoint_list;
    }
    
    
    
    
}
