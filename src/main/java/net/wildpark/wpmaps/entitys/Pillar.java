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
import javax.persistence.Transient;
import net.wildpark.wpmaps.enums.PillarType;

@Entity
@Table(name = "pillar")
@DiscriminatorValue("Pillar")
@AttributeOverride(name = "numberOfTracks", column = @Column(name = "cd_release_number_of_tracks"))
public class Pillar extends MapPoint{

    @Column(name = "cd_release_type")
    @Enumerated(EnumType.STRING)
    private PillarType type;

    public Pillar() {

    }

    public Pillar(int id, String name,Double lat,Double lng, int year, String length, PillarType type) {
        super(id, name,lat,lng, year, length);
        this.type = type;
    }

    public PillarType getType() {
        return type;
    }

    public void setType(PillarType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CDRelease{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", lat='" + getLat() + '\'' +
                ", lng='" + getLng() + '\'' +
                ", year=" + getYear() +
                ", length='" + getLength() + '\'' +
                ", type=" + type +
                ", type=" + getRelease_type() +
                '}';
    }


}
