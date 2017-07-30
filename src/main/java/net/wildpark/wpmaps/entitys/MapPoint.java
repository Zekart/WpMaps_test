/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitys;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "releases")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "release_type")


public abstract class MapPoint {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private int id;

    @Column(name = "point_name")
    private String name;
    
    @Column(name = "point_lat")
    private Double lat;
    
    @Column(name = "point_lng")
    private Double lng;

    @Column(name = "release_year")
    private int year;

    @Column(name = "release_length")
    private String length;
    
    @Column(name="release_type")
    private String release_type;

    public MapPoint() {
    }



    protected MapPoint(int id, String name,Double lat, Double lng, int year, String length) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.year = year;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getRelease_type() {
        return release_type;
    }

    public void setRelease_type(String release_type) {
        this.release_type = release_type;
    }
    

    @Override
    public String toString() {
     return "Release{" +
       "id=" + id +
       ", name='" + name + '\'' +
       ", year=" + year +
       ", length='" + length + '\'' +
       '}';
    }
}
