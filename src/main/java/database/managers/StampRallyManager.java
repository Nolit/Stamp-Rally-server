package database.managers;

import database.entities.StampRallys;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class StampRallyManager {
    @PersistenceContext
    private EntityManager em;

    public void create(StampRallys stampRally){
        em.persist(stampRally);
    }

    public StampRallys read(Integer id){
       return em.find(StampRallys.class, id);
    }
    
    public void update(StampRallys stampRally){
        em.merge(stampRally);
    }
    
    public void remove(StampRallys stampRally){
        StampRallys deleteTarget = em.merge(stampRally);
        em.remove(deleteTarget);
    }
}
