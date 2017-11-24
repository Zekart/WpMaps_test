/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.basicPageControllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import net.wildpark.wpmaps.entitys.Pillar;
import net.wildpark.wpmaps.facades.PillarFacade;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author Zekart
 */
@ManagedBean
@SessionScoped
public class Find_controller implements Serializable{
    
    @EJB
    private PillarFacade pillarFacade;
    
    private List<Pillar> pillar_list;
    
    private String txt1;

    private LatLng coordinate;
    
    private int id;
    
    public Find_controller() {
        
    }
    @PostConstruct
    public void initPoint() {
        pillar_list = pillarFacade.findAll();
    }
    
    public List<String> completeText(String query) {
        List<String> results = new ArrayList<>();

        for(int i = 0; i < pillar_list.size(); i++) {
            if (pillar_list.get(i).getTransportStation().contains(query)) {
                results.add(pillar_list.get(i).getTransportStation());
            }else if(String.valueOf(pillar_list.get(i).getNumberStation()).contains(query)){
                results.add(String.valueOf(pillar_list.get(i).getNumberStation()));
            }          
            
        }         
        if (results.isEmpty()) {
            results.add("Нету результатов");
        }
        
        return results;
    }
    

    public void action(SelectEvent event){
        String selected_obj;
        selected_obj = event.getObject().toString();

        for (Pillar pillar : pillar_list) {
            if (pillar.getTransportStation().equals(selected_obj) || pillar.getTransportStation().equals(selected_obj) ) {
                coordinate = new LatLng(pillar.getLat(), pillar.getLng());
            }else{
                
            }
        }
        System.out.println(coordinate);
        
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

    public LatLng getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(LatLng coordinate) {
        this.coordinate = coordinate;
    }  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
