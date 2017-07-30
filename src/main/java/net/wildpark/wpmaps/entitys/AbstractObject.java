/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitys;

import java.util.Date;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author Panker-RDP
 */
public class AbstractObject {
    private String title;
    private String about;
    private Date added;
    private Date lastModified;
    private LatLng coordinate;
    
    private String getLatLngAsString(){
        return String.valueOf(coordinate.getLat())+","+String.valueOf(coordinate.getLng());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public LatLng getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(LatLng coordinate) {
        this.coordinate = coordinate;
    }
    
    
    
    
}
