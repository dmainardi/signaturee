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
package com.dmainardi.signaturee.business.boundary;

import com.dmainardi.signaturee.business.entity.Signature;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Stateless
public class SignatureService {
    @PersistenceContext
    EntityManager em;
    
    public Signature saveSignature(Signature signature) {
        if (signature.getId() == null)
            em.persist(signature);
        else
            return em.merge(signature);
        
        return null;
    }
    
    public Signature readSignature(Long id) {
        return em.find(Signature.class, id);
    }
    
    public void deleteSignature(Long id) {
        em.remove(readSignature(id));
    }

    public List<Signature> listSignatures() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Signature> query = cb.createQuery(Signature.class);
        Root<Signature> root = query.from(Signature.class);
        CriteriaQuery<Signature> select = query.select(root).distinct(true);

        return em.createQuery(select).getResultList();
    }
}
