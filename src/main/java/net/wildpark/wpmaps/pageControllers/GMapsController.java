/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.pageControllers;

import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.sql.rowset.serial.SerialException;
import javax.swing.ImageIcon;
import net.wildpark.wpmaps.entitys.Cabel;
import net.wildpark.wpmaps.entitys.Clutch;
import net.wildpark.wpmaps.entitys.DrawWell;
import net.wildpark.wpmaps.entitys.House;
import net.wildpark.wpmaps.entitys.MapPoint;
import net.wildpark.wpmaps.entitys.Pillar;
import net.wildpark.wpmaps.enums.DrawWellOwner;
import net.wildpark.wpmaps.enums.DrawWellType;
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
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.map.GeocodeResult;


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
    private String owner;
    private PillarMaterial matheriallPillar;
    private PillarType typePillar;
    private PillarCapacity capacityPillar;
    private ObjectType obj_type;
    private HouseType typeOfHouse;
    private HouseOwner ownerofHouse;
    private DrawWellOwner ownerDrawWell;
    private DrawWellType type_drawWell;
    
    private int id;
    private Boolean capacityCabel;
    
    private int zoomMap = 13;
    private boolean showMarker = false; 
    

    private double lat;     
    private double lng;
    
    
    private List<MapPoint> list; 
     
    //Staff mappoint =  new Staff();
    
    Pillar pillar = new Pillar();
    House house = new House();
    DrawWell draw_well = new DrawWell();
    MapPoint point = new MapPoint();
    Cabel cabel = new Cabel();
    
    Clutch clutch = new Clutch();
    
    List<LatLng> coord = new ArrayList<>();
    List<Marker> markers;  
    List<Clutch> clutchs = new ArrayList<>();
    List<Cabel> cabels = new ArrayList<>();
    
    byte[] image;
    
    private String centerGeoMap = "46.9422145,31.9990089";
    
//
    @PostConstruct
    public void init() {
        model = new DefaultMapModel();
        list = mapFacade.findAll();   
                       
        for (MapPoint e:list) {
            model.addOverlay(new Marker(new LatLng(e.getLat(), e.getLng()),String.valueOf(e.getId()),e,"../resources/marker/"+e.getDecriminatorValue()+"_marker.png"));                
        }   
    }
        
    public void onGeocode(GeocodeEvent event) {

        List<GeocodeResult> results = event.getResults();
         
        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();
            zoomMap = 17;
             
        }
    } 
    
    
    public void buttonAction() {
        System.out.println("All right");
    }
        
    
    public StreamedContent getImage() {
        byte[] imageInByteArray = point.getPic();
        return new DefaultStreamedContent(new ByteArrayInputStream(imageInByteArray), "image/png/jpg");
    }
    
    public void addMarkerP() {

        
        
        pillar.setLat(lat);
        pillar.setLng(lng);
        pillar.setMaterial(matheriallPillar);
        pillar.setNumberStation(numberStation);
        pillar.setTransportStation(transportStation);
        pillar.setType(typePillar);
        pillar.setOwner(owner.toString());
        
        clutch.setAddress("sdfsdf");
        clutch.setCassetsCount(4);
        
        point.setClutch(Collections.singletonList(clutch));
        pillar.setClutch(clutchs);
        
        pillarFacade.create(pillar);
        id = pillar.getId();
        marker = new Marker(new LatLng(lat, lng), String.valueOf(id),pillar,"../resources/marker/pillar_marker.png" );
        model.addOverlay(marker);
//        //list.clear();
        init();
        //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("@all");
    }
    public void addMarkerH() {

        house.setLat(lat);
        house.setLng(lng);
        house.setType_house(typeOfHouse);
        house.setOwner(ownerofHouse.toString());
        
        houseFacade.create(house);
        id = house.getId();
        marker = new Marker(new LatLng(lat, lng), String.valueOf(id),house,"../resources/marker/house_marker.png" );
        model.addOverlay(marker);
//        //list.clear();
        init();
        //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("@all");
    }   
    public void addMarkerW() throws FileNotFoundException {
        File file = new File("C:\\Users\\zekar\\Pictures\\Saved Pictures\\WJBG_WepKcc.jpg");
        byte[] picInByte = new byte[(int) file.length()];
        FileInputStream fileStream = new FileInputStream(file);
        try {
            fileStream.read(picInByte);
            fileStream.close();
            draw_well.setPic(picInByte);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        draw_well.setLat(lat);
        draw_well.setLng(lng);
        draw_well.setOwner(ownerDrawWell.toString());
        draw_well.setType_draw_well(type_drawWell);
        
        drawWellFacade.create(draw_well);
        id = draw_well.getId();
        marker = new Marker(new LatLng(lat, lng), String.valueOf(id),draw_well,"../resources/marker/draw_marker.png" );
        model.addOverlay(marker);
//        //list.clear();
        init();
        //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("@all");
    }
    public void deleteMarker(){        
        //point = (MapPoint) marker.getData();
        System.out.println("Select id  " + point.getId());
        point = mapFacade.find(point.getId());
        if(point != null){            
            mapFacade.remove(point);
            //list.clear();
            init();
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("@all");
        }
    }
//       
//
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();   
        point = (MapPoint) marker.getData(); 
    }
   
    
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
    public DrawWellOwner[] getDrawWellOwner() {
        return DrawWellOwner.values();
    }
    public DrawWellType[] getDrawWellType() {
        return DrawWellType.values();
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
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

    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    public void setCenterGeoMap(String centerGeoMap) {
        this.centerGeoMap = centerGeoMap;
    }

    public MapPoint getPoint() {
        return point;
    }

    public void setPoint(MapPoint point) {
        this.point = point;
    }

    public List<Cabel> getCabels() {
        return cabels;
    }

    public void setCabels(List<Cabel> cabels) {
        this.cabels = cabels;
    }

    public Cabel getCabel() {
        return cabel;
    }

    public void setCabel(Cabel cabel) {
        this.cabel = cabel;
    }

    public Clutch getClutch() {
        return clutch;
    }

    public void setClutch(Clutch clutch) {
        this.clutch = clutch;
    }

    






    
    
   
    
  
}
