/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.pageControllers;

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
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.wildpark.wpmaps.entitys.Cabel;
import net.wildpark.wpmaps.entitys.Clutch;
import net.wildpark.wpmaps.entitys.ConnectPoint;
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
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import net.wildpark.wpmaps.enums.PillarOwner;
import net.wildpark.wpmaps.enums.PillarType;
import net.wildpark.wpmaps.enums.PillarMaterial;
import net.wildpark.wpmaps.facades.CableFacade;
import net.wildpark.wpmaps.facades.ClutchFacade;
import net.wildpark.wpmaps.facades.ConnectPointFacade;
import net.wildpark.wpmaps.facades.HouseFacade;
import net.wildpark.wpmaps.facades.PillarFacade;
import net.wildpark.wpmaps.facades.DrawWellFacade;
import net.wildpark.wpmaps.facades.PointFacade;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
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
    @EJB
    private CableFacade cabFacade;   
    @EJB
    private ClutchFacade clutchFacade;
    @EJB
    private ConnectPointFacade conFacade;

    
    private MapModel model;
    private Marker marker;
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
    
    private int id;
    private Boolean zoomPoint = false;
    
    private int zoomMap = 13;
    

    private double lat;     
    private double lng;
    private byte[] image;    
    private boolean flag;
    private boolean selectOne;
    private int idClutch;
    private LatLng cord;
    private List<MapPoint> list; 
    private List<ConnectPoint> listConnect;
     
    //Staff mappoint =  new Staff();
    
    Pillar pillar = new Pillar();
    House house = new House();
    DrawWell draw_well = new DrawWell();
    MapPoint point = new MapPoint();
    Cabel cabel = new Cabel();

    
    ConnectPoint connect_point = new ConnectPoint();

    
    Clutch clutch = new Clutch();    
    PointWizard pz = new PointWizard(); 
    
    List<Clutch> clutc_rend = new ArrayList<>();
    List<Cabel> cabels = new ArrayList<>();
    List<LatLng> coord = new ArrayList<LatLng>(); 
    List<Fiber> fiber = new ArrayList<>();
    
    private String centerGeoMap = "46.9422145,31.9990089";
    
    
    
    //@PostConstruct
    public void initPoint() {
        Polyline polyline = new Polyline();
                   
        polyline.setStrokeWeight(2);
        polyline.setStrokeColor("#FF9930");
        polyline.setStrokeOpacity(1);
               
        model = new DefaultMapModel();
        list = mapFacade.findAll();     
        
        for (MapPoint e:list) {
            model.addOverlay(new Marker(new LatLng(e.getLat(), e.getLng()),String.valueOf(e.getId()),e,"../resources/marker/"+e.getDecriminatorValue()+"_marker.png"));                
        }  
        listConnect = conFacade.findAll();
        for (ConnectPoint c:listConnect) {            
            polyline.getPaths().add(new LatLng(mapFacade.find(c.getFromPoint()).getLat(), mapFacade.find(c.getFromPoint()).getLng()));
            polyline.getPaths().add(new LatLng(mapFacade.find(c.getToPoint()).getLat(), mapFacade.find(c.getToPoint()).getLng()));
        }

        model.addOverlay(polyline);
    }
        
    public void onGeocode(GeocodeEvent event) {

        List<GeocodeResult> results = event.getResults();
         
        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();
            zoomMap = 17;             
        }
    } 
  
    
    public void checkFormAdd() {
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
  
        if(pz.isSkip()!= true){
            pillar.setClutch(clutc_rend);
        }
        
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
        
        if(pz.isSkip()!= true){
            house.setClutch(clutc_rend);
        }
        
        houseFacade.create(house);
        id = house.getId();
        marker = new Marker(new LatLng(lat, lng), String.valueOf(id),house,"../resources/marker/house_marker.png" );
        model.addOverlay(marker);
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
        
        if(pz.isSkip()!= true){
            draw_well.setClutch(clutc_rend);
            
//            Cabel cabl = new Cabel();
//            
//            cabl.setClutch(clutc_rend);
//            cabels.add(cabl);
//            
//            cl.setCable(cabels);
//            
//            clutchFacade.create(cl);
            
            
            //draw_well.setCable(cabels);
        }        
        System.out.println(cabels);
        drawWellFacade.create(draw_well);
        id = draw_well.getId();
        marker = new Marker(new LatLng(lat, lng), String.valueOf(id),draw_well,"../resources/marker/draw_marker.png" );
        model.addOverlay(marker);

//        //list.clear();
        initPoint();
        clutc_rend.clear();
        //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("@all");
    }

    public void deleteMarker(){        
        point = mapFacade.find(id); 
            for (ConnectPoint b : listConnect) {
                if (b.getFromPoint()== id ||  b.getToPoint() == id) {
                    conFacade.remove(b);
                }
            }         
        if(point != null){                   
            mapFacade.remove(point);
            //list.clear();
            initPoint();            
        }

    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();   
        point = (MapPoint) marker.getData(); 
        id = point.getId();
        ss(point.getLat(),point.getLng());
         
    }
    
    public void newLine(ActionEvent actionEvent) {
        this.clutc_rend.add(new Clutch());
    }
    public void newLineC(ActionEvent actionEvent) {
        this.cabels.add(new Cabel());
    }
    public void newLineF(ActionEvent actionEvent) {
        this.fiber.add(new Fiber());
    }    
    private void ss(double lat, double lng){
        cord = new LatLng(lat, lng);
    } 
    
    public void connectPillar(){
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Режим добавления", "Выберите 2 маркер"));
        Polyline polyline = new Polyline();
           
        polyline.setStrokeWeight(2);
        polyline.setStrokeColor("#FF9900");
        polyline.setStrokeOpacity(1);

        coord.add(new LatLng(point.getLat(), point.getLng()));

        if (coord.size()==2) {
            if(coord.get(0).equals(coord.get(1))){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ошибка", "Попытка соединить один и тот же объект"));
                coord.clear();
            }else{
            for (LatLng en : coord ){
                polyline.getPaths().add(en);
            }
            model.addOverlay(polyline);
            
            RequestContext.getCurrentInstance().update("gmap");
            coord.clear();            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Режим соединения", "Успешно"));
            connect_point.setToPoint(point.getId());            
            conFacade.create(connect_point);
            initPoint();
            }            
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Режим соединения", "Выберите 2 маркер"));
            connect_point.setFromPoint(point.getId());            
        } 
    }
    

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
        
        if(zoomMap == 15){           
            if(zoomPoint != true){ 
                initPoint();
                zoomPoint = true;
                RequestContext.getCurrentInstance().update("gmap");
                //FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("gmap");
            }                       
        }else{
            if (zoomMap == 14) {
                if(zoomPoint != false){
                    model.getMarkers().clear();
                    model.getPolylines().clear();
                    RequestContext.getCurrentInstance().update("gmap");                    
                }                
                zoomPoint = false;                
            }           
        }
    }
    
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     public List<Clutch> getLignes() {
        return this.clutc_rend;
    }

    public void setLignes(List<Clutch> clutc_rend) {
        this.clutc_rend = clutc_rend;
    }   

    public Cabel getCabel() {
        return cabel;
    }

    public void setCabel(Cabel cabel) {
        this.cabel = cabel;
    }

    public List<Clutch> getClutc_rend() {
        return clutc_rend;
    }

    public void setClutc_rend(List<Clutch> clutc_rend) {
        this.clutc_rend = clutc_rend;
    }

    public List<Fiber> getFiber() {
        return fiber;
    }

    public void setFiber(List<Fiber> fiber) {
        this.fiber = fiber;
    }

    public int getIdClutch() {
        return idClutch;
    }

    public void setIdClutch(int idClutch) {
        this.idClutch = idClutch;
    }


    
    
  
}
