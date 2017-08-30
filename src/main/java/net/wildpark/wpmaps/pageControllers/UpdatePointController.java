/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.pageControllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import net.wildpark.wpmaps.entitys.MapPoint;
import net.wildpark.wpmaps.facades.PointFacade;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Inquisitor
 */
@Named(value = "updatePointController")
@SessionScoped
public class UpdatePointController implements Serializable{
    @EJB
    private PointFacade mapFacade;
    private MapPoint list;
    
    private String c = "dsfsdffsf";
    private int index;
    
    
//    public void init() {   
//        list = mapFacade.find(MapPoint.class, index );
//            for (MapPoint e:list) {
//                System.out.println("nnnnnnn" + e.getAddress());
//            }
//    }
    
    public void findProfessor(int id) {
        list = mapFacade.find(id);
        System.out.println(list);
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

    public MapPoint getList() {
        return list;
    }

    public void setList(MapPoint list) {
        this.list = list;
    }
    
    
    
    
}
