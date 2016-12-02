package database.managers;

import database.entities.Users;
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
}
