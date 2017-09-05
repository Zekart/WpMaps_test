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
import net.wildpark.wpmaps.enums.PillarMaterial;
import net.wildpark.wpmaps.enums.PillarType;

@Entity
@Table(name = "pillar")
@DiscriminatorValue("Столб")
//@AttributeOverride(name = "numberOfTracks", column = @Column(name = "cd_release_number_of_tracks"))
public class Pillar extends MapPoint implements Serializable{  
    
    @Column(name = "material")
    @Enumerated(EnumType.STRING)
    private PillarMaterial material;
    
    @Column(name = "pillar_type")
    @Enumerated(EnumType.STRING)
    private PillarType type;
        

    public Pillar() {

    }

    public Pillar(int id, String owner,Double lat,Double lng,String address,String transportStation,int numberStation,PillarMaterial material, PillarType type,byte[] pic,List<Clutch> clutch) {
        super(id, owner,lat,lng,address,transportStation,numberStation,pic,clutch);
        this.material = material;
        this.material = material;
        this.type = type;
    }

    public PillarType getType() {
        return type;
    }

    public void setType(PillarType type) {
        this.type = type;
    }

    public PillarMaterial getMaterial() {
        return material;
    }

    public void setMaterial(PillarMaterial material) {
        this.material = material;
    }   
   
    

    @Override
    public String toString() {
        return "Pillar{" +
                "id=" + getId() +
                ", owner='" + getOwner() + '\'' +
                ", lat='" + getLat() + '\'' +
                ", lng='" + getLng() + '\'' +
                ", transportstation=" + getTransportStation() +
                ", numberStation='" + getNumberStation() + '\'' +
                ", material=" + getMaterial() +
                ", type=" + type +
                ", type=" + getRelease_type() +
                ", clutch=" + getClutch().size() +
                '}';
    }


}
