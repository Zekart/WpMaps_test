/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.basicPageControllers;


import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import net.wildpark.wpmaps.entitys.MapPoint;
import net.wildpark.wpmaps.facades.PointFacade;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Zekart
 */
@Named(value = "basicPageController")
@SessionScoped

public class basicPageController implements Serializable{
    @EJB
    private PointFacade mapFacade;
        
    private MapModel basicModel;
    private Marker marker;    
    private List<MapPoint> basicList;
    
    MapPoint point = new MapPoint();
        
//    @PostConstruct
//    public void init() {
//        
//        basicModel = new DefaultMapModel();
//        basicList = mapFacade.findAll();   
//                       
//        for (MapPoint e:basicList) {
//            basicModel.addOverlay(new Marker(new LatLng(e.getLat(), e.getLng()),String.valueOf(e.getId()),e,"../resources/marker/"+e.getDecriminatorValue()+"_marker.png"));                
//        }   
//    }
        
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();   
        point = (MapPoint) marker.getData();         
    }

    public PointFacade getMapFacade() {
        return mapFacade;
    }

    public void setMapFacade(PointFacade mapFacade) {
        this.mapFacade = mapFacade;
    }

    public MapModel getBasicModel() {
        return basicModel;
    }

    public void setBasicModel(MapModel basicModel) {
        this.basicModel = basicModel;
    }

    public List<MapPoint> getBasicList() {
        return basicList;
    }

    public void setBasicList(List<MapPoint> basicList) {
        this.basicList = basicList;
    }



    
    
    
}
