package database.managers;

import database.entities.Reviews;
import database.entities.ReviewsPK;
import database.entities.StampRallys;
import database.entities.Users;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ReviewManager {
    @PersistenceContext
    private EntityManager em;

    public void create(Reviews review){
        em.persist(review);
    }
    
    public Reviews update(Reviews review){
        return em.merge(review);
    }
    
    public void remove(Reviews review){
        Reviews deleteTarget = em.merge(review);
        em.remove(deleteTarget);
    }
    
    public void createOrUpdate(Users user, StampRallys stampRally, int point){
        int userId = user.getUserId();
        int stampRallyId = stampRally.getStamprallyId();
        
        List<Reviews> reviewList = em.createNamedQuery("Reviews.findEvaluatedData", Reviews.class)
                .setParameter("userId", userId)
                .setParameter("stampRallyId", stampRallyId)
                .getResultList();
        Reviews review;
        if(reviewList.isEmpty()){
            review =  new Reviews(new ReviewsPK(stampRallyId, userId));
            review.setUsers(user);
            review.setStampRallys(stampRally);
        }else{
            review =  reviewList.get(0);
        }
        review.setUpdateDate(new Date());
        review.setReview(point);
        if(reviewList.isEmpty()){
            create(review);
        }else{
            update(review);
        }
    }
}
