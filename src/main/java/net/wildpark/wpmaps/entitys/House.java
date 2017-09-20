/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import net.wildpark.wpmaps.enums.HouseType;

@Entity
@Table(name = "house")
@DiscriminatorValue("Дом")
//@AttributeOverride(name = "numberOfTracks", column = @Column(name = "mp3_release_number_of_tracks"))
public class House extends MapPoint implements Serializable{

    @Column(name = "name")
    private String house_name;    
    
    @Column(name = "house_type")
    @Enumerated(EnumType.STRING)
    private HouseType type_house;
    
    @Column(name = "count_floor")
    private int count_floor;    
    
    @Column(name = "count_entrance")
    private int count_entrance;
       
    @Column(name = "big_box")
    private boolean bigBox;
 
    @Column(name = "house_number")
    private String house_number;

    
    public House() {

    }

    public House(int id, String owner, Double lat, Double lng,HouseType type_house,String house_name,String house_number,int count_floor,String address,int count_entrance,boolean bigBox,byte[] pic,List<Clutch> clutch) {
        super(id, owner,lat,lng,address,pic,clutch);
        this.house_name = house_name;
        this.type_house = type_house;
        this.house_number = house_number;
        this.count_floor = count_floor;
        this.count_entrance = count_entrance;
        this.bigBox = bigBox;
    }

    public HouseType getType_house() {
        return type_house;
    }

    public void setType_house(HouseType type_house) {
        this.type_house = type_house;
    }

    public int getCount_floor() {
        return count_floor;
    }

    public void setCount_floor(int count_floor) {
        this.count_floor = count_floor;
    }


    public int getCount_entrance() {
        return count_entrance;
    }

    public void setCount_entrance(int count_entrance) {
        this.count_entrance = count_entrance;
    }

    public boolean isBigBox() {
        return bigBox;
    }

    public void setBigBox(boolean bigBox) {
        this.bigBox = bigBox;
    }

    public String getHouse_name() {
        return house_name;
    }

    public void setHouse_name(String house_name) {
        this.house_name = house_name;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }
    
    

    @Override
    public String toString() {
        return "House{" +
                "id=" + getId() +
                ", owner='" + getOwner() + '\'' +
                ", lat='" + getLat() + '\'' +
                ", lng='" + getLng() + '\'' +
                ", house_type=" + type_house +
                ", type=" + getClutch() +
                '}';
    }
}