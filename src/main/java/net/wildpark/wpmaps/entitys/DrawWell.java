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
import javax.persistence.Table;

/**
 *
 * @author Zekart
 */
@Entity
@Table(name = "drawwell")
@DiscriminatorValue("drawWell")
@AttributeOverride(name = "numberOfTracks", column = @Column(name = "mp3_release_number_of_tracks"))
public class DrawWell extends MapPoint {

    @Column(name = "mp3_release_bitrate")
    private int bitrate;

    public DrawWell() {

    }

    public DrawWell(int id, String name,Double lat,Double lng, int year, String length, int numberOfTracks) {
        super(id, name,lat,lng, year, length);
        this.bitrate = bitrate;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    @Override
    public String toString() {
        return "DrawWell{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", year=" + getYear() +
                ", length='" + getLength() + '\'' +
                ", bitrate=" + bitrate +
                '}';
    }
}
    
