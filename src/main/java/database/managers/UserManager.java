package database.managers;

import database.entities.Friends;
import database.entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserManager {
    @PersistenceContext
    private EntityManager em;
    
    public void create(Users user){
        em.persist(user);
    }
    
    public Users read(Integer user_id){
       return em.find(Users.class, user_id);
    }
    
    public Users update(Users user){
        return em.merge(user);
    }
    
    public Users readByEmailAndPassword(String email, String password){
        List<Users> list = em.createNamedQuery("Users.findByMailAddressAndPassword", Users.class)
                .setParameter("mailAddress", email)
                .setParameter("password", password)
                .getResultList();
        return list.isEmpty() ? null : list.get(0);
    }
    
    public List<Friends> getFollowList(int userId){
        return em.createNamedQuery("Friends.findByFollowId", Friends.class)
                .setParameter("followId", userId)
                .getResultList();
    }
    
    public int getFollowCount(int userId){
        List<Friends> list = getFollowList(userId);
        return list.isEmpty() ? 0 : list.size();
    }
    
     public List<Friends> getFollowerList(int userId){
        return em.createNamedQuery("Friends.findByFollowerId", Friends.class)
                .setParameter("followerId", userId)
                .getResultList();
     }
     
     public boolean isFollow(int followId, int followerId){
        return em.createNamedQuery("Friends.findByFollowSet", Friends.class)
            .setParameter("followId", followId)
            .setParameter("followerId", followerId)
            .getResultList().isEmpty() ? false : true;
     }
    
    public int getFollowerCount(int userId){
        List<Friends> list = getFollowerList(userId);
        return list.isEmpty() ? 0 : list.size();
    }
    
    public void refresh(Users user){
        em.refresh(user);
    }
    
    public void close(){
        em.close();
    }
}
