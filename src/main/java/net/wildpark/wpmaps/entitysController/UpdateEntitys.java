/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitysController;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import net.wildpark.wpmaps.entitys.Clutch;
import net.wildpark.wpmaps.entitys.DrawWell;
import net.wildpark.wpmaps.entitys.House;
import net.wildpark.wpmaps.entitys.Pillar;
import net.wildpark.wpmaps.enums.DrawWellOwner;
import net.wildpark.wpmaps.enums.DrawWellType;
import net.wildpark.wpmaps.enums.HouseOwner;
import net.wildpark.wpmaps.enums.HouseType;
import net.wildpark.wpmaps.enums.ObjectType;
import net.wildpark.wpmaps.enums.PillarCapacity;
import net.wildpark.wpmaps.enums.PillarMaterial;
import net.wildpark.wpmaps.enums.PillarOwner;
import net.wildpark.wpmaps.enums.PillarType;
import net.wildpark.wpmaps.facades.DrawWellFacade;
import net.wildpark.wpmaps.facades.HouseFacade;
import net.wildpark.wpmaps.facades.PillarFacade;
import net.wildpark.wpmaps.facades.PointFacade;
import net.wildpark.wpmaps.pageControllers.GMapsController;

/**
 *
 * @author zekart
 */
@Named(value = "updateEntController")


public class UpdateEntitys implements Serializable{
    @EJB
    private DrawWellFacade drawWellFacade;
    @EJB
    private HouseFacade houseFacade;
    @EJB
    private PillarFacade pillarFacade;
    
    
    private PointFacade mapFacade;
    private DrawWell select_draw_well ;
    private Pillar select_pillar ;
    private House select_house ;
    private Clutch select_clutch ;
    
    
    private String p_address = "";
    private String d_address = "";
    private String h_address = "";
    
    private String p_station = "";
    private String h_station = "";
    private String d_station = "";
    
    private int p_nstation;
    private int h_nstation;
    private int d_nstation;
    
    private String pillar_owner = "";
    private String house_owner = "";
    private String draw_owner = "";
    
    private PillarMaterial matheriallPillar;
    private PillarType typePillar;
    private PillarCapacity capacityPillar;
    private ObjectType obj_type;
    private HouseType typeOfHouse;
    private HouseOwner ownerofHouse;
    private DrawWellOwner ownerDrawWell;
    private DrawWellType type_drawWell;
    
    
    
    
    public void updateDraw(int id){

        System.out.println("ID" + id);
        select_draw_well = (DrawWell)drawWellFacade.find(id);
        //select_draw_well = (DrawWell) drawWellFacade.find(id);
        select_draw_well.setTransportStation(d_station);
        select_draw_well.setNumberStation(d_nstation);
        select_draw_well.setAddress(d_address); 
        select_draw_well.setOwner(draw_owner);
        select_draw_well.setType_draw_well(type_drawWell);
        
        
        System.out.println("Draw Well " + select_draw_well);
        //drawWellFacade.merge(select_draw_well);
    }
    public void updatePillar(int id){
        select_pillar = (Pillar)pillarFacade.find(id);
        //select_draw_well = (DrawWell) drawWellFacade.find(id);
        select_pillar.setAddress(p_address); 
        select_pillar.setOwner(pillar_owner);
        select_pillar.setMaterial(matheriallPillar);
        select_pillar.setNumberStation(p_nstation);
        select_pillar.setTransportStation(p_station);
        select_pillar.setType(typePillar);
        
        pillarFacade.merge(select_pillar);
    }
    public void updateHouse(int id){
        select_house = (House)houseFacade.find(id);
        //select_draw_well = (DrawWell) drawWellFacade.find(id);
        select_house.setAddress(h_address); 
        select_house.setOwner(house_owner);
        select_house.setType_house(typeOfHouse);
        select_draw_well.setTransportStation(d_station);
        select_draw_well.setNumberStation(d_nstation);
        houseFacade.merge(select_house);
    } 
    
    
    
    
    
    
    
    
    
    
    public HouseType[] getHouseType() {
        return HouseType.values();
    }
    public HouseOwner[] getHouseOwner() {
        return HouseOwner.values();
    }    
    public ObjectType[] getObjectType() {
        return ObjectType.values();
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
    public PillarCapacity[] getPillarCapacity() {
        return PillarCapacity.values();
    }   
    public DrawWellOwner[] getDrawWellOwner() {
        return DrawWellOwner.values();
    }
    public DrawWellType[] getDrawWellType() {
        return DrawWellType.values();
    }    
    public DrawWell getSelect_draw_well() {
        return select_draw_well;
    }

    public void setSelect_draw_well(DrawWell select_draw_well) {
        this.select_draw_well = select_draw_well;
    }

    public Pillar getSelect_pillar() {
        return select_pillar;
    }

    public void setSelect_pillar(Pillar select_pillar) {
        this.select_pillar = select_pillar;
    }

    public House getSelect_house() {
        return select_house;
    }

    public void setSelect_house(House select_house) {
        this.select_house = select_house;
    }

    public Clutch getSelect_clutch() {
        return select_clutch;
    }

    public void setSelect_clutch(Clutch select_clutch) {
        this.select_clutch = select_clutch;
    }

    public DrawWellFacade getDrawWellFacade() {
        return drawWellFacade;
    }

    public void setDrawWellFacade(DrawWellFacade drawWellFacade) {
        this.drawWellFacade = drawWellFacade;
    }

    public HouseFacade getHouseFacade() {
        return houseFacade;
    }

    public void setHouseFacade(HouseFacade houseFacade) {
        this.houseFacade = houseFacade;
    }

    public PillarFacade getPillarFacade() {
        return pillarFacade;
    }

    public void setPillarFacade(PillarFacade pillarFacade) {
        this.pillarFacade = pillarFacade;
    }

    public PointFacade getMapFacade() {
        return mapFacade;
    }

    public void setMapFacade(PointFacade mapFacade) {
        this.mapFacade = mapFacade;
    }

    public String getP_address() {
        return p_address;
    }

    public void setP_address(String p_address) {
        this.p_address = p_address;
    }

    public String getD_address() {
        return d_address;
    }

    public void setD_address(String d_address) {
        this.d_address = d_address;
    }

    public String getH_address() {
        return h_address;
    }

    public void setH_address(String h_address) {
        this.h_address = h_address;
    }

    public String getP_station() {
        return p_station;
    }

    public void setP_station(String p_station) {
        this.p_station = p_station;
    }

    public String getH_station() {
        return h_station;
    }

    public void setH_station(String h_station) {
        this.h_station = h_station;
    }

    public String getD_station() {
        return d_station;
    }

    public void setD_station(String d_station) {
        this.d_station = d_station;
    }

    public int getP_nstation() {
        return p_nstation;
    }

    public void setP_nstation(int p_nstation) {
        this.p_nstation = p_nstation;
    }

    public int getH_nstation() {
        return h_nstation;
    }

    public void setH_nstation(int h_nstation) {
        this.h_nstation = h_nstation;
    }

    public int getD_nstation() {
        return d_nstation;
    }

    public void setD_nstation(int d_nstation) {
        this.d_nstation = d_nstation;
    }

    public PillarMaterial getMatheriallPillar() {
        return matheriallPillar;
    }

    public void setMatheriallPillar(PillarMaterial matheriallPillar) {
        this.matheriallPillar = matheriallPillar;
    }

    public PillarType getTypePillar() {
        return typePillar;
    }

    public void setTypePillar(PillarType typePillar) {
        this.typePillar = typePillar;
    }

    public PillarCapacity getCapacityPillar() {
        return capacityPillar;
    }

    public void setCapacityPillar(PillarCapacity capacityPillar) {
        this.capacityPillar = capacityPillar;
    }

    public ObjectType getObj_type() {
        return obj_type;
    }

    public void setObj_type(ObjectType obj_type) {
        this.obj_type = obj_type;
    }

    public HouseType getTypeOfHouse() {
        return typeOfHouse;
    }

    public void setTypeOfHouse(HouseType typeOfHouse) {
        this.typeOfHouse = typeOfHouse;
    }

    public HouseOwner getOwnerofHouse() {
        return ownerofHouse;
    }

    public void setOwnerofHouse(HouseOwner ownerofHouse) {
        this.ownerofHouse = ownerofHouse;
    }

    public DrawWellOwner getOwnerDrawWell() {
        return ownerDrawWell;
    }

    public void setOwnerDrawWell(DrawWellOwner ownerDrawWell) {
        this.ownerDrawWell = ownerDrawWell;
    }

    public DrawWellType getType_drawWell() {
        return type_drawWell;
    }

    public void setType_drawWell(DrawWellType type_drawWell) {
        this.type_drawWell = type_drawWell;
    }

    public String getPillar_owner() {
        return pillar_owner;
    }

    public void setPillar_owner(String pillar_owner) {
        this.pillar_owner = pillar_owner;
    }

    public String getHouse_owner() {
        return house_owner;
    }

    public void setHouse_owner(String house_owner) {
        this.house_owner = house_owner;
    }

    public String getDraw_owner() {
        return draw_owner;
    }

    public void setDraw_owner(String draw_owner) {
        this.draw_owner = draw_owner;
    }


    
    
    
    
    
}
