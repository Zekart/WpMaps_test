/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitysController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import net.wildpark.wpmaps.entitys.Cabel;
import net.wildpark.wpmaps.entitys.Clutch;
import net.wildpark.wpmaps.entitys.DrawWell;
import net.wildpark.wpmaps.entitys.Fiber;
import net.wildpark.wpmaps.entitys.House;
import net.wildpark.wpmaps.entitys.MapPoint;
import net.wildpark.wpmaps.entitys.Pillar;
import net.wildpark.wpmaps.enums.DrawWellOwner;
import net.wildpark.wpmaps.enums.DrawWellType;
import net.wildpark.wpmaps.enums.HouseOwner;
import net.wildpark.wpmaps.enums.HouseType;
import net.wildpark.wpmaps.enums.ObjectType;
import net.wildpark.wpmaps.enums.PillarCapacity;
import net.wildpark.wpmaps.enums.PillarMaterial;
import net.wildpark.wpmaps.enums.PillarType;
import net.wildpark.wpmaps.facades.CableFacade;
import net.wildpark.wpmaps.facades.ClutchFacade;
import net.wildpark.wpmaps.facades.ConnectPointFacade;
import net.wildpark.wpmaps.facades.DrawWellFacade;
import net.wildpark.wpmaps.facades.HouseFacade;
import net.wildpark.wpmaps.facades.PillarFacade;
import net.wildpark.wpmaps.facades.PointFacade;
import net.wildpark.wpmaps.pageControllers.PointWizard;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Zekart
 */
@Named(value = "pointAddController")
@SessionScoped

public class PointAddController implements Serializable{
    
    @EJB
    private DrawWellFacade drawWellFacade;
    @EJB
    private HouseFacade houseFacade;
    @EJB
    private PillarFacade pillarFacade;
    @EJB
    private PointFacade mapFacade;
    @EJB
    private CableFacade cabFacade;   
    @EJB
    private ClutchFacade clutchFacade;
    @EJB
    private ConnectPointFacade conFacade;
    
    
    private double lat;     
    private double lng;
    
    private String transportStation = "";
    private int numberStation = 0;
    private String owner = "";
    private String address = "";
    private PillarMaterial matheriallPillar;
    private PillarType typePillar;
    private PillarCapacity capacityPillar;
    private ObjectType obj_type;

    
    List<Pillar> pillarList = new ArrayList<>();
    List<Clutch> clutc_rend = new ArrayList<>();
    List<Cabel> cabels = new ArrayList<>();
    List<LatLng> coord = new ArrayList<>(); 
    List<Fiber> fiber = new ArrayList<>();
    List<DrawWell> drawList = new ArrayList<>();
    
    PointWizard pz = new PointWizard();
    
    Pillar pillar = new Pillar();
    House house = new House();
    DrawWell draw_well = new DrawWell();
    MapPoint point = new MapPoint();
    Cabel cabel = new Cabel();
    
    private Pillar select_pillar = null;
    
    public void addMarkerP() {       
        pillar.setLat(lat);
        pillar.setLng(lng);
        pillar.setMaterial(matheriallPillar);
        pillar.setNumberStation(numberStation);
        pillar.setTransportStation(transportStation);
        pillar.setType(typePillar);
        pillar.setOwner(owner);
        pillar.setAddress(address);
  
        if(pz.isSkip()!= true){
            pillar.setClutch(clutc_rend);
        }
        
        pillarFacade.create(pillar);
//        //list.clear();
        clutc_rend.clear();
        //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("@all");
//                      RequestContext requestContext = RequestContext.getCurrentInstance();  
//                requestContext.execute("PF('wizp').hide()");
    }
    
    public void updatePillar(int id){
        select_pillar = (Pillar)pillarFacade.find(id);
        System.out.println("Draw");
        //select_draw_well = (DrawWell) drawWellFacade.find(id);
        select_pillar.setAddress(address); 
        select_pillar.setOwner(owner);
        select_pillar.setMaterial(matheriallPillar);
        select_pillar.setNumberStation(numberStation);
        select_pillar.setTransportStation(transportStation);
        select_pillar.setType(typePillar);
        pillarFacade.merge(select_pillar);
    }
    
}
