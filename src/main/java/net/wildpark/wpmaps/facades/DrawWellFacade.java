/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.wildpark.wpmaps.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.wildpark.wpmaps.entitys.DrawWell;


/**
 *
 * @author Zekart
 */
@Stateless
public class DrawWellFacade extends AbstractFacade<DrawWell> {

    @PersistenceContext(unitName = "net.wildpark.wpmaps_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DrawWellFacade() {
        super(DrawWell.class);
    }
    
}
