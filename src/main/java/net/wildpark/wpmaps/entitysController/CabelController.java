/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitysController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import net.wildpark.wpmaps.entitys.Cabel;
import net.wildpark.wpmaps.entitys.Clutch;
import net.wildpark.wpmaps.facades.CableFacade;
import org.primefaces.context.RequestContext;

/**
 *
 * @author zekart
 */
@Named(value = "cabelController")
@SessionScoped
public class CabelController implements Serializable{
    
    @EJB
    private CableFacade cableFacade;
    
    
    private int capacity;
    private String inplace;
    private int lenthCable;
    private String marking;
    private int moduleCount;
    private String outPlace;
    private String production;
    
    private List<Cabel> cabel_list = new ArrayList<>();
    private Cabel cabel = new Cabel();
    
//    @PostConstruct
//    public void initPoint() {
//        this.cabel_list.add(new Cabel());
//    }
    
    public String cabelRegister(int count){
        for (int i = 0; i < count; i++) {
            this.cabel_list.add(cabel);
        }
        
        this.cabel = new Cabel();
        return "";
    }
    
    
    public String setCabelList(int index){
        System.out.println("Index" + index);
        return "";
    }
    
    
    public Cabel clearForm(int index){
        if (cabel_list.isEmpty()) {
            for (int i = 0; i < index; i++) {
                cabel_list.add(i, new Cabel());
            }            
        }
        return cabel_list.get(index -1);
        
    }
        

    public List<Cabel> getCabel_list() {
        return cabel_list;
    }

    public void setCabel_list(List<Cabel> cabel_list) {
        this.cabel_list = cabel_list;
    }

    public Cabel getCabel() {
        return cabel;
    }

    public void setCabel(Cabel cabel) {
        this.cabel = cabel;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getInplace() {
        return inplace;
    }

    public void setInplace(String inplace) {
        this.inplace = inplace;
    }

    public int getLenthCable() {
        return lenthCable;
    }

    public void setLenthCable(int lenthCable) {
        this.lenthCable = lenthCable;
    }

    public String getMarking() {
        return marking;
    }

    public void setMarking(String marking) {
        this.marking = marking;
    }

    public int getModuleCount() {
        return moduleCount;
    }

    public void setModuleCount(int moduleCount) {
        this.moduleCount = moduleCount;
    }

    public String getOutPlace() {
        return outPlace;
    }

    public void setOutPlace(String outPlace) {
        this.outPlace = outPlace;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    
    
}
