/*
 * Copyright (C) 2016 Davide Mainardi <ingmainardi at live.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.dmainardi.signaturee.business.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Entity
public class Image extends BaseEntity<Long>{
    @Id
    @GeneratedValue
    private Long id;
    
    @Lob
    @NotNull
    @Column(nullable = false)
    private byte[] image;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    public Image() {
    }

    public Image(byte[] image) {
        this.image = image;
    }
    
    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        lastModified = new Date();
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Date getLastModified() {
        return lastModified;
    }
    
}
