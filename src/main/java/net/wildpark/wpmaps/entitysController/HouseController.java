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
import net.wildpark.wpmaps.entitys.House;
import net.wildpark.wpmaps.enums.HouseOwner;
import net.wildpark.wpmaps.enums.HouseType;
import net.wildpark.wpmaps.enums.TypeConnection;
import net.wildpark.wpmaps.facades.HouseFacade;

/**
 *
 * @author zekart
 */
@Named(value = "houseController")
@SessionScoped
public class HouseController implements Serializable {

    @EJB
    private HouseFacade houseFacade;
    private House h = new House();
    
    private HouseType typeOfHouse;
    private HouseOwner ownerofHouse;
    private TypeConnection typeConnection;

    public HouseController() {
    }
    
    public String add(){
        this.houseFacade.create(this.h);
        return "index";
    }
    
    public List<House> findAll(){
        return this.houseFacade.findAll();
    }
    
    
    public HouseType[] getHouseType() {
        return HouseType.values();
    }
    public HouseOwner[] getHouseOwner() {
        return HouseOwner.values();
    }    
    public TypeConnection[] getTypeConnection() {
        return TypeConnection.values();
    } 
    public House getH() {
        return h;
    }

    public void setH(House h) {
        this.h = h;
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
    
    public TypeConnection getTypeOfConnection() {
        return typeConnection;
    }

    public void setTypeOfConnection(TypeConnection typeConnection) {
        this.typeConnection = typeConnection;
    }
    
    
}
