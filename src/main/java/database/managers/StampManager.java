package database.managers;

import database.entities.Stamps;
import database.entities.Users;
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
    public List<Stamps> getMyStampBook(Integer referenceUserId){
        return em.createNamedQuery("Stamps.findByReferenceUserId", Stamps.class)
                .setParameter("userId", referenceUserId)
                .getResultList();
    }
    
//    public List<Stamps> getStampRallyAlbum(Integer stampRallyid, Integer referenceUserid){
//        return em.createNamedQuery("Stamps.findByStampRallyIdAndReferenceUserId", .class)
//    }
}
