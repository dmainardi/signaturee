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
package com.dmainardi.signaturee.presentation;

import com.dmainardi.signaturee.business.boundary.SignatureService;
import com.dmainardi.signaturee.business.entity.Image;
import com.dmainardi.signaturee.business.entity.Signature;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.DatatypeConverter;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Named
@ViewScoped
public class SignaturePresenter implements Serializable{
    @Inject
    SignatureService service;
    
    private String imageDataUri;
    private Signature signature;
    private Long id;
    
    public List<Signature> listSignatures() {
        return service.listSignatures();
    }
    
    public String saveSignature() {
        if (imageDataUri == null || imageDataUri.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Image must be processed"));
            return null;
        }
        else {
            String find = "base64,";
            byte[] bytes = DatatypeConverter.parseBase64Binary(imageDataUri.substring(imageDataUri.indexOf(find) + find.length()));
            Image imageTemp = new Image(bytes);
            signature.setImage(imageTemp);
        }
        try {
            service.saveSignature(signature);
        } catch (EJBException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ExceptionUtility.unwrap(e.getCausedByException()).getLocalizedMessage()));
            return null;
        }
        
        return "/signatures?faces-redirect=true";
    }
    
    public void detailSignature() {
        if (id != null) {
            if (id == 0)
                signature = new Signature();
            else
                signature = service.readSignature(id);
            id = null;
        }
    }
    
    public void deleteSignature(Signature signature) {
        try {
            service.deleteSignature(signature.getId());
        } catch (EJBException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ExceptionUtility.unwrap(e.getCausedByException()).getLocalizedMessage()));
        }
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageDataUri() {
        return imageDataUri;
    }

    public void setImageDataUri(String imageDataUri) {
        this.imageDataUri = imageDataUri;
    }
    
}
