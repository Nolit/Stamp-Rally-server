package database.managers;

import database.entities.Stamps;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
    public void update(Stamps stamp){
        em.merge(stamp);
    }
    
    public void remove(Stamps stamp){
        Stamps deleteTarget = em.merge(stamp);
        em.remove(deleteTarget);
    }
    
    public List<Stamps> getMyStampBook(Integer referenceUserId){
        return em.createNamedQuery("Stamps.findByReferenceUserId", Stamps.class)
                .setParameter("userId", referenceUserId)
                .getResultList();
    }
    
//    public List<Stamps> getStampRallyAlbum(Integer stampRallyid, Integer referenceUserid){
//        return em.createNamedQuery("Stamps.findByStampRallyIdAndReferenceUserId", .class)
//    }
}
