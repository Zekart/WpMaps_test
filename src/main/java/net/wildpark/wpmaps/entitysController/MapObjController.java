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
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import net.wildpark.wpmaps.entitys.Clutch;
import net.wildpark.wpmaps.entitys.DrawWell;
import net.wildpark.wpmaps.entitys.House;
import net.wildpark.wpmaps.entitys.MapPoint;
import net.wildpark.wpmaps.entitys.Pillar;
import net.wildpark.wpmaps.facades.DrawWellFacade;
import net.wildpark.wpmaps.facades.HouseFacade;
import net.wildpark.wpmaps.facades.PillarFacade;
import net.wildpark.wpmaps.facades.PointFacade;
import net.wildpark.wpmaps.pageControllers.PointWizard;

/**
 *
 * @author zekart
 */
@Named(value = "mapObjController")
@SessionScoped
public class MapObjController implements Serializable{

    @EJB
    private PointFacade pointFacade;

    @EJB
    private PillarFacade pillarFacade;

    @EJB
    private HouseFacade houseFacade;

    @EJB
    private DrawWellFacade drawWellFacade;
    
    

    private double lat;
    private double lng;
    
    private List<Clutch> clutch = new ArrayList<>();
    private List<House> house_list = new ArrayList<>();
    private List<DrawWell> draw_list = new ArrayList<>();
    private List<Pillar> pillar_list = new ArrayList<>();
    private List<Pillar> temp_pillar_list = new ArrayList<>();
    private Pillar select_pillar;
    
    
    Pillar pillar = new Pillar();
    House house = new House();
    DrawWell drawWell = new DrawWell();
    MapPoint point = new MapPoint();
    
    PointWizard pz = new PointWizard();
    


    public MapObjController() {
    }
        
    
    public void savePillar(){
        pillar.setLat(this.lat);
        pillar.setLng(this.lng);
        if(pz.isSkip()!= true){
            pillar.setClutch(clutch);
        }
        this.pillarFacade.create(pillar);
        clutch.clear();
    }
    public void saveHouse(){
        house.setLat(this.lat);
        house.setLng(this.lng);
        if(pz.isSkip()!= true){
            house.setClutch(clutch);
        }
        houseFacade.create(house);
        clutch.clear();
    }
    public void saveDrawWell(){
        drawWell.setLat(this.lat);
        drawWell.setLng(this.lng);
        if(pz.isSkip()!= true){
            drawWell.setClutch(clutch);
        }
        drawWellFacade.create(drawWell);
        clutch.clear();
    }    
    
    public List<Pillar> findPillar(int id){
        pillar_list.clear();
        pillar = pillarFacade.find(id);
        pillar_list.add(pillar);
        System.out.println("1");
        return pillar_list;
    }
    

    public List<Pillar> getTemp_pillar_list() {
        return temp_pillar_list;
    }

    public void setTemp_pillar_list(List<Pillar> temp_pillar_list) {
        this.temp_pillar_list = temp_pillar_list;
    }

    public List<House> getHouse_list() {
        return house_list;
    }

    public void setHouse_list(List<House> house_list) {
        this.house_list = house_list;
    }

    public List<DrawWell> getDraw_list() {
        return draw_list;
    }

    public void setDraw_list(List<DrawWell> draw_list) {
        this.draw_list = draw_list;
    }

    public List<Pillar> getPillar_list() {
        return pillar_list;
    }

    public void setPillar_list(List<Pillar> pillar_list) {
        this.pillar_list = pillar_list;
    }
    
    
    
    
    
    public void delLine(ActionEvent actionEvent) {
        this.clutch.clear();
    }
    public void newLine(ActionEvent actionEvent) {
        this.clutch.add(new Clutch());     
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
    
    
    
    public Pillar getPillar() {
        return pillar;
    }

    public void setPillar(Pillar pillar) {
        this.pillar = pillar;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public DrawWell getDrawWell() {
        return drawWell;
    }

    public void setDrawWell(DrawWell drawWell) {
        this.drawWell = drawWell;
    }

    public List<Clutch> getClutch() {
        return clutch;
    }

    public void setClutch(List<Clutch> clutch) {
        this.clutch = clutch;
    }

    public Pillar getSelect_pillar() {
        return select_pillar;
    }

    public void setSelect_pillar(Pillar select_pillar) {
        this.select_pillar = select_pillar;
    }
    
    
    
    
}