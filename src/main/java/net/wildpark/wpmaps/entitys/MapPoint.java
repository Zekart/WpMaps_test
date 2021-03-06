/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "point")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "release_type")


public  class MapPoint implements Serializable{
    
    
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
    
    @Column(name = "transportstation")
    private String transportStation;
    
    @Column(name = "numberstation")
    private int numberStation;  
    
    @Lob
    @Basic(fetch=LAZY)
    @Column(name = "pic")
    private byte[] pic;
    
    @Transient
    private String release_type;
               
    @OneToMany(targetEntity = Clutch.class,  cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Clutch> clutch;


    
    public String getDecriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }
    
    public MapPoint() {
    }

    protected MapPoint(int id, String owner,Double lat, Double lng,String address,String transportStation,int numberStation, byte[] pic, List<Clutch> clutch) {
        this.id = id;
        this.owner = owner;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
        this.transportStation = transportStation;
        this.numberStation = numberStation;
        this.clutch = clutch;
        this.pic = pic;
        
    }
    
    protected  MapPoint(int id, Double lat,Double lng, byte[] pic){
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.pic = pic;
    }
    
    protected  MapPoint(int id,String owner, Double lat,Double lng,String address, byte[] pic,List<Clutch> clutch){
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.owner = owner;
        this.address = address;
        this.pic = pic;
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

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
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
    
    
    @Override
    public String toString() {
     return "Release{" +
       "id=" + id +
       ", name='" + owner + '\'' +
             ", name='"  + '\'' +
       '}';
    }



}
