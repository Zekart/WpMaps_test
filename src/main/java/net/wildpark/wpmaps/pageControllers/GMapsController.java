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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import net.wildpark.wpmaps.entitys.Clutch;
import net.wildpark.wpmaps.entitys.House;
import net.wildpark.wpmaps.entitys.MapPoint;
import net.wildpark.wpmaps.entitys.Person;
import net.wildpark.wpmaps.entitys.Pillar;
import net.wildpark.wpmaps.entitys.PointStaff;
import net.wildpark.wpmaps.enums.FiberType;
import net.wildpark.wpmaps.enums.HouseOwner;
import net.wildpark.wpmaps.enums.HouseType;
import net.wildpark.wpmaps.enums.ObjectType;
import net.wildpark.wpmaps.enums.PillarCapacity;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import net.wildpark.wpmaps.enums.PillarOwner;
import net.wildpark.wpmaps.enums.PillarType;
import net.wildpark.wpmaps.enums.PillarMaterial;
import net.wildpark.wpmaps.facades.HouseFacade;
import net.wildpark.wpmaps.facades.PillarFacade;
import net.wildpark.wpmaps.facades.DrawWellFacade;
import net.wildpark.wpmaps.facades.PointFacade;
import org.primefaces.context.RequestContext;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.Polyline;


/**
 *
 * @author Panker-RDP
 */
@Named(value = "gMapsController")
@SessionScoped
public class GMapsController implements Serializable {
    
    @EJB
    private DrawWellFacade drawWellFacade;
    @EJB
    private HouseFacade houseFacade;
    @EJB
    private PillarFacade pillarFacade;
    @EJB
    private PointFacade mapFacade;    
    
    private MapModel model;
    private Marker marker;
    private String transportStation;
    private int numberStation;
    private PillarOwner owner;
    private PillarMaterial matheriallPillar;
    private PillarType typePillar;
    private PillarCapacity capacityPillar;
    private ObjectType obj_type;
    private HouseType typeOfHouse;
    private HouseOwner ownerofHouse;
    
    private int id;
    private Boolean capacityCabel;
    
    private int zoomMap = 13;
    private boolean showMarker = false; 
    

    private double lat;     
    private double lng;
    
    
    private List<MapPoint> list; 
     
    //Staff mappoint =  new Staff();
    
    Pillar car = new Pillar();
    House bike = new House();
    
    Clutch selectedMappoint = new Clutch();

    
    List<LatLng> coord = new ArrayList<>();
    List<Marker> markers;  
//
    @PostConstruct
    public void init() {
        model = new DefaultMapModel();
        list = mapFacade.findAll();   
        
        for(MapPoint e: list){
            System.out.println(e);
        }

//        bike.setBitrate(12);
//        bike.setLength("128");
//        bike.setName("Nero");
//        bike.setYear(1239);
//        
//        houseFacade.create(bike);
//        

//car.setLength("34");
//car.setName("honda");
//car.setType(PillarType.CIRCULAR);
//car.setYear(1987);
//
//houseFacade.create(bike);

        
        for (MapPoint e:list) {
            model.addOverlay(new Marker(new LatLng(e.getLat(), e.getLng()),String.valueOf(e.getId()),e,"../resources/marker/EL_TRANCE/EMPTY.png"));                
        }   
    }

    public void addMarker() {

        car.setLat(lat);
        car.setLng(lng);
car.setLength("34");
car.setName("honda");
car.setType(PillarType.CIRCULAR);
car.setYear(1987);
////        mappoint.setNumbertranspotrstation(numberStation);
////        mappoint.setTransportstation(transportStation);
////        mappoint.setOwner(owner);
////        mappoint.setType(typePillar);
////        mappoint.setMatheriallPillar(matheriallPillar);
////        mappoint.setCapacityPillar(capacityPillar);
//        
//
        pillarFacade.create(car);
        
        System.out.println("Yes");
//        
        id = car.getId();
        marker = new Marker(new LatLng(lat, lng), String.valueOf(id),bike,"../resources/marker/EL_TRANCE/EMPTY.png" );
        model.addOverlay(marker);
//        //list.clear();
        init();
        //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("@all");
    }
    
//    public void deleteMarker(){        
//        //selectedPillar = (Pillar) marker.getData();
////        System.out.println("Select id  " + selectedMappoint.getId());
//        mappoint = mapFacade.find(selectedMappoint.getId());
//        if(mappoint != null){            
//            mapFacade.remove(mappoint);
//            //list.clear();
//            init();
//            //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("@all");
//        }
//    }
//       
//
//    public void onMarkerSelect(OverlaySelectEvent event) {
//        marker = (Marker) event.getOverlay();   
//        selectedMappoint = (Pillar) marker.getData(); 
//        System.out.println("go");
//    }
   
    
//    public void connectPillar(){
//        Polyline polyline = new Polyline();
//        
//        polyline.setStrokeWeight(2);
//        polyline.setStrokeColor("#FF9900");
//        polyline.setStrokeOpacity(1);
//        
//        coord.add(new LatLng(selectedMappoint.getLat(), selectedMappoint.getLng()));
//
//        
////        LatLng  coord1 = new LatLng(selectedMappoint.getLat(), selectedMappoint.getLng());
////        LatLng coord2 = new LatLng(selectedMappoint.getLat(), selectedMappoint.getLng());
//        
////        polyline.getPaths().add(coord1);
////        polyline.getPaths().add(coord2);
//
//        if (coord.size()==2) {
//            for (LatLng en : coord ){
//                System.out.println(en);
//                polyline.getPaths().add(en);
//            }
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Режим соединения", "Успешно"));
//            model.addOverlay(polyline);
//            RequestContext.getCurrentInstance().update("gmap");
//            coord.clear();                   
//        }else{
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Режим добавления", "Выберите 2 маркер"));            
//        }          
//    }
    

//    public void changePillar(){        
//        id = (Integer) marker.getData();
//        mappoint = mapFacade.find(id);
//        
//        System.out.println("Id: " + id);             
//    }
    
    public void onStateChange(StateChangeEvent event){
        
        zoomMap = event.getZoomLevel();  
        
        System.out.println("zoom : " + zoomMap);
        if(zoomMap >= 14){
            showMarker = true;             
        }else{
            showMarker = false;
          
        }
        for (Marker m : markers) {
            m.setVisible(showMarker);

        } 

        System.out.println("marker" + markers);

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
    
    public String changeInfoPillar(){
        return"pillarChange.xhtml?faces-redirect=true";
    }

    public MapModel getModel() {
        return model;
    }

    public void setModel(MapModel model) {
        this.model = model;
    }
 
    public Marker getMarker() {
        return marker;
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

    public PillarOwner getOwner() {
        return owner;
    }

    public void setOwner(PillarOwner owner) {
        this.owner = owner;
    }

    public PillarCapacity getCapacityPillar() {
        return capacityPillar;
    }

    public void setCapacityPillar(PillarCapacity capacityPillar) {
        this.capacityPillar = capacityPillar;
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


    public Boolean getCapacityCabel() {
        return capacityCabel;
    }

    public void setCapacityCabel(Boolean capacityCabel) {
        this.capacityCabel = capacityCabel;
    }
//
    public List<MapPoint> getList() {
        return list;
    }

    public void setList(List<MapPoint> list) {
        this.list = list;
    }
//
    public Clutch getSelectedPillar() {
        return selectedMappoint;
    }

    public void setSelectedPillar(Clutch selectedPillar) {
        this.selectedMappoint = selectedPillar;
    }

    public int getZoomMap() {
        return zoomMap;
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






    
    
   
    
  
}
