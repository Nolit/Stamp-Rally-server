/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.managers;

import database.entities.StampPads;
import database.entities.Stamps;
import database.entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yaboo
 */
@Stateless
public class StampManager {
    @PersistenceContext
    private EntityManager em;

    public void create(Stamps stamp){
        em.persist(stamp);
    }

    public Stamps read(Integer id){
       return em.find(Stamps.class, id);
    }
    
    public Stamps update(Stamps stamp){
        return em.merge(stamp);
    }
    
    public void remove(Stamps stamp){
        Stamps deleteTarget = em.merge(stamp);
        em.remove(deleteTarget);
    }
    
    public void refresh(Stamps stamp){
        em.refresh(stamp);
    }
    
    public void flash(){
        em.flush();
    }
    
    public List<Stamps> findByUserId(Integer userId){
        return em.createNamedQuery("Stamps.findByUserId", Stamps.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
