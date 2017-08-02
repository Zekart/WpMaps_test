/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitys;

import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import net.wildpark.wpmaps.enums.PillarMaterial;
import net.wildpark.wpmaps.enums.PillarType;

@Entity
@Table(name = "pillar")
@DiscriminatorValue("Pillar")
//@AttributeOverride(name = "numberOfTracks", column = @Column(name = "cd_release_number_of_tracks"))
public class Pillar extends MapPoint{

    @Column(name = "transportstation")
    private String transportStation;
    
    @Column(name = "numberstation")
    private int numberStation;    
    
    @Column(name = "material")
    @Enumerated(EnumType.STRING)
    private PillarMaterial material;
    
    @Column(name = "pillar_type")
    @Enumerated(EnumType.STRING)
    private PillarType type;
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true )
    private List<Clutch> clutch;

    public Pillar() {

    }

    public Pillar(int id, String owner,Double lat,Double lng,String transportStation,int numberStation,PillarMaterial material, PillarType type) {
        super(id, owner,lat,lng);
        this.material = material;
        this.numberStation = numberStation;
        this.material = material;
        this.type = type;
        this.clutch = clutch;
    }

    public PillarType getType() {
        return type;
    }

    public void setType(PillarType type) {
        this.type = type;
    }

    public String getTransportStation() {
        return transportStation;
    }

    public void setTransportStation(String transportStation) {
        this.transportStation = transportStation;
    }

    public int getNumberStation() {
        return numberStation;
    }

    public void setNumberStation(int numberStation) {
        this.numberStation = numberStation;
    }

    public PillarMaterial getMaterial() {
        return material;
    }

    public void setMaterial(PillarMaterial material) {
        this.material = material;
    }

    public List<Clutch> getClutch() {
        return clutch;
    }

    public void setClutch(List<Clutch> clutch) {
        this.clutch = clutch;
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
                ", clutch=" + clutch +
                '}';
    }


}
