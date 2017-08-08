/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitys;

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
public class House extends MapPoint {

    @Column(name = "house_type")
    @Enumerated(EnumType.STRING)
    private HouseType type_house;
       

    public House() {

    }

    public House(int id, String owner, Double lat, Double lng,String address,List<Clutch> clutch, int numberOfTracks) {
        super(id, owner,lat,lng,address,clutch);
        this.type_house = type_house;
    }

    public HouseType getType_house() {
        return type_house;
    }

    public void setType_house(HouseType type_house) {
        this.type_house = type_house;
    }
    

    @Override
    public String toString() {
        return "House{" +
                "id=" + getId() +
                ", owner='" + getOwner() + '\'' +
                ", lat='" + getLat() + '\'' +
                ", lng='" + getLng() + '\'' +
                ", house_type=" + type_house +
                ", type=" + getRelease_type() +
                '}';
    }
}