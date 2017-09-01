/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.pageControllers;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.wildpark.wpmaps.entitys.MapPoint;
import net.wildpark.wpmaps.facades.PointFacade;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Inquisitor
 */
@Named(value = "updatePointController")
@SessionScoped
public class UpdatePointController implements Serializable{
    @EJB
    private PointFacade mapFacade;
       
    private MapPoint a_point;
    
    private String c ;
    private String hid;
    private int index;

    private MapPoint addr;
    
    
    @PostConstruct
    public void init() {
        String value = FacesContext.getCurrentInstance().
             getExternalContext().getRequestParameterMap().get("hiddenId");
        hid = value;
        FacesMessage msg = new FacesMessage("hiden " + hid);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    private String getId(){
        String value = FacesContext.getCurrentInstance().
             getExternalContext().getRequestParameterMap().get("hiddenId");
        return value;
    }
    
    public void onRowEdit(RowEditEvent event) {
        int id = 1;

        a_point = (MapPoint) mapFacade.find(id);
        a_point.setAddress(c);
        
        mapFacade.merge(a_point);
        RequestContext.getCurrentInstance().update(":ob_table");

        
        FacesMessage msg = new FacesMessage("Объект изменен " + getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);


    }
     
    public void onRowEditClutch(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Муфта изменена");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowEditCabel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Кабель изменен");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Отмена редактирования");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }   

    public MapPoint getAddr() {
        return addr;
    }

    public void setAddr(MapPoint addr) {
        this.addr = addr;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }
    
    
}
