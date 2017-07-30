/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.pageControllers;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import net.wildpark.wpmaps.entitys.Clutch;
import net.wildpark.wpmaps.facades.HouseFacade;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Zekart
 */
@Named(value = "gMapsClutchController")
@SessionScoped

public class gmapClutchController implements Serializable{
   
    @EJB
    private HouseFacade mapClutchFacade;
    private MapModel modelClutch;
    private List<Clutch> list; 
    private Integer data = 0;
    private EntityManager manager;
    Clutch selectedPillar = new Clutch();
    
    private String centerClutch;
        
//    @PostConstruct
//    public void init() {       
//        modelClutch = new DefaultMapModel();
//        list = mapClutchFacade.findAll();
//
//        data = showResult();
//
//        for (Pillar pil : list) {
//            if(pil.getId() == data){
//                modelClutch.addOverlay(new Marker(new LatLng(pil.getLat(), pil.getLng()),String.valueOf(pil.getId()),pil,"../resources/marker/"+pil.getOwner()+"/"+pil.getCapacityPillar()+".png"));                
//                centerClutch =  setCenter(pil.getLat(), pil.getLng());
//            }
//        }       
//        
//        System.out.println(selectedPillar.getId());
//
//    }
    
    public String setCenter(Double lat, Double lng){
        centerClutch = lat +","+lng;
        return centerClutch;
    }
    
   public Integer showResult() {
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = 
         fc.getExternalContext().getRequestParameterMap();
      data =  Integer.parseInt(params.get("clutch_id")); 
      return data;
   } 
   
    public Clutch findPillar(int id){
        return manager.find(Clutch.class, id);
    }
      
   
    public HouseFacade getMapClutchFacade() {
        return mapClutchFacade;
    }

    public void setMapClutchFacade(HouseFacade mapClutchFacade) {
        this.mapClutchFacade = mapClutchFacade;
    }

    public MapModel getModelClutch() {
        return modelClutch;
    }

    public void setModelClutch(MapModel modelClutch) {
        this.modelClutch = modelClutch;
    }

    public List<Clutch> getList() {
        return list;
    }

    public void setList(List<Clutch> list) {
        this.list = list;
    }
    
    public Integer getData() {
       return data;
    }

    public void setData(Integer data) {
       this.data = data;
    } 

    public String getCenterClutch() {
        return centerClutch;
    }

    public void setCenterClutch(String centerClutch) {
        this.centerClutch = centerClutch;
    }

    
}
