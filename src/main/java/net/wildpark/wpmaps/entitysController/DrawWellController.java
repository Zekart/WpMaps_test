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
import net.wildpark.wpmaps.enums.DrawWellOwner;
import net.wildpark.wpmaps.enums.DrawWellType;
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

    private DrawWellOwner ownerDrawWell;
    private DrawWellType type_drawWell;

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

    public DrawWellOwner[] getDrawWellOwner() {
        return DrawWellOwner.values();
    }
    public DrawWellType[] getDrawWellType() {
        return DrawWellType.values();
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
    
}
