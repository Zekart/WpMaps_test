/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitys;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Zekart
 */
@Entity
public class Fiber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String addressOwner;
    private String indicationReflector;
    private int numberFiber;
    private int typeFiber; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressOwner() {
        return addressOwner;
    }

    public void setAddressOwner(String addressOwner) {
        this.addressOwner = addressOwner;
    }

    public String getIndicationReflector() {
        return indicationReflector;
    }

    public void setIndicationReflector(String indicationReflector) {
        this.indicationReflector = indicationReflector;
    }

    public int getNumberFiber() {
        return numberFiber;
    }

    public void setNumberFiber(int numberFiber) {
        this.numberFiber = numberFiber;
    }

    public int getTypeFiber() {
        return typeFiber;
    }

    public void setTypeFiber(int typeFiber) {
        this.typeFiber = typeFiber;
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
        if (!(object instanceof Fiber)) {
            return false;
        }
        Fiber other = (Fiber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Fiber[" + id + ","+addressOwner+","+indicationReflector+","+numberFiber+","+typeFiber+" ]";
    }
    
}
