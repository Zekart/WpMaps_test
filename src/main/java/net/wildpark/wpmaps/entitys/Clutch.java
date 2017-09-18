/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Zekart
 */
@Entity
@Table(name = "clutch")

public class Clutch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="clutch_id")
    private int id;
    private String conditions;
    private int cassetsCount;
    private String info;
    private int inputs;
    
    @OneToMany(mappedBy = "clutch", cascade = CascadeType.ALL)
    private List<Cabel> cable;

    public Clutch() {
    }
    
    

    public Clutch( List<Cabel> cable ) {
        this.cable = cable;
    }
    
//    
//    
//    @ManyToOne
//    @JoinColumn(name = "point_id")
//    private MapPoint mappoint;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public int getCassetsCount() {
        return cassetsCount;
    }

    public void setCassetsCount(int cassetsCount) {
        this.cassetsCount = cassetsCount;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getInputs() {
        return inputs;
    }

    public void setInputs(int inputs) {
        this.inputs = inputs;
    }

    public List<Cabel> getCable() {
        return cable;
    }

    public void setCable(List<Cabel> cable) {
        this.cable = cable;
    }

    @Override
    public String toString() {
        return "Clutch :)[ id=" + id +        
                ", Conditions='" + conditions + '\'' +
                ", Count of cassets='" + cassetsCount + '\'' +
                ", Info='" + info + '\'' +
                ", Inputs='" + inputs + '\'' +
                ", Cable='" + cable + '\'' +
                
       '}';
    }
    
}
