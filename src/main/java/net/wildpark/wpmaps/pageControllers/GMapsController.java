/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.pageControllers;

import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.map.GeocodeResult;
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
    private String owner;
    private String address;
    private PillarMaterial matheriallPillar;
    private PillarType typePillar;
    private PillarCapacity capacityPillar;
    private ObjectType obj_type;
    private HouseType typeOfHouse;
    private HouseOwner ownerofHouse;
    private DrawWellOwner ownerDrawWell;
    private DrawWellType type_drawWell;
    
    private int id;
    private Boolean zoomPoint = false;
    
    private int zoomMap = 13;
    private boolean showMarker = false; 
    

    private double lat;     
    private double lng;
    private byte[] image;
    
    private boolean flag;
   
    private List<MapPoint> list; 
     
    //Staff mappoint =  new Staff();
    
    Pillar pillar = new Pillar();
    House house = new House();
    DrawWell draw_well = new DrawWell();
    MapPoint point = new MapPoint();
    Cabel cabel = new Cabel();
    
    Clutch clutch = new Clutch();
    
    PointWizard pz = new PointWizard();
    
    List<LatLng> coord = new ArrayList<>();
    List<Marker> markers;  
    List<Clutch> clutchs = new ArrayList<>();
    List<Cabel> cabels = new ArrayList<>();
    
    private String centerGeoMap = "46.9422145,31.9990089";
    
//
    //@PostConstruct
    public void initPoint() {
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
        
    
    public StreamedContent getImage() throws IOException {
        byte[] imageInByteArray;
        if(point.getPic() != null){
            image = point.getPic();
        }else{
            image = getBytesFile();
        }
        return new DefaultStreamedContent(new ByteArrayInputStream(image), "image/png/jpg");
    }
    
    public byte[] getBytesFile() throws IOException{
        
        InputStream iStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/No-image-found.jpg");
        Path path = Paths.get(iStream.toString());
        byte[] data = Files.readAllBytes(path);
        return data;
    }
    
    public void addMarkerP() {       
        pillar.setLat(lat);
        pillar.setLng(lng);
        pillar.setMaterial(matheriallPillar);
        pillar.setNumberStation(numberStation);
        pillar.setTransportStation(transportStation);
        pillar.setType(typePillar);
        pillar.setOwner(owner);
        pillar.setAddress(address);
  
        if(pz.isSkip()!= false){
            pillar.setClutch(Collections.singletonList(clutch));
        }
        //pillar.setClutch(Collections.singletonList(clutch));
        //pillar.setClutch(clutchs);
        
        pillarFacade.create(pillar);
        id = pillar.getId();
        marker = new Marker(new LatLng(lat, lng), String.valueOf(id),pillar,"../resources/marker/pillar_marker.png" );
        model.addOverlay(marker);
//        //list.clear();
        initPoint();
        //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("@all");
//                      RequestContext requestContext = RequestContext.getCurrentInstance();  
//                requestContext.execute("PF('wizp').hide()");
    }
    public void addMarkerH() {

        house.setLat(lat);
        house.setLng(lng);
        house.setType_house(typeOfHouse);
        house.setOwner(owner);
        house.setAddress(address);
        
        if(pz.isSkip()!= false){
            house.setClutch(Collections.singletonList(clutch));
        }
        
        houseFacade.create(house);
        id = house.getId();
        marker = new Marker(new LatLng(lat, lng), String.valueOf(id),house,"../resources/marker/house_marker.png" );
        model.addOverlay(marker);
//        //list.clear();
        initPoint();
        
        //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("@all");
    }   
    public void addMarkerW() throws FileNotFoundException {
//        File file = new File("C:\\Users\\zekar\\Pictures\\Saved Pictures\\WJBG_WepKcc.jpg");
//        byte[] picInByte = new byte[(int) file.length()];
//        FileInputStream fileStream = new FileInputStream(file);
//        try {
//            fileStream.read(picInByte);
//            fileStream.close();
//            draw_well.setPic(picInByte);
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }

        //draw_well.setPic(uplView.getImgByte());
        
        draw_well.setLat(lat);
        draw_well.setLng(lng);
        draw_well.setOwner(owner);
        draw_well.setType_draw_well(type_drawWell);
        draw_well.setAddress(address);
        
        System.out.println("Skip  " + pz.isSkip());
        if(pz.isSkip()!= true){
            draw_well.setClutch(Collections.singletonList(clutch));
        }        
        
        drawWellFacade.create(draw_well);
        id = draw_well.getId();
        marker = new Marker(new LatLng(lat, lng), String.valueOf(id),draw_well,"../resources/marker/draw_marker.png" );
        model.addOverlay(marker);
        
//        //list.clear();
        initPoint();
        //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("@all");
    }
    public void deleteMarker(){        
        System.out.println("Select id  " + id);
        point = mapFacade.find(id);
        //System.out.println("Point" + point);
        if(point != null){            
            mapFacade.remove(point);
            //list.clear();
            initPoint();
            //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("@all");

        }
    }
//       
//
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();   
        point = (MapPoint) marker.getData(); 
        System.out.println("Id" +  point.getId());
        id = point.getId();
    }

    public void connectObj(ActionEvent actionEvent) {
        System.out.println("Fo");
    }
    
//    public void connectPillar(){
//        Polyline polyline = new Polyline();
//        
//        polyline.setStrokeWeight(2);
//        polyline.setStrokeColor("#FF9900");
//        polyline.setStrokeOpacity(1);
//        
//        coord.add(new LatLng(point.getLat(), point.getLng()));
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
//            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Режим соединения", "Успешно"));
//            model.addOverlay(polyline);
//            RequestContext.getCurrentInstance().update("gmap");
//            coord.clear();                   
//        }else{
//            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Режим добавления", "Выберите 2 маркер"));            
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
        LatLng center = event.getCenter();
        centerGeoMap = center.getLat()+","+center.getLng();
        
        System.out.println("zoom : " + zoomMap);
        if(zoomMap == 16){
            
            if(zoomPoint != true){
                System.out.println("Show in");  
                initPoint();
                zoomPoint = true;
                RequestContext.getCurrentInstance().update("gmap");
                //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gmap");
            }
            
            
        }else{
            if (zoomMap == 15) {
                if(zoomPoint != false){
                    System.out.println("Show out");  
                    model.getMarkers().clear();
                    RequestContext.getCurrentInstance().update("gmap");
                    
                }
                
                zoomPoint = false;
                
            }
            
            
        }
//        for (Marker m : markers) {
//            m.setVisible(showMarker);
//
//        } 
//
//        System.out.println("marker" + markers);

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


    public void setFlagToTrue() {
        this.flag = true;
    }

    public boolean isFlag() {
        return flag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Clutch> getClutchs() {
        return clutchs;
    }

    public void setClutchs(List<Clutch> clutchs) {
        this.clutchs = clutchs;
    }



    
    
   
    
  
}
