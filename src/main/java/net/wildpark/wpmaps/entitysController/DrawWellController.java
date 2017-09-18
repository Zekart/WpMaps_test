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
import net.wildpark.wpmaps.entitys.DrawWell;
import net.wildpark.wpmaps.facades.DrawWellFacade;

/**
 *
 * @author zekart
 */
@Named(value = "drawWellController")
@SessionScoped
public class DrawWellController implements Serializable {

    @EJB
    private DrawWellFacade drawWellFacade;
    private DrawWell d = new DrawWell();

    public DrawWellController() {
    }
    
    public String add(){
        this.drawWellFacade.create(this.d);
        return "index";
    }
    
    public void delete(){
        this.drawWellFacade.remove(this.d);
    }
    
    public List<DrawWell> findAll(){
        return this.drawWellFacade.findAll();
    }

    public DrawWell getD() {
        return d;
    }

    public void setD(DrawWell d) {
        this.d = d;
    }
    
}
