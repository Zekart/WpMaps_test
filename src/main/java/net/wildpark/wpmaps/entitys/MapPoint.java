/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitys;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "releases")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "release_type")


public  class MapPoint {
    

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private int id;

    @Column(name="owner")
    private String owner;
    
    @Column(name = "point_lat")
    private Double lat;
    
    @Column(name = "point_lng")
    private Double lng;
    
    @Column(name = "address")
    private String address;
    
    @Transient
    private String release_type;
               
    @OneToMany(targetEntity = Clutch.class, mappedBy="mappoint", cascade= {CascadeType.ALL}, fetch=FetchType.EAGER)
    private List<Clutch> clutch;
    
    public String getDecriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }
    
    public MapPoint() {
    }

    protected MapPoint(int id, String owner,Double lat, Double lng,String address,List<Clutch> clutch ) {
        this.id = id;
        this.owner = owner;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
        this.clutch = clutch;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    

    public String getRelease_type() {
        return release_type;
    }

    public void setRelease_type(String release_type) {
        this.release_type = release_type;
    }

    public List<Clutch> getClutch() {
        return clutch;
    }

    public void setClutch(List<Clutch> clutch) {
        this.clutch = clutch;
    }



    
    @Override
    public String toString() {
     return "Release{" +
       "id=" + id +
       ", name='" + owner + '\'' +
             ", name='"  + '\'' +
       '}';
    }



}
