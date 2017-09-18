/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.pageControllers;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.wildpark.wpmaps.entitys.Clutch;
import net.wildpark.wpmaps.entitys.DrawWell;
import net.wildpark.wpmaps.entitys.House;
import net.wildpark.wpmaps.entitys.MapPoint;
import net.wildpark.wpmaps.entitys.Pillar;
import net.wildpark.wpmaps.enums.HouseOwner;
import net.wildpark.wpmaps.enums.HouseType;
import net.wildpark.wpmaps.enums.ObjectType;
import net.wildpark.wpmaps.enums.PillarMaterial;
import net.wildpark.wpmaps.enums.PillarOwner;
import net.wildpark.wpmaps.enums.PillarType;
import net.wildpark.wpmaps.facades.CableFacade;
import net.wildpark.wpmaps.facades.ClutchFacade;
import net.wildpark.wpmaps.facades.DrawWellFacade;
import net.wildpark.wpmaps.facades.HouseFacade;
import net.wildpark.wpmaps.facades.PillarFacade;
import net.wildpark.wpmaps.facades.PointFacade;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author zekart
 */
@Named(value = "addMarker")
@SessionScoped
public class AddMarker implements Serializable {

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
    
    private Pillar p = new Pillar();
    private House h = new House();
    private DrawWell d = new DrawWell();
    private MapPoint mp = new MapPoint();
    private Clutch cl = new Clutch();
    
    List<Clutch> clutc_rend = new ArrayList<>();
    
    private double lat;     
    private double lng;
    private ObjectType obj_type;
    
    PointWizard pz = new PointWizard();
    
    
    
    public AddMarker() {
    }
    
    public String pillar_add(){
        this.p.setLat(lat);
        this.p.setLng(lng);
        if(pz.isSkip()!= true){
            this.p.setClutch(this.clutc_rend);

        }
        this.pillarFacade.create(this.p);
        return "index";
    }
    
    public String house_add(){
        this.h.setLat(lat);
        this.h.setLng(lng);
        if(pz.isSkip()!= true){
            this.h.setClutch(this.clutc_rend);

        }
        this.houseFacade.create(this.h);
        return "index";
    }  
    
    public String draw_add(){
        this.d.setLat(lat);
        this.d.setLng(lng);
        if(pz.isSkip()!= true){
            this.d.setClutch(this.clutc_rend);
        }
        this.drawWellFacade.create(this.d);
        return "index";
    }      

    public List<Clutch> getClutc_rend() {
        return clutc_rend;
    }

    public void setClutc_rend(List<Clutch> clutc_rend) {
        this.clutc_rend = clutc_rend;
    }
    
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    public void delLine(ActionEvent actionEvent) {
        this.clutc_rend.clear();
    }
    public void newLine(ActionEvent actionEvent) {
        this.clutc_rend.add(new Clutch());
        
    }
    
    
    
    public ObjectType[] getObjectType() {
        return ObjectType.values();
    }

    public ObjectType getObj_type() {
        return obj_type;
    }

    public void setObj_type(ObjectType obj_type) {
        this.obj_type = obj_type;
    }
      
    public HouseType[] getHouseType() {
        return HouseType.values();
    }
    public HouseOwner[] getHouseOwner() {
        return HouseOwner.values();
    }       
    public List<Pillar> findAll(){
        return this.pillarFacade.findAll();
    }
    
    public PillarOwner[] getPillarOwner() {
        return PillarOwner.values();
    }
    
    public PillarMaterial[] getPillarMaterial() {
        return PillarMaterial.values();
    }    
    public PillarType[] getPillarType() {
        return PillarType.values();
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

    public Pillar getP() {
        return p;
    }

    public void setP(Pillar p) {
        this.p = p;
    }

    public House getH() {
        return h;
    }

    public void setH(House h) {
        this.h = h;
    }

    public DrawWell getD() {
        return d;
    }

    public void setD(DrawWell d) {
        this.d = d;
    }

    public MapPoint getMp() {
        return mp;
    }

    public void setMp(MapPoint mp) {
        this.mp = mp;
    }

    public Clutch getCl() {
        return cl;
    }

    public void setCl(Clutch cl) {
        this.cl = cl;
    }
    


    
    
}
