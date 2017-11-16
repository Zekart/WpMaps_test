/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.basicPageControllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import net.wildpark.wpmaps.entitys.Pillar;
import net.wildpark.wpmaps.facades.PillarFacade;

/**
 *
 * @author Zekart
 */
@ManagedBean
@SessionScoped
public class Find_controller implements Serializable{
    
    @EJB
    private PillarFacade pillarFacade;
    
    private List<Pillar> pillar_list;
    
    private String txt1;

    public Find_controller() {
        
    }
    @PostConstruct
    public void initPoint() {
        pillar_list = pillarFacade.findAll();
    }
    
    public List<String> completeText(String query) {

        List<String> results = new ArrayList<>();
        for(int i = 0; i < pillar_list.size(); i++) {
//            if (pillar_list.get(i).) {
//                
//            }
            results.add(query + pillar_list.get(0));
        }      
        
        return results;
    }

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public List<Pillar> getPillar_list() {
        return pillar_list;
    }

    public void setPillar_list(List<Pillar> pillar_list) {
        this.pillar_list = pillar_list;
    }
    
    
    
    
    
}
