/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Zekart
 */
@Entity
public class Cabel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capacity;
    private String inplace;
    private int lenthCable;
    private String marking;
    private int moduleCount;
    private String outPlace;
    private String production;
    
    public Cabel() {
    }

    public Cabel(int capacity, String inplace, int lenthCable, String marking, int moduleCount, String outPlace, String production) {
        this.capacity = capacity;
        this.inplace = inplace;
        this.lenthCable = lenthCable;
        this.marking = marking;
        this.moduleCount = moduleCount;
        this.outPlace = outPlace;
        this.production = production;
    }
    

    @ManyToOne
    @JoinColumn(name = "clutch_id")  
    private Clutch clutch;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
    private List<Fiber> fiber;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getInplace() {
        return inplace;
    }

    public void setInplace(String inplace) {
        this.inplace = inplace;
    }

    public int getLenthCable() {
        return lenthCable;
    }

    public void setLenthCable(int lenthCable) {
        this.lenthCable = lenthCable;
    }

    public String getMarking() {
        return marking;
    }

    public void setMarking(String marking) {
        this.marking = marking;
    }

    public int getModuleCount() {
        return moduleCount;
    }

    public void setModuleCount(int moduleCount) {
        this.moduleCount = moduleCount;
    }

    public String getOutPlace() {
        return outPlace;
    }

    public void setOutPlace(String outPlace) {
        this.outPlace = outPlace;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public List<Fiber> getFiber() {
        return fiber;
    }

    public void setFiber(List<Fiber> fiber) {
        this.fiber = fiber;
    }

    public Clutch getClutch() {
        return clutch;
    }

    public void setClutch(Clutch clutch) {
        this.clutch = clutch;
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
        if (!(object instanceof Cabel)) {
            return false;
        }
        Cabel other = (Cabel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.wildpark.wpmaps.entitys.Cabel[ id=" + id + " ]";
    }
    
}
