/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author Panker-RDP
 */
@Entity
public class Cord extends AbstractObject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int number;
    private double lastMeasurement;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfLastMeasurement;
    private Long cableId;
    private boolean used=false;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getLastMeasurement() {
        return lastMeasurement;
    }

    public void setLastMeasurement(double lastMeasurement) {
        this.lastMeasurement = lastMeasurement;
    }

    public Date getDateOfLastMeasurement() {
        return dateOfLastMeasurement;
    }

    public void setDateOfLastMeasurement(Date dateOfLastMeasurement) {
        this.dateOfLastMeasurement = dateOfLastMeasurement;
    }

    public Long getCableId() {
        return cableId;
    }

    public void setCableId(Long cableId) {
        this.cableId = cableId;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
    
    @Override
    public void setCoordinate(LatLng coordinate) {
        super.setCoordinate(coordinate); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LatLng getCoordinate() {
        return super.getCoordinate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLastModified(Date lastModified) {
        super.setLastModified(lastModified); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getLastModified() {
        return super.getLastModified(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAdded(Date added) {
        super.setAdded(added); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getAdded() {
        return super.getAdded(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAbout(String about) {
        super.setAbout(about); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAbout() {
        return super.getAbout(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTitle() {
        return super.getTitle(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cord)) {
            return false;
        }
        Cord other = (Cord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.wildpark.wpmaps.entitys.Cord[ id=" + id + " ]";
    }
    
}
