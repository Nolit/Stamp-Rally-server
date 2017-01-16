package database.managers;

import database.entities.RallyCompleteUsers;
import database.entities.StampRallys;
import database.entities.Users;
import java.util.List;
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
    
    public List<StampRallys> search(String searchword){
        return (List<StampRallys>) em.createNamedQuery("StampRallys.findBySearchKeyWord",StampRallys.class)
        .setParameter("keyword", "%" + searchword + "%")
        .getResultList();
    }
    
    public RallyCompleteUsers getStampRallyCompleteUser(Integer achieverId, Integer stampRallyId){
        List<RallyCompleteUsers> ret = em.createNamedQuery("RallyCompleteUsers.findByUserIdAndReferenceStamprallyId", RallyCompleteUsers.class)
                .setParameter("achieverId", achieverId)
                .setParameter("stamprallyId", stampRallyId)
                .getResultList();
            return ret.size() > 0 ? ret.get(0) : null;
    }
    
}