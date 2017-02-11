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
        
        Reviews review = findEvaluatedData(userId, stampRallyId);
        if(review == null){
            review =  new Reviews(new ReviewsPK(stampRallyId, userId));
            review.setUsers(user);
            review.setStampRallys(stampRally);
            review.setUpdateDate(new Date());
            review.setReview(point);
            create(review);
            return;
        }
        review.setUpdateDate(new Date());
        review.setReview(point);
        update(review);
    }
    
    public Reviews findEvaluatedData(int userId, int stampRallyId){
        List<Reviews> reviewList = em.createNamedQuery("Reviews.findEvaluatedData", Reviews.class)
                .setParameter("userId", userId)
                .setParameter("stampRallyId", stampRallyId)
                .getResultList();
        return reviewList.isEmpty() ? null : reviewList.get(0);
    }
    
    public Double getAveragePoint(int stampRallyId){
        List<Double> points =  em.createNamedQuery("Reviews.averageByStamrallyId", Double.class)
                .setParameter("stampRallyId", stampRallyId)
                .getResultList();
        return points.isEmpty() ? null : points.get(0);
    }
}
