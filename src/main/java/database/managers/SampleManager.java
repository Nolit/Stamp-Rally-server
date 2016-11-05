package database.managers;

import database.entities.Sample;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SampleManager {
    @PersistenceContext
    private EntityManager em;

    public void create(Sample sample){
        em.persist(sample);
    }

    public Sample read(Integer id){
       return em.find(Sample.class, id);
    }

    public void update(Sample sample){
        em.merge(sample);
    }
    
    public void remove(Sample sample){
        Sample deleteTarget = em.merge(sample);
        em.remove(deleteTarget);
    }
}
