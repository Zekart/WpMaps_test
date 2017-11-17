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
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import net.wildpark.wpmaps.entitys.Cabel;
import net.wildpark.wpmaps.entitys.Clutch;
import net.wildpark.wpmaps.entitys.DrawWell;
import net.wildpark.wpmaps.entitys.House;
import net.wildpark.wpmaps.entitys.MapPoint;
import net.wildpark.wpmaps.entitys.Pillar;
import net.wildpark.wpmaps.facades.CableFacade;
import net.wildpark.wpmaps.facades.DrawWellFacade;
import net.wildpark.wpmaps.facades.HouseFacade;
import net.wildpark.wpmaps.facades.PillarFacade;
import net.wildpark.wpmaps.facades.PointFacade;
import net.wildpark.wpmaps.pageControllers.PointWizard;
import org.primefaces.context.RequestContext;

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
    
    @EJB
    private CableFacade cableFacade;    

    private double lat;
    private double lng;
    private String number_house;
    private String user_connection;
    private String number_flat;
    private String address_info;
    private String number_entrance;
    
    private String a_street;
    private String a_number;
    
    private List<Clutch> clutch = new ArrayList<>();
    private List<Cabel> cabel = new ArrayList<>();
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
        clearAll();
    }
    public void saveHouse(){
        house.setLat(this.lat);
        house.setLng(this.lng);
        house.setAddress_info(address_info);
        house.setNumber_entrance(number_entrance);
        house.setAddress(house.getAddress().concat(" " + number_house + " " + number_flat));
        
        
        if(pz.isSkip()!= true){
            house.setClutch(clutch);
        }
        houseFacade.create(house);     
        clearAll();
    }
    public void saveDrawWell(){
        drawWell.setLat(this.lat);
        drawWell.setLng(this.lng);
        if(pz.isSkip()!= true){
            drawWell.setClutch(clutch);
            
        }
        drawWellFacade.create(drawWell);
        clearAll();
        //closeAddPanell();
    }  
    
    public void clearAll(){
        pillar = new Pillar();
        house = new House();
        drawWell = new DrawWell();
        
        clutch.clear();
    }
    
    public List<Pillar> findPillar(int id){
        pillar_list.clear();
        pillar = pillarFacade.find(id);
        pillar_list.add(pillar);
        return pillar_list;
    }
    
    public void closeAddPanell(){
        RequestContext.getCurrentInstance().execute("alert('peek-a-boo');");      
    }
    
    
    public void autoAddress(AjaxBehaviorEvent event){
        System.out.println("Address: ");
    }

    public String getA_street() {
        return a_street;
    }

    public void setA_street(String a_street) {
        this.a_street = a_street;
    }

    public String getA_number() {
        return a_number;
    }

    public void setA_number(String a_number) {
        this.a_number = a_number;
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
    
    public void delLineCable(ActionEvent actionEvent) {
        this.cabel.clear();
    }
    public void newLineCable(ActionEvent actionEvent) {
        this.cabel.add(new Cabel());     
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

    public String getNumber_house() {
        return number_house;
    }

    public void setNumber_house(String number_house) {
        this.number_house = number_house;
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

    public String getUser_connection() {
        return user_connection;
    }

    public void setUser_connection(String user_connection) {
        this.user_connection = user_connection;
    }

    public String getNumber_flat() {
        return number_flat;
    }

    public void setNumber_flat(String number_flat) {
        this.number_flat = number_flat;
    }

    public String getAddress_info() {
        return address_info;
    }

    public void setAddress_info(String address_info) {
        this.address_info = address_info;
    }

    public List<Cabel> getCabel() {
        return cabel;
    }

    public void setCabel(List<Cabel> cabel) {
        this.cabel = cabel;
    }
    
    
    
    
}
