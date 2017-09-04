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
    
    
    private double lat;     
    private double lng;
    private String transportStation = null;
    private int numberStation = 0;
    private String owner;
    private String address = "";
    private PillarMaterial matheriallPillar;
    private PillarType typePillar;
    private PillarCapacity capacityPillar;
    private ObjectType obj_type;
    private HouseType typeOfHouse;
    private HouseOwner ownerofHouse;
    private DrawWellOwner ownerDrawWell;
    private DrawWellType type_drawWell;
    
    private ObjectType obj_typs;
    
    private List<Pillar> listPillar = new ArrayList<>();
    private List<House> listHouse = new ArrayList<>();
    private List<DrawWell> listDrawWell = new ArrayList<>();
           
    
    private int id;

    
    Pillar pillar = new Pillar();
    House house = new House();
    DrawWell draw_well = new DrawWell();

    

    public void addMarkerP() {       
        pillar.setLat(lat);
        pillar.setLng(lng);
        pillar.setMaterial(matheriallPillar);
        pillar.setNumberStation(numberStation);
        pillar.setTransportStation(transportStation);
        pillar.setType(typePillar);
        pillar.setOwner(owner);
        pillar.setAddress(address);
        
        pillarFacade.create(pillar);
        System.out.println(address);
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

    public List<Pillar> getListPillar() {
        return listPillar;
    }

    public void setListPillar(List<Pillar> listPillar) {
        this.listPillar = listPillar;
    }

    public List<House> getListHouse() {
        return listHouse;
    }

    public void setListHouse(List<House> listHouse) {
        this.listHouse = listHouse;
    }

    public List<DrawWell> getListDrawWell() {
        return listDrawWell;
    }

    public void setListDrawWell(List<DrawWell> listDrawWell) {
        this.listDrawWell = listDrawWell;
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

    public String getTransportStation() {
        return transportStation;
    }

    public void setTransportStation(String transportStation) {
        this.transportStation = transportStation;
    }

    public int getNumberStation() {
        return numberStation;
    }

    public void setNumberStation(int numberStation) {
        this.numberStation = numberStation;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public ObjectType getObj_typs() {
        return obj_typs;
    }

    public void setObj_typs(ObjectType obj_typs) {
        this.obj_typs = obj_typs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public DrawWell getDraw_well() {
        return draw_well;
    }

    public void setDraw_well(DrawWell draw_well) {
        this.draw_well = draw_well;
    }


    
    
    
    
    
    
}
