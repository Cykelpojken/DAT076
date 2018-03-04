/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import Forum.EntityClasses.Thread;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Arvid
 */
@Stateless
@Named
public class ThreadBean {

    private static final Logger LOG = Logger.getLogger(ThreadBean.class.getName());
    @PersistenceContext
    EntityManager em;
    
    @Inject Thread thread;

    public List getList(){
        return em.createNamedQuery("Thread.findAll").getResultList();
    }
    
    public Thread getThread(String name){
        return (Thread)em.createNamedQuery("Thread.findByTitle").setParameter("title", name).getSingleResult();
    }
    
    public void create(){
        System.out.println(thread.toString());
        List<Thread> l = getList();
        Thread dummy = new Thread(thread.getTitle(), thread.getText());
        for(Thread t : l){
            if(dummy.equals(t))
                return;
        }
        try{
            em.persist(dummy);
        }
        catch(EntityExistsException e){
        e.printStackTrace();}
    }
    
    public String getTitle(String title){
        return getThread(title).getTitle();
    }
    public int getPosts(String title){
        return getThread(title).getPosts();
    }
}
