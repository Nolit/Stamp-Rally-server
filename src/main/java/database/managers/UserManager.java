package database.managers;

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
        //ログインに使う
    public boolean login(String email, String password){
        List<Users> a = em.createNamedQuery("Users.findByLoginData", Users.class)
          .setParameter("mailAddress", email)
          .setParameter("password", password)
          .getResultList();
        boolean b = a.size()>0;
        return b;
    }
    
    public void refresh(Users user){
        em.refresh(user);
    }
    
    public void close(){
        em.close();
    }
}
