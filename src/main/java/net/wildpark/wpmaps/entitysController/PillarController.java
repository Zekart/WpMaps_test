/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitysController;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import net.wildpark.wpmaps.entitys.Clutch;
import net.wildpark.wpmaps.entitys.MapPoint;
import net.wildpark.wpmaps.entitys.Pillar;
import net.wildpark.wpmaps.enums.PillarCapacity;
import net.wildpark.wpmaps.enums.PillarMaterial;
import net.wildpark.wpmaps.enums.PillarOwner;
import net.wildpark.wpmaps.enums.PillarType;
import net.wildpark.wpmaps.facades.PillarFacade;
import net.wildpark.wpmaps.pageControllers.GMapsController;
import net.wildpark.wpmaps.pageControllers.PointWizard;

/**
 *
 * @author zekart
 */
@Named(value = "pillarController")
@SessionScoped
public class PillarController implements Serializable {

    @EJB
    private PillarFacade pillarFacade;
    private Pillar p = new Pillar();
    private List<Clutch> clutch_list = new ArrayList<>();

    private PillarMaterial matheriallPillar;
    private PillarType typePillar;
    private PillarOwner ownerPillar;
    
    PointWizard pz = new PointWizard();
    
    
    public PillarController() {
    }
    
    
    public String add(){
        //System.out.println("1:" + clutchBean.findAll() + " 2:  " + clutchBean.getAdded_clutch() +" 3: " + clutchBean.takeList());
        //clutch_list = clutchBean.takeList();
//        if(pz.isSkip()!= true){
//            //this.p.setClutch(this.clutc_rend);
//            System.out.println(clutch_list);
//        }
        //this.p.setClutch(clutch_list);
        this.pillarFacade.create(this.p);
        return "index";
    }
    
    public List<Pillar> findAll(){
        return this.pillarFacade.findAll();
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
    

    public Pillar getP() {
        return p;
    }

    public void setP(Pillar p) {
        this.p = p;
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

    public PillarOwner getOwnerPillar() {
        return ownerPillar;
    }

    public void setOwnerPillar(PillarOwner ownerPillar) {
        this.ownerPillar = ownerPillar;
    }

    public List<Clutch> getClutch_list() {
        return clutch_list;
    }

    public void setClutch_list(List<Clutch> clutch_list) {
        this.clutch_list = clutch_list;
    }
    
    
}
