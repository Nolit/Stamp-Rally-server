package database.managers;

import database.entities.RallyCompleteUsers;
import database.entities.RallyCompleteUsersPK;
import database.entities.StampRallys;
import database.entities.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Stateless
public class StampRallyManager {
    @PersistenceContext
    private EntityManager em;

    public void create(StampRallys stampRally){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<StampRallys>> constraintViolations = validator.validate(stampRally);
        if(constraintViolations.size() > 0){
            Iterator<ConstraintViolation<StampRallys>> iterator = constraintViolations.iterator();
            while(iterator.hasNext()){
                ConstraintViolation<StampRallys> cv = iterator.next();
                System.err.println(cv.getRootBeanClass().getName()+"."+cv.getPropertyPath() + " " +cv.getMessage());

//                JsfUtil.addErrorMessage(cv.getRootBeanClass().getSimpleName()+"."+cv.getPropertyPath() + " " +cv.getMessage());
            }
        }else{
            em.persist(stampRally);
        }
//        em.persist(stampRally);
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
    
    public void clearStampRally(StampRallys stampRally, Users user){
        RallyCompleteUsers completeUser = new RallyCompleteUsers(new RallyCompleteUsersPK(user.getUserId(), stampRally.getStamprallyId()));
        completeUser.setAchieveDate(new Date());
        completeUser.setChallangeDate(new Date());
        em.persist(completeUser);
    }
    
}