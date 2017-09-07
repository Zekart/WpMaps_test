/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitysController;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import net.wildpark.wpmaps.entitys.MapPoint;
import net.wildpark.wpmaps.facades.PointFacade;


/**
 *
 * @author zekart
 */
@Named(value = "pointController")
@SessionScoped
public class PointController implements Serializable {

    @EJB
    private PointFacade pointFacade;
    private MapPoint mp = new MapPoint();

    public PointController() {
    }
    
    public String add(){
        this.pointFacade.create(this.mp);
        return "index";
    }
    
    public List<MapPoint> findAll(){
        return this.pointFacade.findAll();
    }

    public MapPoint getMp() {
        return mp;
    }

    public void setMp(MapPoint mp) {
        this.mp = mp;
    }
    
    
    
}
