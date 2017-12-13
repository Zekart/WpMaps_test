/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitysController;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import net.wildpark.wpmaps.entitys.Clutch;
import net.wildpark.wpmaps.entitys.DrawWell;
import net.wildpark.wpmaps.entitys.MapPoint;
import net.wildpark.wpmaps.facades.DrawWellFacade;
import net.wildpark.wpmaps.facades.PointFacade;
import net.wildpark.wpmaps.pageControllers.PointWizard;
import org.primefaces.context.RequestContext;

/**
 *
 * @author zekart
 */
@Named(value = "drawWellController")
@SessionScoped
public class DrawWellController implements Serializable {

    @EJB
    private PointFacade pointFacade;
    
    @EJB
    private DrawWellFacade drawWellFacade;
    
    
    
    
    DrawWell drawWell = new DrawWell();
    MapPoint point = new MapPoint();
    
    
    MapObjController mapObjController = new MapObjController();
    PointWizard pz = new PointWizard();
    
    ClutchController clutch_controll;
    List<Clutch> clutch = new ArrayList<>();
    
    public DrawWellController() {

    }        
    
    public void setSaveDrawWell(Double lat, double lng, String address){   
        
        drawWell.setLat(lat);
        drawWell.setLng(lng);
        drawWell.setAddress(address);

        if(pz.isSkip()!= true){

//            cabel.add(cabel_o);
//            clutch_o.setCable(cabel);
//            cableFacade.create(cabel_o);

            //drawWell.setClutch(clutch.getAdded_clutch()); 
        }
        //drawWellFacade.create(drawWell);
//            drawWell = new DrawWell();            
        //closeAddPanell();
        System.out.println(clutch_controll.returnNewClutch());
    }
          
    
    public void clearAll(){
        drawWell = new DrawWell();   
        ClutchController clutch = new ClutchController();
        
        clutch.getAdded_clutch().clear();
        RequestContext.getCurrentInstance().reset("j_idt189:ff:wizard_form:drawWell_panel");
    }
    
    
    public String add(){
        this.drawWellFacade.create(this.drawWell);
        return "index";
    }
    
    public void delete(){
        this.drawWellFacade.remove(this.drawWell);
    }
    
    public List<DrawWell> findAll(){
        return this.drawWellFacade.findAll();
    }

    public DrawWell getDrawWell() {
        return drawWell;
    }

    public void setDrawWell(DrawWell drawWell) {
        this.drawWell = drawWell;
    }

    public PointFacade getPointFacade() {
        return pointFacade;
    }

    public void setPointFacade(PointFacade pointFacade) {
        this.pointFacade = pointFacade;
    }

    public DrawWellFacade getDrawWellFacade() {
        return drawWellFacade;
    }

    public void setDrawWellFacade(DrawWellFacade drawWellFacade) {
        this.drawWellFacade = drawWellFacade;
    }

    public MapObjController getMapObjController() {
        return mapObjController;
    }

    public void setMapObjController(MapObjController mapObjController) {
        this.mapObjController = mapObjController;
    }

    public List<Clutch> getClutch() {
        return clutch;
    }

    public void setClutch(List<Clutch> clutch) {
        this.clutch = clutch;
    }

    

    
    
}
