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
import javax.persistence.Table;

/**
 *
 * @author Zekart
 */
@Entity
@Table(name = "drawwell")
@DiscriminatorValue("Колодец")
//@AttributeOverride(name = "numberOfTracks", column = @Column(name = "mp3_release_number_of_tracks"))
public class DrawWell extends MapPoint implements Serializable{

    @Column(name = "pit")
    private Boolean pit;  
    
    @Column(name = "chanel_number")
    private int chanel_number; 

    public DrawWell() {

    }

    public DrawWell(int id,Double lat,Double lng,Boolean pit,int chanel_number, byte[] pic) {
        super(id, lat, lng,pic);
        this.pit = pit;
        this.chanel_number = chanel_number;
        
    }

    public Boolean getPit() {
        return pit;
    }

    public void setPit(Boolean pit) {
        this.pit = pit;
    }

    public int getChanel_number() {
        return chanel_number;
    }

    public void setChanel_number(int chanel_number) {
        this.chanel_number = chanel_number;
    }
    


    @Override
    public String toString() {
        return "DrawWell{" +
                "id=" + getId() +
                ", name='" + getOwner() + '\'' +
                ", pit=" + pit +
                ", chanel number=" + chanel_number +
                '}';
    }
}
    
