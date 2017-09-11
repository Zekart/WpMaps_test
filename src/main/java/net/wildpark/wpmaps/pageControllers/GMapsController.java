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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import net.wildpark.wpmaps.entitys.Clutch;
import net.wildpark.wpmaps.entitys.ConnectPoint;
import net.wildpark.wpmaps.entitys.DrawWell;
import net.wildpark.wpmaps.entitys.House;
import net.wildpark.wpmaps.entitys.MapPoint;
import net.wildpark.wpmaps.entitys.Pillar;
import net.wildpark.wpmaps.entitysController.PointController;
import net.wildpark.wpmaps.enums.ObjectType;
import net.wildpark.wpmaps.facades.ClutchFacade;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import net.wildpark.wpmaps.facades.ConnectPointFacade;
import net.wildpark.wpmaps.facades.HouseFacade;
import net.wildpark.wpmaps.facades.PillarFacade;
import net.wildpark.wpmaps.facades.DrawWellFacade;
import net.wildpark.wpmaps.facades.PointFacade;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.StateChangeEvent;
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
    private ClutchFacade clutchFacade;    
    @EJB
    private DrawWellFacade drawWellFacade;
    @EJB
    private HouseFacade houseFacade;
    @EJB
    private PillarFacade pillarFacade;
    @EJB
    private PointFacade mapFacade;
    @EJB
    private ConnectPointFacade conFacade;
    
    //private boolean pillarflag = false;
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    
    private MapModel model;
    private Marker marker;
    private Polyline selectPolyLine;

    private String decriminatorValue;

    private ObjectType obj_type;

    
    private int id;
    private Boolean zoomPoint = false;
    
    private int zoomMap = 13;
   
    private boolean flag;
    private boolean selectOne;


    private List<MapPoint> list; 
    private List<ConnectPoint> listConnect;
    
    List<MapPoint> temp = new ArrayList<>();
    MapPoint selected_point = new MapPoint();
    
    private List<Pillar> sel_pillar_list = new ArrayList<>();
    Pillar pillar = new Pillar();
    private List<House> sel_house_list = new ArrayList<>();
    House house = new House();
    private List<DrawWell> sel_draw_list = new ArrayList<>();
    DrawWell draw = new DrawWell();
    private List<Clutch> select_clutch = new ArrayList<>();
    private Clutch clutch = new Clutch();
    private Clutch selected_clutch_toGet_id;
    
    List<LatLng> coord = new ArrayList<>(); 
    List<House> h_temp = new ArrayList<>();
    House house_point = new House();
    
    ConnectPoint connect_point = new ConnectPoint();
       
    PointWizard pz = new PointWizard(); 


    private String centerGeoMap = "46.9422145,31.9990089";
    
    PointController pc = new PointController();
  
    //@PostConstruct
    public void initPoint() {
        
        model = new DefaultMapModel();
        
        list = mapFacade.findAll();     

        for (MapPoint e:list) {
            model.addOverlay(new Marker(new LatLng(e.getLat(), e.getLng()),String.valueOf(e.getId()),e,"../resources/marker/"+e.getDecriminatorValue()+"_marker.png"));                

        }      
        
        listConnect = conFacade.findAll();
        for (ConnectPoint c:listConnect) {  
            Polyline polyline = new Polyline();
                   
            polyline.setStrokeWeight(2);
            polyline.setStrokeColor("#FF9930");
            polyline.setStrokeOpacity(1);
            
            polyline.getPaths().add(new LatLng(mapFacade.find(c.getToPoint()).getLat(), mapFacade.find(c.getToPoint()).getLng()));
            polyline.getPaths().add(new LatLng(mapFacade.find(c.getFromPoint()).getLat(), mapFacade.find(c.getFromPoint()).getLng()));
//            if (polyline.getPaths().size() == 2){
            System.out.println(polyline.getPaths().size());
//                continue;
//            }
            model.addOverlay(polyline);
            
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
//    public void checkFormAdd() {
//        System.out.println("All right");
//    }
//    public StreamedContent getImage() throws IOException {
////        if(selected_point.getPic() != null){
////            image = selected_point.getPic();
////        }else{
//            image = getBytesFile();
////        }
//        return new DefaultStreamedContent(new ByteArrayInputStream(image), "image/png/jpg");
//    }
//    
//    public byte[] getBytesFile() throws IOException{
//        
//        InputStream iStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("../resources/marker/Столб_marker.png");
//        Path path = Paths.get(iStream.toString());
//        byte[] data = Files.readAllBytes(path);
//        return data;
//    }
    public void deleteMarker(){        
        selected_point = mapFacade.find(id);
            for (ConnectPoint b : listConnect) {
                if (b.getFromPoint()== id ||  b.getToPoint() == id) {
                    conFacade.remove(b);
                }
            }         
        if(selected_point != null){                   
            mapFacade.remove(selected_point);
            initPoint();            
        }
    }
    
    public void changeMarker(){
        selected_point =  mapFacade.find(id);
        temp.add(selected_point);

    }  

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();   
                
        selected_point = (MapPoint) marker.getData(); 
        id = selected_point.getId();
        decriminatorValue = selected_point.getDecriminatorValue();
        //ss(selected_point.getLat(),selected_point.getLng());  
    }
    
   
    
    public void connectPillar(){
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Режим добавления", "Выберите 2 маркер"));
        Polyline polyline = new Polyline();
           
        polyline.setStrokeWeight(2);
        polyline.setStrokeColor("#FF9900");
        polyline.setStrokeOpacity(1);

        coord.add(new LatLng(selected_point.getLat(), selected_point.getLng()));

        if (coord.size()==2) {
            if(coord.get(0).equals(coord.get(1))){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ошибка", "Попытка соединить один и тот же объект"));
                coord.clear();
            }else{
                for (LatLng en : coord ){
                    polyline.getPaths().add(en);
                    System.out.println(polyline.getPaths());
                }
                model.addOverlay(polyline);

                RequestContext.getCurrentInstance().update("gmap");           
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Режим соединения", "Успешно"));
                connect_point.setToPoint(selected_point.getId());            
                conFacade.create(connect_point);
                coord.clear();
                //initPoint();
            }            
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Режим соединения", "Выберите 2 маркер"));
            connect_point.setFromPoint(selected_point.getId());            
        } 
    }
    
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
    
    
    public void viewBugCustomized() {

        
        Map<String,Object> optionsBugPage = new HashMap<>();
        optionsBugPage.put("modal", true);
        optionsBugPage.put("width", 840);
        optionsBugPage.put("height", 340);
        optionsBugPage.put("contentWidth", "100%");
        optionsBugPage.put("contentHeight", "100%");
        optionsBugPage.put("headerElement", "customheader");
        
	RequestContext.getCurrentInstance().openDialog("bugReportPage", optionsBugPage, null);

    }    
    public void viewBugListCustomized() {

        
        Map<String,Object> optionsBugList = new HashMap<>();
        optionsBugList.put("modal", true);
        optionsBugList.put("width", 840);
        optionsBugList.put("height", 340);
        optionsBugList.put("contentWidth", "100%");
        optionsBugList.put("contentHeight", "100%");
        optionsBugList.put("headerElement", "customheader");
        
	RequestContext.getCurrentInstance().openDialog("bugListPage", optionsBugList, null);

    }    
    
    
    public void viewCarsCustomized() {
        this.sel_pillar_list.clear();
        this.sel_house_list.clear();
        this.sel_draw_list.clear();
        this.select_clutch.clear();

        
        Map<String,Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("width", 840);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");
        
        switch(decriminatorValue) {
        case "Столб": 
            pillar = pillarFacade.find(id);              
            this.sel_pillar_list.add(pillar);
            this.select_clutch = pillar.getClutch();
	    RequestContext.getCurrentInstance().openDialog("pillar_change", options, null);
		break;
	case "Колодец": 
            draw = drawWellFacade.find(id);
            this.sel_draw_list.add(draw);
            this.select_clutch = draw.getClutch();
	    RequestContext.getCurrentInstance().openDialog("draw_change", options, null);
		break;
	case "Дом": 
            house = houseFacade.find(id);
            this.sel_house_list.add(house);
            this.select_clutch = house.getClutch();
	    RequestContext.getCurrentInstance().openDialog("house_change", options, null);
		break;
	default: 
	        System.err.println("error");
	    break;
        }
    }      
    public void onRowEdit(RowEditEvent event) {
        switch(decriminatorValue) {
        case "Столб": 
    	    pillarFacade.merge(pillar);           
		break;
	case "Колодец": 
	    drawWellFacade.merge(draw);
		break;
	case "Дом": 
	    houseFacade.merge(house);
		break;
	default: 
	        System.err.println("error");
	    break;
        }        
        
        FacesMessage msg = new FacesMessage("Сохранено");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
  
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        DataTable s = (DataTable) event.getSource();
        String m = s.getRowData().toString();
        String ms = m.substring(m.indexOf('=')+ 1, m.indexOf(','));
        int ids = Integer.parseInt(ms);

        selected_clutch_toGet_id = (Clutch)s.getRowData();

        clutchFacade.merge(selected_clutch_toGet_id);
        
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Сохранено", "Old: " + oldValue + ", New:" + newValue );
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }   
    

    public List<Clutch> getSelect_clutch() {
        return select_clutch;
    }

    public void setSelect_clutch(List<Clutch> select_clutch) {
        this.select_clutch = select_clutch;
    }
    
    
    
    
    public List<Pillar> getSel_pillar_list() {
        return sel_pillar_list;
    }


    public void setSel_pillar_list(List<Pillar> sel_pillar_list) {
        this.sel_pillar_list = sel_pillar_list;
    }

    public List<House> getSel_house_list() {
        return sel_house_list;
    }

    public void setSel_house_list(List<House> sel_house_list) {
        this.sel_house_list = sel_house_list;
    }

    public List<DrawWell> getSel_draw_list() {
        return sel_draw_list;
    }

    public void setSel_draw_list(List<DrawWell> sel_draw_list) {
        this.sel_draw_list = sel_draw_list;
    }

    public List<MapPoint> getTemp() {
        return temp;
    }

    public void setTemp(List<MapPoint> temp) {
        this.temp = temp;
    }
 
    
    public ObjectType[] getObjectType() {
        return ObjectType.values();
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

    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    public void setCenterGeoMap(String centerGeoMap) {
        this.centerGeoMap = centerGeoMap;
    }

    public MapPoint getSelected_point() {
        return selected_point;
    }

    public void setSelected_point(MapPoint selected_point) {
        this.selected_point = selected_point;
    }


    public void setFlagToTrue() {
        this.flag = true;
    }

    public boolean isFlag() {
        return flag;
    }   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDecriminatorValue() {
        return decriminatorValue;
    }

    public void setDecriminatorValue(String decriminatorValue) {
        this.decriminatorValue = decriminatorValue;
    }

    public Clutch getSelected_clutch_toGet_id() {
        return selected_clutch_toGet_id;
    }

    public void setSelected_clutch_toGet_id(Clutch selected_clutch_toGet_id) {
        this.selected_clutch_toGet_id = selected_clutch_toGet_id;
    }
    
}
