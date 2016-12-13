/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.managers;

import database.entities.StampPads;
import database.entities.StampRallys;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class StampPadsManager {
    @PersistenceContext
    private EntityManager em;

    public void create(StampPads pad){
        em.persist(pad);
    }

    public StampPads read(Integer id){
       return em.find(StampPads.class, id);
    }
    
    public void update(StampPads pad){
        em.merge(pad);
    }
    
    public void remove(StampPads pad){
        StampPads deleteTarget = em.merge(pad);
        em.remove(deleteTarget);
    }
}
