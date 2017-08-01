/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.entitys;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import net.wildpark.wpmaps.enums.DrawWellOwner;
import net.wildpark.wpmaps.enums.DrawWellType;
import net.wildpark.wpmaps.enums.PillarType;

/**
 *
 * @author Zekart
 */
@Entity
@Table(name = "drawwell")
@DiscriminatorValue("Draw")
//@AttributeOverride(name = "numberOfTracks", column = @Column(name = "mp3_release_number_of_tracks"))
public class DrawWell extends MapPoint {

    @Column(name = "draw_well_type")
    @Enumerated(EnumType.STRING)
    private DrawWellType type_draw_well;
    

    public DrawWell() {

    }

    public DrawWell(int id, String owner,Double lat,Double lng, int numberOfTracks) {
        super(id, owner,lat,lng);
        this.type_draw_well = type_draw_well;
    }

    public DrawWellType getType_draw_well() {
        return type_draw_well;
    }

    public void setType_draw_well(DrawWellType type_draw_well) {
        this.type_draw_well = type_draw_well;
    }

    @Override
    public String toString() {
        return "DrawWell{" +
                "id=" + getId() +
                ", name='" + getOwner() + '\'' +
                ", type draw well=" + type_draw_well +
                '}';
    }
}
    
